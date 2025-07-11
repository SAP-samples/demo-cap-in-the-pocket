import * as vscode from "vscode";
import { exec } from "child_process";
import * as http from "http";
import * as https from "https";

interface WebAppLink {
  label: string;
  url: string;
  originalPath: string;
}

class WebAppDiscovery {
  private static instance: WebAppDiscovery;
  private webApps: WebAppLink[] = [];
  private scanInterval: NodeJS.Timeout | null = null;
  private lastScanTimestamp: number = 0;
  private scanning: boolean = false;
  private listeners: ((apps: WebAppLink[]) => void)[] = [];

  private constructor() {}

  public static getInstance(): WebAppDiscovery {
    if (!WebAppDiscovery.instance) {
      WebAppDiscovery.instance = new WebAppDiscovery();
    }
    return WebAppDiscovery.instance;
  }

  public getWebApps(): WebAppLink[] {
    return [...this.webApps];
  }

  public addChangeListener(listener: (apps: WebAppLink[]) => void): void {
    this.listeners.push(listener);
  }

  public removeChangeListener(listener: (apps: WebAppLink[]) => void): void {
    const index = this.listeners.indexOf(listener);
    if (index !== -1) {
      this.listeners.splice(index, 1);
    }
  }

  private notifyListeners(): void {
    for (const listener of this.listeners) {
      listener(this.getWebApps());
    }
  }

  public startScanning(port: number = 4004): void {
    if (this.scanInterval) {
      clearInterval(this.scanInterval);
    }

    // Initial scan
    this.scanForWebApps(port);

    // Set up interval for subsequent scans (every 2 seconds)
    this.scanInterval = setInterval(() => {
      this.scanForWebApps(port);
    }, 2000);
  }

  public stopScanning(): void {
    if (this.scanInterval) {
      clearInterval(this.scanInterval);
      this.scanInterval = null;
    }
  }

  private async scanForWebApps(port: number): Promise<void> {
    // Prevent concurrent scans
    if (this.scanning) {
      return;
    }

    // Throttle scanning to prevent excessive requests
    const now = Date.now();
    if (now - this.lastScanTimestamp < 1000) {
      return;
    }

    this.scanning = true;
    this.lastScanTimestamp = now;

    try {
      const rootUrl = `http://localhost:${port}`;
      const html = await this.fetchUrl(rootUrl);
      const discoveredApps = this.parseWebAppsFromHtml(html, rootUrl);
      console.log("Discovered web apps:", discoveredApps);
      // Check if the discovered apps are different from what we already have
      const hasChanged = this.hasWebAppsChanged(discoveredApps);

      if (hasChanged) {
        // Update cache with new web apps
        this.webApps = discoveredApps;
        this.notifyListeners();
      }
    } catch (error) {
      // Server may not be running - clear apps if we had any
      if (this.webApps.length > 0) {
        this.webApps = [];
        this.notifyListeners();
      }
    } finally {
      this.scanning = false;
    }
  }

  private hasWebAppsChanged(newApps: WebAppLink[]): boolean {
    if (this.webApps.length !== newApps.length) {
      return true;
    }

    for (let i = 0; i < this.webApps.length; i++) {
      if (this.webApps[i].url !== newApps[i].url ||
          this.webApps[i].label !== newApps[i].label) {
        return true;
      }
    }

    return false;
  }

  private parseWebAppsFromHtml(html: string, rootUrl: string): WebAppLink[] {
    const webApps: WebAppLink[] = [];

    // Get configuration preference for dist vs webapp URLs
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const useDistUrl = config.get('useDistUrl') as boolean ?? true; // Default to true

    // Regular expression to find web application links
    // Look for links inside the Web Applications section
    const webAppSectionRegex = /<h2>Web Applications<\/h2>\s*<ul>([\s\S]*?)<\/ul>/i;
    const webAppSection = webAppSectionRegex.exec(html);

    if (webAppSection && webAppSection[1]) {
      // Find all list items with links
      const webAppLinkRegex = /<li>\s*<a href="([^"]+)"><span>([^<]+)<\/span><\/a>\s*<\/li>/g;
      let match;

      while ((match = webAppLinkRegex.exec(webAppSection[1])) !== null) {
        const path = match[1]; // /app_name/webapp/index.html
        const fullPath = match[2]; // /app_name/webapp

        // Extract app name from path
        const appNameMatch = /\/([^\/]+)\/webapp/.exec(path);
        if (appNameMatch && appNameMatch[1]) {
          const appName = appNameMatch[1];

          // Convert snake_case to Title Case
          const label = this.snakeCaseToTitleCase(appName);

          // Apply the URL path based on configuration
          let url = `${rootUrl}${path}`; // Default is webapp

          // Use /dist/ instead of /webapp/ if configured
          if (useDistUrl) {
            url = url.replace('/webapp/', '/dist/');
          }

          webApps.push({
            label,
            url,
            originalPath: path
          });
        }
      }
    }

    return webApps;
  }

  private snakeCaseToTitleCase(text: string): string {
    return text.split('_')
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(' ');
  }

  private fetchUrl(url: string): Promise<string> {
    return new Promise((resolve, reject) => {
      const requester = url.startsWith('https') ? https : http;

      // Create basic auth credentials (username: "authenticated", password: "")
      const auth = Buffer.from('authenticated:').toString('base64');

      // Create request options with auth header
      const options = {
        headers: {
          'Authorization': `Basic ${auth}`
        }
      };

      const request = requester.get(url, options, response => {
        if (response.statusCode !== 200) {
          reject(new Error(`Failed to fetch ${url}: ${response.statusCode}`));
          return;
        }

        let data = '';
        response.on('data', chunk => data += chunk);
        response.on('end', () => resolve(data));
      });

      request.on('error', error => reject(error));
      request.end();
    });
  }
}

