import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../../shared/auth/AuthGuard";
import {RoleSorgulaComponent} from "./component/sorgula/role-sorgula.component";

const routes: Routes = [
  {
    path: '',
    component: RoleSorgulaComponent,
    canActivate: [AuthGuard],
    data: {
      id: 'role_sorgulama',
      authorization: 'ROLE_MENU'
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class RoleRoutingModule {
}
