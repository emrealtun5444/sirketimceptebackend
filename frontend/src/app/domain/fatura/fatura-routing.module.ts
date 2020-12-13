import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../shared/auth/AuthGuard";
import {FaturaComponent} from "./component/sorgula/fatura.component";
import {FaturaDetayComponent} from "./component/detay/fatura-detay.component";

const routes: Routes = [
    {
        path: '',
        component: FaturaComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'fatura_sorgula',
            authorization: 'FATURA_MENU'
        }
    },
    {
        path: ':id/detay',
        component: FaturaDetayComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'fatura_detay',
            authorization: 'FATURA_MENU'
        }
    },
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class FaturaRoutingModule {
}
