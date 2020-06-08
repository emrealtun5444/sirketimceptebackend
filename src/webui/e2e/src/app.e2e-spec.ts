import {VeronaPage} from './app.po';

describe('Verona App', () => {
    let page: VeronaPage;

    beforeEach(() => {
        page = new VeronaPage();
    });

    it('should display welcome message', () => {
        page.navigateTo();
        expect(page.getTitleText()).toEqual('Welcome to Verona!');
    });
});