export function activate(context: vscode.ExtensionContext) {
  console.log("Activating CAP-in-the-Pocket extension...");

  // Create the webview provider instance to share between commands
  const provider = new RunSpringBootViewProvider(context);
  
  // Register the webview provider
  context.subscriptions.push(
    vscode.window.registerWebviewViewProvider(
      "runSpringBootView", 
      provider
    )
  );
  
  // Register the clear logs command
  context.subscriptions.push(
    vscode.commands.registerCommand('cap-in-the-pocket.clearLogs', () => {
      if (provider._view) {
        provider.clearLogs(provider._view.webview);
      }
    })
  );
  
  // Register the kill and clear command
  context.subscriptions.push(
    vscode.commands.registerCommand('cap-in-the-pocket.killAndClear', () => {
      if (provider._view) {
        provider.killAndClear(provider._view.webview);
      }
    })
  );
}

// Update the class to store both raw and formatted logs
class RunSpringBootViewProvider implements vscode.WebviewViewProvider {
  private context: vscode.ExtensionContext;
  public _view?: vscode.WebviewView;
  // Store raw logs separately from formatted logs
  private logHistory: string = '';
  private rawLogHistory: string[] = [];

  constructor(context: vscode.ExtensionContext) {
    this.context = context;

    // Listen for configuration changes to update the buttons
    context.subscriptions.push(
      vscode.workspace.onDidChangeConfiguration(e => {
        if (e.affectsConfiguration('cap-in-the-pocket.urlButtons') && this._view) {
          // Send updated buttons without refreshing whole view
          this.updateUrlButtons();
        }
      })
    );

    // Start listening for web app changes
    const webAppDiscovery = WebAppDiscovery.getInstance();
    webAppDiscovery.addChangeListener(() => {
      if (this._view) {
        // Send updated buttons without refreshing whole view
        this.updateUrlButtons();
      }
    });
  }

  resolveWebviewView(webviewView: vscode.WebviewView) {
    this._view = webviewView;

    // Set up the webview content
    webviewView.webview.options = {
      enableScripts: true
    };
    webviewView.webview.html = this.getWebviewContent();

    // Handle messages from the webview
    webviewView.webview.onDidReceiveMessage((message) => {
      switch (message.command) {
        case "restart":
          this.restart(webviewView.webview);
          break;
        case "recompile":
          this.recompile(webviewView.webview);
          break;
        case "openUrl":
          this.openUrl(message.url);
          break;
        case "clearLogs":
          this.clearLogs(webviewView.webview);
          break;
        case "killAndClear":
          this.killAndClear(webviewView.webview);
          break;
      }
    });

    // Add visibility change handler - this is key for restoring logs when the sidebar is reopened
    webviewView.onDidChangeVisibility(() => {
      if (webviewView.visible && this.rawLogHistory.length > 0) {
        // Clear existing output first
        webviewView.webview.postMessage({ command: 'clearOutput' });

        // Send raw logs for reprocessing on the client side
        setTimeout(() => {
          webviewView.webview.postMessage({
            command: 'restoreRawLogs',
            logs: this.rawLogHistory
          });
        }, 300);
      }
    });

    // Start scanning for web apps
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const port = config.get('serverPort') as number || 4004;
    WebAppDiscovery.getInstance().startScanning(port);

    // Send initial buttons (after a short delay to ensure webview is ready)
    setTimeout(() => this.updateUrlButtons(), 100);

    // We don't need this anymore as onDidChangeVisibility will handle it
    // if (this.logHistory) {
    //   setTimeout(() => {
    //     webviewView.webview.postMessage({
    //       log: this.logHistory,
    //     });
    //   }, 500);
    // }
  }

  private logToWebview(webview: vscode.Webview, message: string): void {
    // Add to history first
    this.addToLogHistory(message);
    // Then send to webview
    webview.postMessage({ log: message });
  }

  // Update this method to store raw logs too
  private addToLogHistory(log: string): void {
    this.logHistory += log;
    this.rawLogHistory.push(log);

    // Optional: Implement log size management
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const maxLogSize = config.get('maxLogMessages') as number || 10000;

    // Simple truncation if log gets too large
    if (this.rawLogHistory.length > maxLogSize) {
      this.rawLogHistory = this.rawLogHistory.slice(-maxLogSize);
      // Rebuild formatted log history from raw logs
      this.logHistory = '';
      for (const rawLog of this.rawLogHistory) {
        this.logHistory += this.formatLogOutput(rawLog);
      }
    }
  }

