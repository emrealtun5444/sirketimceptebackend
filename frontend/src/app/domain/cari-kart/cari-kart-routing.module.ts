import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../shared/auth/AuthGuard";
import {CariKartComponent} from "./component/sorgula/cari-kart.component";

const routes: Routes = [
    {
        path: '',
        component: CariKartComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'cari_kart_sorgula',
          authorization: 'CARI_MENU'
        }
    }
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class CariKartRoutingModule {
}
