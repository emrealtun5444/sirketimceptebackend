import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../../shared/auth/AuthGuard";
import {ProfileComponent} from "./component/profile/profile.component";
import {KullaniciSorgulaComponent} from "./component/sorgula/kullanici-sorgula.component";

const routes: Routes = [
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'user_profile',
        }
    },
    {
        path: '',
        component: KullaniciSorgulaComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'user_sorgulama',
            authorization: 'USER_MENU'
        }
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class  UserRoutingModule{
}