  private restart(webview: vscode.Webview) {
    // Get workspace folder - use the first one if multiple are open
    const workspaceFolders = vscode.workspace.workspaceFolders;
    if (!workspaceFolders || workspaceFolders.length === 0) {
      webview.postMessage({ log: "\n❌ Error: No workspace folder is open. Please open a CAP project folder first.\n" });
      return;
    }

    // Use the first workspace folder as the project root
    const projectRoot = workspaceFolders[0].uri.fsPath;

    // Get configured commands from settings
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const port = config.get('serverPort') as number || 4004;
    const killPortCommand = (config.get('killPortCommand') as string).replace("PORT", port.toString());
    const runCommand = (config.get('runCommand') as string).replace("PORT", port.toString());

    // Update webview UI to show "running" state with the correct folder
    const logMessage = `\n▶️ Running in ${projectRoot}:\n    Stopping processes on port ${port} and starting CAP app...\n\n`;
    this.logToWebview(webview, logMessage);

    // Execute the shell command in the project root directory
    const options = {
      cwd: projectRoot,
      shell: true,
      env: {
        ...process.env
      }
    };

    // First, kill any processes using the configured port
    const killPortProcess = require('child_process').spawn(
      killPortCommand,
      [],
      options
    );

    killPortProcess.on('close', (code: number) => {
      const closeMessage = code === 0
        ? `✅ Stopped existing processes using port ${port}\n\n`
        : `ℹ️ No processes were using port ${port}\n\n`;

      this.logToWebview(webview, closeMessage);

      // Now start the configured run process
      const childProcess = require('child_process').spawn(runCommand, [], options);

      // Stream stdout in real-time
      childProcess.stdout.on('data', (data: Buffer) => {
        const rawOutput = data.toString();
        const formattedOutput = this.formatLogOutput(rawOutput);
        this.logToWebview(webview, formattedOutput);
      });

      // Stream stderr in real-time
      childProcess.stderr.on('data', (data: Buffer) => {
        const rawOutput = data.toString();
        const formattedOutput = this.formatLogOutput(rawOutput);
        this.logToWebview(webview, formattedOutput);
      });

      // Handle process completion
      childProcess.on('close', (code: number) => {
        const exitMessage = code === 0
          ? "\n✅ Process completed successfully.\n"
          : `\n⚠️ Process exited with code ${code}.\n`;
        this.logToWebview(webview, exitMessage);
      });

      // Handle process errors
      childProcess.on('error', (err: Error) => {
        webview.postMessage({ log: `\n❌ Error: ${err.message}\n` });
      });
    });

    // Handle errors from the kill port process
    killPortProcess.on('error', (err: Error) => {
      webview.postMessage({ log: `\n⚠️ Warning: Could not check for processes on port ${port}: ${err.message}\n` });
    });
  }


  private recompile(webview: vscode.Webview) {
    // Get workspace folder - use the first one if multiple are open
    const workspaceFolders = vscode.workspace.workspaceFolders;
    if (!workspaceFolders || workspaceFolders.length === 0) {
      webview.postMessage({ log: "\n❌ Error: No workspace folder is open. Please open a CAP project folder first.\n" });
      return;
    }

    // Use the first workspace folder as the project root
    const projectRoot = workspaceFolders[0].uri.fsPath;

    // Get configured commands from settings
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const compileCommand = config.get('compileCommand') as string || 'mvn compile -B';

    // Execute the shell command in the project root directory
    const options = {
      cwd: projectRoot,
      shell: true,
      env: {
        ...process.env
      }
    };

    const logMessage = `\n▶️ Recompiling the CAP app, the Spring Boot Dev Tools should reload the Java part...\n\n`;
    this.addToLogHistory(logMessage);
    webview.postMessage({ log: logMessage });

    // Now start the configured compile process
    const childProcess = require('child_process').spawn(compileCommand, [], options);

    // Stream stdout in real-time
    childProcess.stdout.on('data', (data: Buffer) => {
      const rawOutput = data.toString();
      const formattedOutput = this.formatLogOutput(rawOutput);
      this.addToLogHistory(formattedOutput);
      webview.postMessage({ log: formattedOutput });
    });

    // Stream stderr in real-time
    childProcess.stderr.on('data', (data: Buffer) => {
      const rawOutput = data.toString();
      const formattedOutput = this.formatLogOutput(rawOutput);
      this.addToLogHistory(formattedOutput);
      webview.postMessage({ log: formattedOutput });
    });

    // Handle process completion
    childProcess.on('close', (code: number) => {
      const exitMessage = code === 0
        ? "\n✅ Process completed successfully.\n"
        : `\n⚠️ Process exited with code ${code}.\n`;
      this.addToLogHistory(exitMessage);
      webview.postMessage({ log: exitMessage });
    });

    // Handle process errors
    childProcess.on('error', (err: Error) => {
      webview.postMessage({ log: `\n❌ Error: ${err.message}\n` });
    });
  }

  private openUrl(url: string) {
    // Use the shell directly to open URLs, which may prevent reload issues on mobile
    const platform = process.platform;

    // Choose the appropriate open command based on platform
    let openCommand: string;

    switch (platform) {
      case 'darwin':
        // macOS
        openCommand = `open "${url}"`;
        break;
      case 'win32':
        // Windows
        openCommand = `start "" "${url}"`;
        break;
      default:
        // Linux and others
        openCommand = `open "${url}"`;
        break;
    }

    // Execute the shell command to open the URL
    exec(openCommand, (error) => {
      if (error) {
        // Fall back to VS Code's method if the shell command fails
        vscode.env.openExternal(vscode.Uri.parse(url));
      }
    });
  }

