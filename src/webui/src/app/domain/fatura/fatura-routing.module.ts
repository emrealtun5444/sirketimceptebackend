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
            role: 'ROLE_ADMIN,ROLE_MODERATOR'
        }
    },
    {
        path: ':id/detay',
        component: FaturaDetayComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'fatura_detay',
            role: 'ROLE_ADMIN,ROLE_MODERATOR'
        }
    },
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class FaturaRoutingModule {
}
