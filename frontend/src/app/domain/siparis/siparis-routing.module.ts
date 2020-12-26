import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../shared/auth/AuthGuard";
import {SiparisComponent} from "./component/sorgula/siparis.component";
import {SiparisDetayComponent} from "./component/detay/siparis-detay.component";

const routes: Routes = [
  {
    path: '',
    component: SiparisComponent,
    canActivate: [AuthGuard],
    data: {
      id: 'siparis_sorgula',
      authorization: 'SIPARIS_MENU'
    }
  },
  {
    path: ':id/detay',
    component: SiparisDetayComponent,
    canActivate: [AuthGuard],
    data: {
      id: 'siparis_detay',
      authorization: 'SIPARIS_MENU'
    }
  },
];

@NgModule({

  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class SiparisRoutingModule {
}
