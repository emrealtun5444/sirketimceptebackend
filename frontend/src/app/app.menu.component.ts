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
        icon: 'fa fa-fw fa-bars',
        badge: 1,
        badgeStyleClass: 'green-badge',
        items: [
          {
            label: this.appStore.translate.instant('menu.kullanici.ayarlari'),
            icon: 'fa fa-fw fa-columns',
            authorization: 'USER_MENU',
            routerLink: ['/user']
          },
          {
            label: this.appStore.translate.instant('menu.role.ayarlari'),
            icon: 'fa fa-fw fa-columns',
            authorization: 'ROLE_MENU',
            routerLink: ['/role']
          },
        ]
      },
      {
        label: this.appStore.translate.instant('menu.stok.yonetimi'),
        authorization: 'STOK_MENU',
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
        authorization: 'CARI_MENU',
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
        authorization: 'FATURA_MENU',
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
        authorization: 'PAZARYERI_MENU',
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
