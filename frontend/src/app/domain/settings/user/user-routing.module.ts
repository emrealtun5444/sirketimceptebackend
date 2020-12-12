import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../../shared/auth/AuthGuard";
import {ProfileComponent} from "./component/profile/profile.component";

const routes: Routes = [
    {
        path: '',
        component: ProfileComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'user_profile',
            role: 'ROLE_ADMIN,ROLE_MODERATOR,ROLE_USER'
        }
    }
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule] 
})

export class  UserRoutingModule{
}
