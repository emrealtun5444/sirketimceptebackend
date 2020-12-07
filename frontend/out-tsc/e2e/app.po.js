"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var protractor_1 = require("protractor");
var VeronaPage = /** @class */ (function () {
    function VeronaPage() {
    }
    VeronaPage.prototype.navigateTo = function () {
        return protractor_1.browser.get(protractor_1.browser.baseUrl);
    };
    VeronaPage.prototype.getTitleText = function () {
        return protractor_1.element(protractor_1.by.css('app-root h1')).getText();
    };
    return VeronaPage;
}());
exports.VeronaPage = VeronaPage;
//# sourceMappingURL=app.po.js.map