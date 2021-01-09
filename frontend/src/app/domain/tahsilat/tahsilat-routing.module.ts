import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../shared/auth/AuthGuard";
import {TahsilatComponent} from "./component/sorgula/tahsilat.component";

const routes: Routes = [
    {
        path: '',
        component: TahsilatComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'tahsilat_sorgula',
            authorization: 'TAHSILAT_MENU'
        }
    }
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class TahsilatRoutingModule {
}
