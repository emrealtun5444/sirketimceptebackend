import {Component, OnInit} from '@angular/core';
import {AppComponent} from './app.component';
import {AppStore} from "./shared/app.store";

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public app: AppComponent,
                private appStore: AppStore) {
    }

    ngOnInit() {
        this.model = [
            {label: 'Dashboard', icon: 'fa fa-fw fa-home', routerLink: ['/']},
            {
                label: this.appStore.translate.instant('menu.stok.yonetimi'),
                role: 'ROLE_ADMIN,ROLE_MODERATOR',
                icon: 'fa fa-fw fa-bars',
                badge: 1,
                badgeStyleClass: 'green-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.stok.sorgulama'),
                        icon: 'fa fa-fw fa-columns',
                        routerLink: ['/stokKart']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.cari.yonetimi'),
                role: 'ROLE_ADMIN,ROLE_MODERATOR',
                icon: 'fa fa-fw fa-bars',
                badge: 1,
                badgeStyleClass: 'green-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.cari.sorgulama'),
                        icon: 'fa fa-fw fa-columns',
                        routerLink: ['/cariKart']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.fatura.yonetimi'),
                role: 'ROLE_ADMIN,ROLE_MODERATOR',
                icon: 'fa fa-fw fa-bars',
                badge: 1,
                badgeStyleClass: 'green-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.fatura.sorgulama'),
                        icon: 'fa fa-fw fa-columns',
                        routerLink: ['/fatura']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.pazaryeri.yonetimi'),
                role: 'ROLE_ADMIN,ROLE_MODERATOR',
                icon: 'fa fa-fw fa-bars',
                badge: 1,
                badgeStyleClass: 'green-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.pazaryeri.conf'),
                        icon: 'fa fa-fw fa-columns',
                        routerLink: ['/marketPlace']
                    }
                ]
            },
            {
                label: 'Menu', icon: 'fa fa-fw fa-bars', badge: 4, badgeStyleClass: 'green-badge', role: 'ROLE_ADMIN',
                items: [
                    {label: 'Horizontal', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'horizontal'},
                    {label: 'Static', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'static'},
                    {label: 'Overlay', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'overlay'},
                    {label: 'Popup', icon: 'fa fa-fw fa-bars', command: event => this.app.menuMode = 'popup'}
                ]
            },
            {
                label: 'Layout Colors', icon: 'fa fa-fw fa-magic', role: 'ROLE_ADMIN',
                items: [
                    {
                        label: 'Flat',
                        icon: 'fa fa-fw fa-circle',
                        badge: 7, badgeStyleClass: 'blue-badge',
                        items: [
                            {
                                label: 'Dark', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => {
                                    this.changeLayout('dark');
                                }
                            },
                            {
                                label: 'Turquoise', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => {
                                    this.changeLayout('turquoise');
                                }
                            },
                            {
                                label: 'Green', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('green');
                                }
                            },
                            {
                                label: 'Blue', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => {
                                    this.changeLayout('blue');
                                }
                            },
                            {
                                label: 'Rose', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('rose');
                                }
                            },
                            {
                                label: 'Teal', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('teal');
                                }
                            },
                            {
                                label: 'Blue Grey', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => {
                                    this.changeLayout('bluegrey');
                                }
                            }
                        ]
                    },
                    {
                        label: 'Special',
                        icon: 'fa fa-fw fa-fire',
                        badge: 8, badgeStyleClass: 'blue-badge',
                        items: [
                            {
                                label: 'Cosmic', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => {
                                    this.changeLayout('cosmic');
                                }
                            },
                            {
                                label: 'Lawrencium', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('lawrencium');
                                }
                            },
                            {
                                label: 'Couple', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('couple');
                                }
                            },
                            {
                                label: 'Stellar', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('stellar');
                                }
                            },
                            {
                                label: 'Beach', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('beach');
                                }
                            },
                            {
                                label: 'Flow', icon: 'fa fa-fw fa-paint-brush',
                                command: (event) => {
                                    this.changeLayout('flow');
                                }
                            },
                            {
                                label: 'Fly', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('fly');
                                }
                            },
                            {
                                label: 'Nepal', icon: 'fa fa-fw fa-paint-brush', command: (event) => {
                                    this.changeLayout('nepal');
                                }
                            }
                        ]
                    }
                ]
            },
            {
                label: 'Themes', icon: 'fa fa-fw fa-paint-brush', badge: 7, badgeStyleClass: 'amber-badge', role: 'ROLE_ADMIN',
                items: [
                    {
                        label: 'Green Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('green');
                        }
                    },
                    {
                        label: 'Teal Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('teal');
                        }
                    },
                    {
                        label: 'Blue Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('blue');
                        }
                    },
                    {
                        label: 'Amber Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('amber');
                        }
                    },
                    {
                        label: 'Purple Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('purple');
                        }
                    },
                    {
                        label: 'Turquoise Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('turquoise');
                        }
                    },
                    {
                        label: 'Blue Grey Theme', icon: 'fa fa-fw fa-diamond', command: (event) => {
                            this.changeTheme('bluegrey');
                        }
                    },
                ]
            }
        ];
    }

    changeTheme(theme) {
        const themeLink: HTMLLinkElement = document.getElementById('theme-css') as HTMLLinkElement;
        const href = 'assets/theme/theme-' + theme + '.css';

        this.replaceLink(themeLink, href);
    }

    changeLayout(layout) {
        const layoutLink: HTMLLinkElement = document.getElementById('layout-css') as HTMLLinkElement;
        const href = 'assets/layout/css/layout-' + layout + '.css';

        this.replaceLink(layoutLink, href);
    }

    isIE() {
        return /(MSIE|Trident\/|Edge\/)/i.test(window.navigator.userAgent);
    }

    replaceLink(linkElement, href) {
        if (this.isIE()) {
            linkElement.setAttribute('href', href);
        } else {
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
}