  private updateUrlButtons(): void {
    if (!this._view) {
      return;
    }

    // Get the configured URL buttons
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    let urlButtons = config.get('urlButtons') as Array<{label: string, url: string}>;

    // Check for discovered web apps
    const discoveredApps = WebAppDiscovery.getInstance().getWebApps();

    urlButtons = discoveredApps;

    let automaticallyDiscoveredApps = discoveredApps.length > 0;

    // add buttons from config
    const configuredUrlButtons = config.get('urlButtons') as Array<{label: string, url: string}>;
    if (configuredUrlButtons != null && configuredUrlButtons.length > 0) {
      urlButtons = configuredUrlButtons;
      automaticallyDiscoveredApps = false;
    }

    // Send message to webview with updated button data
    this._view.webview.postMessage({
      command: 'updateButtons',
      buttons: urlButtons,
      automaticallyDiscovered: automaticallyDiscoveredApps
    });
  }

  private getWebviewContent(): string {
    // Get the configured URL buttons
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    let urlButtons = config.get('urlButtons') as Array<{label: string, url: string}>;

    // Check for discovered web apps
    const discoveredApps = WebAppDiscovery.getInstance().getWebApps();
    urlButtons = discoveredApps;
    let automaticallyDiscoveredApps = discoveredApps.length > 0;

    // add buttons from config
    const configuredUrlButtons = config.get('urlButtons') as Array<{label: string, url: string}>;
    if (configuredUrlButtons != null && configuredUrlButtons.length > 0) {
      urlButtons = configuredUrlButtons;
      automaticallyDiscoveredApps = false;
    }

    // Generate button HTML
    let urlButtonsHtml = '';
    if (urlButtons && urlButtons.length > 0) {
      urlButtonsHtml = `
        <div class="url-buttons">
          <h3>Application Links</h3>
          <div class="button-container">
            ${urlButtons.map(button => `
              <button class="url-button" data-url="${button.url}">
                ${button.label}
              </button>
            `).join('')}
          </div>
          ${automaticallyDiscoveredApps ? `
          <p class="configuration-hint">
            Automatically discovered application links.
          </p>
          ` : ''}
        </div>
      `;
    }

    // Get proper URI for external resources
    const webview = this._view?.webview;
    const logoPath = vscode.Uri.joinPath(this.context.extensionUri, 'media', 'sapmachine.svg');
    const logoUri = webview?.asWebviewUri(logoPath);

    // Get CSS and JS files
    const cssPath = vscode.Uri.joinPath(this.context.extensionUri, 'media', 'styles.css');
    const cssUri = webview?.asWebviewUri(cssPath);

    const jsPath = vscode.Uri.joinPath(this.context.extensionUri, 'media', 'webview.js');
    const jsUri = webview?.asWebviewUri(jsPath);

    return `
      <html>
      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cssUri}">
        <title>CAP in the Pocket</title>
      </head>
      <body>
        <div class="top-buttons-container">
          <button id="restartButton" class="large-button">
            (Re)Launch CAP App
          </button>
          <button id="recompileButton" class="large-button">
            Recompile CAP App
          </button>
        </div>
        <div class="extension-tagline">
          Experimental CAP-in-the-Pocket Extension
        </div>
        <pre id="output" data-vscode-context='{"webviewSection": "log"}'></pre>

        ${urlButtonsHtml}

        <div class="bubble-container" id="bubbleContainer"></div>
        <img src="${logoUri}" class="lurking-logo" id="sapLogo" alt="SAP Machine logo lurking" />

        <script>
          // Pass config values to the JavaScript file
          const maxMessageLimit = ${config.get('maxLogMessages') || 10000};
        </script>
        <script src="${jsUri}"></script>
      </body>
      </html>
    `;
  }

