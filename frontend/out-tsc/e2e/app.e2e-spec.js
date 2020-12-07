"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var app_po_1 = require("./app.po");
describe('Verona App', function () {
    var page;
    beforeEach(function () {
        page = new app_po_1.VeronaPage();
    });
    it('should display welcome message', function () {
        page.navigateTo();
        expect(page.getTitleText()).toEqual('Welcome to Verona!');
    });
});
//# sourceMappingURL=app.e2e-spec.js.map