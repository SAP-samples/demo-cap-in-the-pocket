#!/bin/bash
echo "(Re)Installing the VSCode plugin"
wget https://github.com/parttimenerd/cap-in-the-pocket-extension/releases/download/snapshot/cap-in-the-pocket-0.0.1.vsix .
code --install-extension cap-in-the-pocket-0.0.1.vsix --no-sandbox --user-data-dir ~