  private formatLogOutput(output: string): string {
    // Get configuration for log formatting
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const enableLogFiltering = config.get('enableLogFiltering') as boolean || true;

    // Remove ANSI escape sequences early to prevent them from appearing in the output
    const cleanOutput = output
      // Remove ANSI escape sequences (including color codes like [0m, [31m, etc.)
      .replace(/\x1b\[[0-9;]*m/g, '')
      // Remove any remaining ANSI escape sequences
      .replace(/\x1b\[[A-Za-z0-9;]*[a-zA-Z]/g, '');

    // Always wrap even plain text in divs for consistent styling
    if (!enableLogFiltering) {
      return `<div class="log-line plain-text">${this.escapeHtml(cleanOutput)}</div>`;
    }

    // Split output into lines for processing
    const lines = cleanOutput.split('\n');
    const formattedLines = lines.map(line => {
      // Skip empty lines but add a small spacer
      if (!line.trim()) {
        return '<div class="log-spacer"></div>';
      }

      // Handle Maven build output with 📦 prefix
      if (line.includes('📦')) {
        // Check for separator lines (with dashes)
        if (line.includes('--------')) {
          // Replace the dashed lines with a visual separator
          return '<div class="log-line maven-separator"></div>';
        } else if (line.includes('BUILD SUCCESS')) {
          return `<div class="log-line build-success">${this.escapeHtml(line)}</div>`;
        } else if (line.includes('BUILD FAILURE')) {
          return `<div class="log-line build-failure">${this.escapeHtml(line)}</div>`;
        } else if (line.includes('Reactor Summary')) {
          return `<div class="log-line maven-summary-header">${this.escapeHtml(line)}</div>`;
        } else if (line.match(/📦\s+[a-zA-Z0-9.:-]+\s+\.+\s+SUCCESS/)) {
          return `<div class="log-line maven-module-success">${this.escapeHtml(line)}</div>`;
        } else {
          return `<div class="log-line maven-output">${this.escapeHtml(line)}</div>`;
        }
      }

      // Special handling for separator lines (like dashes, equals signs)
      if (/^[-=*]{3,}$/.test(line.trim()) || line.trim().startsWith('----------')) {
        return `<div class="log-line separator-line">
                  <hr class="stylish-separator" />
                </div>`;
      }

      // Handle content lines of security message boxes
      if (/^\*\s+.*\s+\*$/.test(line.trim())) {
        // This is a content line in an asterisk security box
        // Extract the content between asterisks
        const content = line.trim().replace(/^\*\s*|\s*\*$/g, '');

        // Check if it's a warning line with exclamation marks
        if (content.includes('!!!')) {
          return `<div class="security-box-warning">${this.escapeHtml(content)}</div>`;
        } else {
          return `<div class="security-box-content">${this.escapeHtml(content)}</div>`;
        }
      }

      // Special handling for Maven's Spring Boot ASCII art
      if (line.includes('____') || line.includes('\\/') || line.includes('/\\\\') ||
          line.includes('( ( )') || line.includes("'  |") || line.includes(' ====')) {
        return `<div class="log-line ascii-art"><pre style="margin: 0; font-family: monospace; line-height: 1">${this.escapeHtml(line)}</pre></div>`;
      }

      // Spring Boot version line
      if (line.includes(':: Spring Boot ::')) {
        return `<div class="log-line spring-boot-version"><pre style="margin: 0; font-family: monospace">${this.escapeHtml(line)}</pre></div>`;
      }

      // Detect and format section headers with different bracket styles enclosed in dashes
      if (line.match(/-{5,}[<\[].+[>\]]-{5,}/) ||
          line.match(/={5,}[<\[].+[>\]]=+/) ||
          line.match(/\+-{5,}[<\[].+[>\]]-{5,}\+/)) {
        let headerText = line.replace(/-{5,}|={5,}|\+-{5,}/g, '').trim();
        return `<div class="log-line maven-section-header">${this.escapeHtml(headerText)}</div>`;
      }

      // Detect and format Maven section headers enclosed in dashes
      if (line.match(/-{10,}< .+ >-{10,}/)) {
        return `<div class="log-line maven-section-header">${this.highlightImportantStrings(line)}</div>`;
      }

      // Maven build summary separator
      if (/^\[INFO\] -{10,}$/.test(line)) {
        return `<div class="log-line maven-separator"><hr /></div>`;
      }

      // Maven build summary header
      if (line.includes('[INFO] Reactor Summary for ') || line.includes('[INFO] BUILD SUCCESS') || line.includes('[INFO] BUILD FAILURE')) {
        const buildResult = line.includes('SUCCESS') ?
          '<span class="build-success">BUILD SUCCESS</span>' :
          (line.includes('FAILURE') ? '<span class="build-failure">BUILD FAILURE</span>' : '');

        // Replace the [INFO] with a special icon for summary header
        const formattedLine = line.replace(/\[INFO\]/, '🏁');

        return `<div class="log-line maven-summary-header">${buildResult || formattedLine}</div>`;
      }

      // Maven module build result line - improved to handle dots properly
      if (line.match(/\[INFO\] [\w\s:.()-]+(\.+) (SUCCESS|FAILURE) \[\s*[0-9.]+\s*[a-z]\]/)) {
        // Extract module name, removing the dots completely
        const moduleMatch = line.match(/\[INFO\] ([\w\s:.()-]+)(\.+) (SUCCESS|FAILURE) \[\s*([0-9.]+)\s*([a-z])\]/);
      
        if (moduleMatch) {
          const moduleName = moduleMatch[1].trim();
          const status = moduleMatch[3];
          const time = moduleMatch[4];
          const timeUnit = moduleMatch[5];
      
          // Format with status icon
          const statusIcon = status === 'SUCCESS' ? '✅' : '❌';
          const statusClass = status === 'SUCCESS' ? 'module-success' : 'module-failure';
      
          // Keep everything on one line with proper spacing
          return `<div class="log-line maven-module-result ${statusClass}">
            <span class="module-name">${moduleName}</span>
            <span class="module-status">${statusIcon}</span>
            <span class="module-time">[${time} ${timeUnit}]</span>
          </div>`;
        }
      }
      

      // Maven build timing info - improved formatting
      if (line.match(/\[INFO\] (Total time:|Finished at:)/)) {
        // Check if it's a timestamp line
        if (line.includes('Finished at:')) {
          // Extract the ISO timestamp
          const timestampMatch = line.match(/Finished at: ([\d\-T:.+]+)/);
          if (timestampMatch && timestampMatch[1]) {
            try {
              // Parse the ISO timestamp
              const isoTimestamp = timestampMatch[1];
              const date = new Date(isoTimestamp);
              
              // Format date and time in a more readable way
              const formattedDate = date.toLocaleDateString(undefined, {
                year: 'numeric',
                month: 'short',
                day: 'numeric'
              });
              
              const formattedTime = date.toLocaleTimeString(undefined, {
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
              });
              
              // Return formatted time with nice styling
              return `<div class="log-line maven-timing-info">
                <span class="timing-icon">⏱️</span>
                <span class="timing-label">Finished on:</span>
                <span class="timing-date">${formattedDate}</span>
                <span class="timing-time">${formattedTime}</span>
              </div>`;
            } catch (e) {
              // Fallback if date parsing fails
              const formattedLine = line.replace(/\[INFO\]/, '⏱️');
              return `<div class="log-line maven-timing-info">${formattedLine}</div>`;
            }
          }
        }
        
        // Format other timing lines (like Total time:)
        const formattedLine = line.replace(/\[INFO\]/, '⏱️');
        return `<div class="log-line maven-timing-info">${formattedLine}</div>`;
      }

      // Format Spring Boot log lines - improved pattern matching
      if (line.match(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d+(Z|[+-]\d{2}:\d{2})\s+\w+\s+\d+\s+---/) ||
          line.match(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d+\s+\w+\s+\d+\s+---/)) { // Handle missing timezone
        // Extract just the time portion from ISO timestamp (HH:MM:SS)
        const timeMatch = line.match(/T(\d{2}:\d{2}:\d{2})/);
        const timeString = timeMatch ? timeMatch[1] : '';

        // Get log level and convert to emoji
        let logLevel = '💡';
        let logLevelClass = 'log-level-trace';
        if (line.includes(' INFO ')) {
          logLevel = '💡';
          logLevelClass = 'log-level-info';
        } else if (line.includes(' WARN ')) {
          logLevel = '⚠️';
          logLevelClass = 'log-level-warn';
        } else if (line.includes(' ERROR ')) {
          logLevel = '❌';
          logLevelClass = 'log-level-error';
        } else if (line.includes(' DEBUG ')) {
          logLevel = '🔍';
          logLevelClass = 'log-level-debug';
        }

        // Extract class/component name - shorter version
        const componentMatch = line.match(/([a-z]+\.)+([A-Z][a-zA-Z0-9_$]+)(\s+|\s*:)/);
        let component = '';
        if (componentMatch) {
          component = componentMatch[2];
          // Use HTML with a special class
          component = `<span class="component-name">${this.escapeHtml(component)}</span>`;
        }

        // Extract actual message
        const messageMatch = line.match(/\s+:\s+(.+)$/);
        const message = messageMatch ? messageMatch[1] : line;

        // Special handling for security message boxes with asterisk borders
        if (/^\*{3,}$/.test(message)) {
          // This is a top or bottom border of an asterisk box
          return `<div class="security-box-border"></div>`;
        }

        // Handle content lines of security message boxes
        if (/^\*\s+.*\s+\*$/.test(message.trim())) {
          // This is a content line in an asterisk security box
          // Extract the content between asterisks
          const content = message.replace(/^\*\s*|\s*\*$/g, '');

          // Check if it's a warning line with exclamation marks
          if (content.includes('!!!')) {
            return `<div class="security-box-warning">${this.escapeHtml(content)}</div>`;
          } else {
            return `<div class="security-box-content">${this.escapeHtml(content)}</div>`;
          }
        }

        // Check if the message ends with JSON content
        let formattedMessage = this.escapeHtml(message);
        let hasJSON = false;

        // More strict JSON detection - parse content between matching braces
        if (message.includes('{') && message.endsWith('}')) {
          const firstOpeningBrace = message.indexOf('{');
          const lastClosingBrace = message.lastIndexOf('}');

          // Make sure we're not parsing stack traces with braces
          // Stack traces typically have patterns like "ClassName.method(" or line numbers
          const potentialJson = message.substring(firstOpeningBrace, lastClosingBrace + 1);
          const looksLikeStackTrace = potentialJson.match(/\b[A-Za-z]+\.[A-Za-z]+\(/) ||
                                     potentialJson.match(/:[0-9]+\)$/);

          if (!looksLikeStackTrace) {
            try {
              const jsonString = message.substring(firstOpeningBrace, lastClosingBrace + 1);
              const jsonObject = JSON.parse(jsonString);
              // Format JSON with indentation
              const formattedJson = JSON.stringify(jsonObject, null, 2);

              // Apply syntax highlighting to the JSON
              const textBeforeJson = this.highlightImportantStrings(message.substring(0, firstOpeningBrace));
              const highlightedJson = this.syntaxHighlightJson(formattedJson);

              formattedMessage = `${textBeforeJson}<pre class="json-content">${highlightedJson}</pre>`;
              hasJSON = true;
            } catch (e) {
              // Not valid JSON, continue with normal formatting
            }
          }
        }

        if (!hasJSON) {
          formattedMessage = this.highlightImportantStrings(message);
        }

        // Format: [time] emoji component: message
        return `<div class="log-line spring-log"><span class="timestamp">[${timeString}]</span> <span class="log-level ${logLevelClass}">${logLevel}</span> ${component}: <span class="message">${formattedMessage}</span></div>`;
      }

      // Format Maven build output
      if (line.includes('[INFO]') || line.includes('[WARNING]') || line.includes('[ERROR]')) {
        let simplified = line;
        let logClass = "maven-line";
        let icon = "📦";
      
        // Determine log level and set appropriate icon
        if (line.includes('[WARNING]')) {
          icon = "⚠️";
          logClass = "maven-line maven-warning";
        } else if (line.includes('[ERROR]')) {
          icon = "❌";
          logClass = "maven-line maven-error";
        }
      
        // Replace Maven log indicators with emoji
        simplified = simplified
                          .replace(/\[INFO\]/g, icon)
                          .replace(/\[WARNING\]/g, icon)
                          .replace(/\[ERROR\]/g, icon);
      
        // Replace phase markers with Unicode arrows
        simplified = simplified
                          .replace(/>>>/g, '▶️')
                          .replace(/<<</g, '◀️');
      
        // Format Maven phase messages like "--- plugin:version:goal @ module ---"
        if (line.includes('---') && line.includes('@') && (line.includes(':compile') || 
            line.includes(':test') || line.includes(':package') || line.includes(':resources'))) {
          
          // Extract the components from the phase message
          const phaseMatch = line.match(/--- ([^:]+):([^:]+):([^ ]+) [^@]+@ ([^ ]+) ---/);
          
          if (phaseMatch) {
            const plugin = phaseMatch[1];
            const version = phaseMatch[2];
            const goal = phaseMatch[3];
            const module = phaseMatch[4];
            
            return `<div class="log-line maven-phase">
              <span class="maven-plugin">${plugin}</span>
              <span class="maven-goal">${goal}</span>
              <span class="maven-version">${version}</span>
              <span class="maven-module">@${module}</span>
            </div>`;
          }
        }
      
        // Format standard Maven log lines
        const inner = this.highlightImportantStrings(simplified);
        return `<div class="log-line ${logClass}">${inner}</div>`;
      }

      // Detect and format Maven section headers enclosed in dashes
      if (line.match(/-{10,}< .+ >-{10,}/)) {
        return `<div class="log-line maven-section-header">${this.highlightImportantStrings(line)}</div>`;
      }

      // Default formatting for unmatched lines
      return `<div class="log-line plain-text">${this.highlightImportantStrings(line)}</div>`;
    });

    return formattedLines.join('');
  }

  // Helper method to safely escape HTML characters and remove ANSI escape sequences
  private escapeHtml(unsafe: string): string {
    return unsafe
        // Remove ANSI escape sequences (including color codes like [0m, [31m, etc.)
        .replace(/\x1b\[[0-9;]*m/g, '')
        // Remove any remaining ANSI escape sequences
        .replace(/\x1b\[[A-Za-z0-9;]*[a-zA-Z]/g, '')
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot")
        .replace(/'/g, "&#039;");
  }

  // Helper method to syntax highlight JSON
  private syntaxHighlightJson(json: string): string {
    // Already escaped JSON string
    return json
      .replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, (match) => {
        let cls = 'json-number'; // Default class for numbers

        if (/^"/.test(match)) {
          if (/:$/.test(match)) {
            cls = 'json-key'; // JSON keys
          } else {
            cls = 'json-string'; // JSON strings
          }
        } else if (/true|false/.test(match)) {
          cls = 'json-boolean'; // JSON booleans
        } else if (/null/.test(match)) {
          cls = 'json-null'; // JSON null values
        }

        return `<span class="${cls}">${this.escapeHtml(match)}</span>`;
      })
      .replace(/\n/g, '<br>')
      .replace(/\s{2}/g, '&nbsp;&nbsp;'); // Keep indentation
  }

  /**
   * Highlights important strings and patterns in log messages
   * with a token-based approach to prevent nesting
   */
    /**
   * Highlights important strings and patterns in log messages
   * with a token-based approach to prevent nesting
   */
    private highlightImportantStrings(text: string): string {
      // Define patterns to highlight with their corresponding CSS classes
      const patterns = [
        // Quoted strings (double quotes)
        {
          regex: /"([^"]*)"/g,
          cssClass: "json-string"
        },
        // Quoted strings (single quotes)
        {
          regex: /'([^']*)'/g,
          cssClass: "json-string"
        },
        // Progress indicators like [1/4], [2/2], etc.
        {
          regex: /\[(\d+)\/(\d+)\]/g,
          cssClass: "highlight-progress"
        },
        // URLs
        {
          regex: /(\/[a-zA-z_]+[?][^ ]+)/g,
          cssClass: "highlight-url"
        },
        {
          regex: /(https?:\/\/[^\s]+)/g,
          cssClass: "highlight-url"
        },
        // Error words
        {
          regex: /\b(error|exception|failed|failure|cannot|unable to|invalid)\b/gi,
          cssClass: "error-message"
        },
        // Warning words
        {
          regex: /\b(warning|warn|deprecated|caution)\b/gi,
          cssClass: "warning-message"
        },
        // Success indicators
        {
          regex: /\b(success|successful|completed|ready|started|listening on port)\b/gi,
          cssClass: "success-message"
        },
        // Important keywords
        {
          regex: /\b(created|deleted|updated|modified|initialized|generated)\b/gi,
          cssClass: "highlight-action"
        },
        // File paths (simplified to avoid regex catastrophic backtracking)
        {
          regex: /(\/|\.\.?\/)([\w\-\.\/]+)/g,
          cssClass: "highlight-path"
        },
        {
          regex: /target\/[a-zA-z.0-9]+/g,
          cssClass: "highlight-path"
        },
        // Windows file paths
        {
          regex: /[a-zA-Z]:\\[\w\\.-]+/g,
          cssClass: "highlight-path"
        },
        // Port numbers
        {
          regex: /\b(port|PORT):\s*(\d+)\b/g,
          cssClass: "highlight-port"
        },
        // component names
        {
          regex: /([a-z]+\.)+([A-Z][a-zA-Z0-9_$]+)/g,
          cssClass: "highlight-classname"
        },
        // component names without package but with $
        {
          regex: /(([A-Z][a-zA-Z0-9_$]+)\$([A-Z][a-zA-Z0-9_$]+)+)/g,
          cssClass: "highlight-classname"
        },
        {
          regex: /([A-Z]+[a-z][a-z]+([A-Z][a-zA-Z0-9_$]+)+)/g,
          cssClass: "highlight-classname"
        },
        // numbers
        {
          regex: /\b\d+(\.\d+)?\b/g,
          cssClass: "json-number"
        },
      ];

      // Find all matches for all patterns
      interface Match {
        start: number;
        end: number;
        text: string;
        cssClass: string;
      }

      const matches: Match[] = [];

      // Collect all matches from all patterns
      patterns.forEach(pattern => {
        const regex = pattern.regex;

        // Need to clone the regex to reset lastIndex
        const clonedRegex = new RegExp(regex.source, regex.flags);
        let match;

        while ((match = clonedRegex.exec(text)) !== null) {
          // Ensure we're not adding empty matches
          if (match[0].length > 0) {
            matches.push({
              start: match.index,
              end: match.index + match[0].length,
              text: match[0],
              cssClass: pattern.cssClass
            });
          }

          // Prevent infinite loop with zero-width matches
          if (match.index === clonedRegex.lastIndex) {
            clonedRegex.lastIndex++;
          }
        }
      });

      // Sort matches by start position
      matches.sort((a, b) => a.start - b.start);

      // Filter out overlapping matches - keep only non-overlapping ones
      const filteredMatches: Match[] = [];
      let lastEnd = -1;

      for (const match of matches) {
        if (match.start >= lastEnd) {
          // This match doesn't overlap with any previously kept match
          filteredMatches.push(match);
          lastEnd = match.end;
        }
      }

      // Build the result string by proceeding through the original text
      // and inserting spans for the matches
      let result = '';
      let currentPos = 0;

      for (const match of filteredMatches) {
        // Add the text before this match
        if (match.start > currentPos) {
          result += this.escapeHtml(text.substring(currentPos, match.start));
        }

        // Add the highlighted match
        result += `<span class="${match.cssClass}">${this.escapeHtml(match.text)}</span>`;

        // Update the current position
        currentPos = match.end;
      }

      // Add any remaining text after the last match
      if (currentPos < text.length) {
        result += this.escapeHtml(text.substring(currentPos));
      }

      return result;
    }

