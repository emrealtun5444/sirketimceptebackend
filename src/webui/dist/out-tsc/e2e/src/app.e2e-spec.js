import { VeronaPage } from './app.po';
describe('Verona App', () => {
    let page;
    beforeEach(() => {
        page = new VeronaPage();
    });
    it('should display welcome message', () => {
        page.navigateTo();
        expect(page.getTitleText()).toEqual('Welcome to Verona!');
    });
});
//# sourceMappingURL=app.e2e-spec.js.map