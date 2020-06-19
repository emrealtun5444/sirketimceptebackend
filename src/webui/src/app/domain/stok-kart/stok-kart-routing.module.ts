import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StokKartComponent} from "./component/sorgula/stok-kart.component";
import {AuthGuard} from "../../shared/auth/AuthGuard";

const routes: Routes = [
    {
        path: '',
        component: StokKartComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'stok_kart_sorgula',
            role: 'ROLE_ADMIN,ROLE_MODERATOR'
        }
    }
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class StokKartRoutingModule {
}
