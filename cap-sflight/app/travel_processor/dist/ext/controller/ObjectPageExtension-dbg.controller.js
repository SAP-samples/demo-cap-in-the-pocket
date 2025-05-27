sap.ui.define(["sap/ui/core/mvc/ControllerExtension"], function (ControllerExtension) {
  "use strict";

  /**
   * @namespace sap.fe.cap.travel.ext.controller
   * @controller
   */
  const ObjectPageExtension = ControllerExtension.extend("sap.fe.cap.travel.ext.controller.ObjectPageExtension", {
    overrides: {
      /**
       * Called when a controller is instantiated and its View controls (if available) are already created.
       * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
       * @memberOf sap.fe.cap.travel.ext.controller.ObjectPageExtension
       */
      onInit() {
        // you can access the Fiori elements extensionAPI via this.base.getExtensionAPI
        //const model = this.base.getExtensionAPI().getModel();
      },
      editFlow: {
        onBeforeSave() {
          const context = this.base.getExtensionAPI().getBindingContext();
          if (!context.getProperty("GoGreen")) {
            //void intentionally discards returned floating promise
            return new Promise((resolve, reject) => {
              void this.openDialog(resolve, reject, context);
            });
          }
          return undefined;
        }
      }
    },
    openDialog: async function _openDialog(resolve, reject, context) {
      //try catch ensures errors in floating promises are handled properly
      try {
        let approveDialog = await this.base.getExtensionAPI().loadFragment({
          id: "myFragment",
          initialBindingContext: context,
          name: "sap.fe.cap.travel.ext.fragment.Trees4Tickets"
        });
        //Dialog Save button
        approveDialog.getBeginButton().attachPress(function () {
          approveDialog.close().destroy();
          resolve(null);
        });
        //Dialog Cancel button
        approveDialog.getEndButton().attachPress(function () {
          approveDialog.close().destroy();
          reject(null);
        });
        //consider dialog closing with ESC
        approveDialog.attachAfterClose(function () {
          approveDialog.destroy();
          reject(null);
        });
        approveDialog.open();
      } catch (error) {
        reject(null);
      }
    }
  });
  return ObjectPageExtension;
});
//# sourceMappingURL=ObjectPageExtension-dbg.controller.js.map
