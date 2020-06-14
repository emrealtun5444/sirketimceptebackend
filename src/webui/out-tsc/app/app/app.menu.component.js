import { __decorate } from "tslib";
import { Component } from '@angular/core';
let AppMenuComponent = class AppMenuComponent {
    constructor(app, appStore) {
        this.app = app;
        this.appStore = appStore;
    }
    ngOnInit() {
        this.model = [
            { label: 'Dashboard', icon: 'fa fa-fw fa-home', routerLink: ['/'] },
            {
                label: this.appStore.translate.instant('menu.stok.yonetimi'), icon: 'fa fa-fw fa-bars', badge: 4, badgeStyleClass: 'green-badge',
                items: [
                    { label: 'Horizontal', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'horizontal' },
                    { label: 'Static', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'static' },
                    { label: 'Overlay', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'overlay' },
                    { label: 'Popup', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'popup' }
                ]
            },
            {
                label: 'Menu', icon: 'fa fa-fw fa-bars', badge: 4, badgeStyleClass: 'green-badge',
                items: [
                    { label: 'Horizontal', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'horizontal' },
                    { label: 'Static', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'static' },
                    { label: 'Overlay', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'overlay' },
                    { label: 'Popup', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'popup' }
                ]
            },
            {
                label: 'Layout Colors', icon: 'fa fa-fw fa-magic',
                items: [
                    {
                        label: 'Flat',
                        icon: 'fa fa-fw fa-circle',
                        badge: 7, badgeStyleClass: 'blue-badge',
                        items: [
                            { label: 'Dark', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => { this.changeLayout('dark'); } },
                            { label: 'Turquoise', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => { this.changeLayout('turquoise'); } },
                            { label: 'Green', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('green'); } },
                            { label: 'Blue', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => { this.changeLayout('blue'); } },
                            { label: 'Rose', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('rose'); } },
                            { label: 'Teal', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('teal'); } },
                            { label: 'Blue Grey', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => { this.changeLayout('bluegrey'); } }
                        ]
                    },
                    {
                        label: 'Special',
                        icon: 'fa fa-fw fa-fire',
                        badge: 8, badgeStyleClass: 'blue-badge',
                        items: [
                            { label: 'Cosmic', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => { this.changeLayout('cosmic'); } },
                            { label: 'Lawrencium', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('lawrencium'); } },
                            { label: 'Couple', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('couple'); } },
                            { label: 'Stellar', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('stellar'); } },
                            { label: 'Beach', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('beach'); } },
                            { label: 'Flow', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => { this.changeLayout('flow'); } },
                            { label: 'Fly', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('fly'); } },
                            { label: 'Nepal', icon: 'fa fa-fw fa-paint-brush', command: (event) => { this.changeLayout('nepal'); } }
                        ]
                    }
                ]
            },
            {
                label: 'Themes', icon: 'fa fa-fw fa-paint-brush', badge: 7, badgeStyleClass: 'amber-badge',
                items: [
                    { label: 'Green Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('green'); } },
                    { label: 'Teal Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('teal'); } },
                    { label: 'Blue Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('blue'); } },
                    { label: 'Amber Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('amber'); } },
                    { label: 'Purple Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('purple'); } },
                    { label: 'Turquoise Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('turquoise'); } },
                    { label: 'Blue Grey Theme', icon: 'fa fa-fw fa-diamond', command: (event) => { this.changeTheme('bluegrey'); } },
                ]
            },
            {
                label: 'Components', icon: 'fa fa-fw fa-sitemap', routerLink: ['/components'],
                items: [
                    { label: 'Sample Page', icon: 'fa fa-fw fa-columns', routerLink: ['/components/sample'] },
                    { label: 'Forms', icon: 'fa fa-fw fa-code', routerLink: ['/components/forms'] },
                    { label: 'Data', icon: 'fa fa-fw fa-table', routerLink: ['/components/data'] },
                    { label: 'Panels', icon: 'fa fa-fw fa-list-alt', routerLink: ['/components/panels'] },
                    { label: 'Overlays', icon: 'fa fa-fw fa-square', routerLink: ['/components/overlays'] },
                    { label: 'Menus', icon: 'fa fa-fw fa-minus-square-o', routerLink: ['/components/menus'] },
                    { label: 'Messages', icon: 'fa fa-fw fa-circle-o-notch', routerLink: ['/components/messages'] },
                    { label: 'Charts', icon: 'fa fa-fw fa-area-chart', routerLink: ['/components/charts'] },
                    { label: 'File', icon: 'fa fa-fw fa-arrow-circle-o-up', routerLink: ['/components/file'] },
                    { label: 'Misc', icon: 'fa fa-fw fa-user-secret', routerLink: ['/components/misc'] }
                ]
            },
            {
                label: 'Template Pages', icon: 'fa fa-fw fa-life-saver', routerLink: ['/pages'],
                items: [
                    { label: 'Empty Page', icon: 'fa fa-fw fa-square-o', routerLink: ['/pages/empty'] },
                    { label: 'Landing Page', icon: 'fa fa-fw fa-globe', url: 'assets/pages/landing.html', target: '_blank' },
                    { label: 'Login Page', icon: 'fa fa-fw fa-sign-in', url: 'assets/pages/login.html', target: '_blank' },
                    { label: 'Error Page', icon: 'fa fa-fw fa-exclamation-circle', url: 'assets/pages/error.html', target: '_blank' },
                    { label: '404 Page', icon: 'fa fa-fw fa-times', url: 'assets/pages/404.html', target: '_blank' },
                    { label: 'Access Denied Page', icon: 'fa fa-fw fa-exclamation-triangle',
                        url: 'assets/pages/access.html', target: '_blank' }
                ]
            },
            {
                label: 'Menu Hierarchy', icon: 'fa fa-fw fa-gg',
                items: [
                    {
                        label: 'Submenu 1', icon: 'fa fa-fw fa-sign-in',
                        items: [
                            {
                                label: 'Submenu 1.1', icon: 'fa fa-fw fa-sign-in',
                                items: [
                                    { label: 'Submenu 1.1.1', icon: 'fa fa-fw fa-sign-in' },
                                    { label: 'Submenu 1.1.2', icon: 'fa fa-fw fa-sign-in' },
                                    { label: 'Submenu 1.1.3', icon: 'fa fa-fw fa-sign-in' },
                                ]
                            },
                            {
                                label: 'Submenu 1.2', icon: 'fa fa-fw fa-sign-in',
                                items: [
                                    { label: 'Submenu 1.2.1', icon: 'fa fa-fw fa-sign-in' },
                                    { label: 'Submenu 1.2.2', icon: 'fa fa-fw fa-sign-in' }
                                ]
                            },
                        ]
                    },
                    {
                        label: 'Submenu 2', icon: 'fa fa-fw fa-sign-in',
                        items: [
                            {
                                label: 'Submenu 2.1', icon: 'fa fa-fw fa-sign-in',
                                items: [
                                    { label: 'Submenu 2.1.1', icon: 'fa fa-fw fa-sign-in' },
                                    { label: 'Submenu 2.1.2', icon: 'fa fa-fw fa-sign-in' },
                                    { label: 'Submenu 2.1.3', icon: 'fa fa-fw fa-sign-in' },
                                ]
                            },
                            {
                                label: 'Submenu 2.2', icon: 'fa fa-fw fa-sign-in',
                                items: [
                                    { label: 'Submenu 2.2.1', icon: 'fa fa-fw fa-sign-in' },
                                    { label: 'Submenu 2.2.2', icon: 'fa fa-fw fa-sign-in' }
                                ]
                            },
                        ]
                    }
                ]
            },
            {
                label: 'Documentation', icon: 'fa fa-fw fa-book',
                items: [
                    { label: 'Setup', icon: 'fa fa-fw fa-gear', routerLink: ['/documentation'] },
                    { label: 'Utils', icon: 'fa fa-fw fa-wrench', routerLink: ['/utils'] }
                ]
            }
        ];
    }
    changeTheme(theme) {
        const themeLink = document.getElementById('theme-css');
        const href = 'assets/theme/theme-' + theme + '.css';
        this.replaceLink(themeLink, href);
    }
    changeLayout(layout) {
        const layoutLink = document.getElementById('layout-css');
        const href = 'assets/layout/css/layout-' + layout + '.css';
        this.replaceLink(layoutLink, href);
    }
    isIE() {
        return /(MSIE|Trident\/|Edge\/)/i.test(window.navigator.userAgent);
    }
    replaceLink(linkElement, href) {
        if (this.isIE()) {
            linkElement.setAttribute('href', href);
        }
        else {
            const id = linkElement.getAttribute('id');
            const cloneLinkElement = linkElement.cloneNode(true);
            cloneLinkElement.setAttribute('href', href);
            cloneLinkElement.setAttribute('id', id + '-clone');
            linkElement.parentNode.insertBefore(cloneLinkElement, linkElement.nextSibling);
            cloneLinkElement.addEventListener('load', () => {
                linkElement.remove();
                cloneLinkElement.setAttribute('id', id);
            });
        }
    }
    onMenuClick() {
        this.app.onMenuClick();
    }
};
AppMenuComponent = __decorate([
    Component({
        selector: 'app-menu',
        templateUrl: './app.menu.component.html'
    })
], AppMenuComponent);
export { AppMenuComponent };
//# sourceMappingURL=app.menu.component.js.map