  // Add a method to clear logs
  public clearLogs(webview: vscode.Webview): void {
    // Clear the log history
    this.logHistory = '';
    this.rawLogHistory = [];
    
    // Update the webview
    webview.postMessage({ command: 'clearOutput' });
  }

  // Add a new method to handle kill and clear
  public killAndClear(webview: vscode.Webview): void {
    // Get workspace folder - use the first one if multiple are open
    const workspaceFolders = vscode.workspace.workspaceFolders;
    if (!workspaceFolders || workspaceFolders.length === 0) {
      webview.postMessage({ log: "\n❌ Error: No workspace folder is open. Please open a CAP project folder first.\n" });
      return;
    }

    // Use the first workspace folder as the project root
    const projectRoot = workspaceFolders[0].uri.fsPath;

    // Get configured commands from settings
    const config = vscode.workspace.getConfiguration('cap-in-the-pocket');
    const port = config.get('serverPort') as number || 4004;
    const killPortCommand = (config.get('killPortCommand') as string).replace("PORT", port.toString());

    // Execute the shell command in the project root directory
    const options = {
      cwd: projectRoot,
      shell: true,
      env: {
        ...process.env
      }
    };

    // First log the action
    this.logToWebview(webview, `\n⏹️ Stopping processes on port ${port}...\n`);

    // Kill any processes using the configured port
    const killPortProcess = require('child_process').spawn(
      killPortCommand,
      [],
      options
    );

    killPortProcess.on('close', (code: number) => {
      const closeMessage = code === 0
        ? `✅ Stopped all processes using port ${port}\n\n`
        : `ℹ️ No processes were found using port ${port}\n\n`;

      this.logToWebview(webview, closeMessage);
      
      // Clear logs after killing the processes
      setTimeout(() => this.clearLogs(webview), 500);
    });

    // Handle errors from the kill port process
    killPortProcess.on('error', (err: Error) => {
      webview.postMessage({ log: `\n⚠️ Warning: Failed to kill processes on port ${port}: ${err.message}\n` });
    });
  }
}

export function deactivate() {
  // Stop the web app discovery scanning
  WebAppDiscovery.getInstance().stopScanning();
}
