import {Component, OnInit} from '@angular/core';
import {AppMainComponent} from './app.main.component';
import {AppStore} from './shared/app.store';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public app: AppMainComponent,
                private appStore: AppStore) {
    }

    ngOnInit() {
        this.model = [
            {label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/']},
            {
                label: this.appStore.translate.instant('menu.system.yonetimi'),
                authorization: 'SYSTEM_MENU',
                icon: 'pi pi-fw pi-briefcase',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.kullanici.ayarlari'),
                        icon: 'pi pi-fw pi-bars',
                        authorization: 'USER_MENU',
                        routerLink: ['/user']
                    },
                    {
                        label: this.appStore.translate.instant('menu.role.ayarlari'),
                        icon: 'pi pi-fw pi-bars',
                        authorization: 'ROLE_MENU',
                        routerLink: ['/role']
                    },
                    {
                        label: this.appStore.translate.instant('menu.company.ayarlari'),
                        icon: 'pi pi-fw pi-bars',
                        authorization: 'COMPANY_MENU',
                        routerLink: ['/company']
                    },
                ]
            },
            {
                label: this.appStore.translate.instant('menu.stok.yonetimi'),
                authorization: 'STOK_MENU',
                icon: 'pi pi-fw pi-th-large',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.stok.sorgulama'),
                        icon: 'pi pi-fw pi-bars',
                        routerLink: ['/stokKart']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.cari.yonetimi'),
                authorization: 'CARI_MENU',
                icon: 'pi pi-fw pi-id-card',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.cari.sorgulama'),
                        icon: 'pi pi-fw pi-bars',
                        routerLink: ['/cariKart']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.fatura.yonetimi'),
                authorization: 'FATURA_MENU',
                icon: 'pi pi-fw pi-book',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.fatura.sorgulama'),
                        icon: 'pi pi-fw pi-bars',
                        routerLink: ['/fatura']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.tahsilat.yonetimi'),
                authorization: 'TAHSILAT_MENU',
                icon: 'pi pi-fw pi-money-bill',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.tahsilat.sorgulama'),
                        icon: 'pi pi-fw pi-bars',
                        routerLink: ['/tahsilat']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.siparis.yonetimi'),
                authorization: 'SIPARIS_MENU',
                icon: 'pi pi-fw pi-shopping-cart',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.siparis.sorgulama'),
                        icon: 'pi pi-fw pi-bars',
                        routerLink: ['/siparis']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.rapor.yonetimi'),
                authorization: 'REPORT_MENU',
                icon: 'pi pi-fw pi-chart-bar',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.rapor.performans'),
                        icon: 'pi pi-fw pi-bars',
                        authorization: 'PERFORMANS_REPORT_MENU',
                        routerLink: ['/report/performans']
                    }
                ]
            },
            {
                label: this.appStore.translate.instant('menu.pazaryeri.yonetimi'),
                authorization: 'PAZARYERI_MENU',
                icon: 'pi pi-fw pi-slack',
                badge: 1,
                badgeStyleClass: 'blue-badge',
                items: [
                    {
                        label: this.appStore.translate.instant('menu.pazaryeri.conf'),
                        icon: 'pi pi-fw pi-bars',
                        routerLink: ['/marketPlace']
                    }
                ]
            },
            {
                label: 'Pages', icon: 'pi pi-fw pi-briefcase', routerLink: ['/pages'],
                authorization: 'SYSTEM_MENU',
                items: [
                    {label: 'Landing', icon: 'pi pi-fw pi-globe', url: 'assets/pages/landing.html', target: '_blank'},
                    {label: 'Login', icon: 'pi pi-fw pi-sign-in', routerLink: ['/login']},
                    {label: 'Invoice', icon: 'pi pi-fw pi-dollar', routerLink: ['/pages/invoice']},
                    {label: 'Help', icon: 'pi pi-fw pi-question-circle', routerLink: ['/pages/help']},
                    {label: 'Error', icon: 'pi pi-fw pi-times-circle', routerLink: ['/error']},
                    {label: 'Not Found', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['/notfound']},
                    {label: 'Access Denied', icon: 'pi pi-fw pi-lock', routerLink: ['/access']},
                ]
            }
        ];
    }
}
