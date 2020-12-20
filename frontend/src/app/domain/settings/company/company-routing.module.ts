import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../../shared/auth/AuthGuard";
import {CompanySorgulaComponent} from "./component/sorgula/company-sorgula.component";

const routes: Routes = [
  {
    path: '',
    component: CompanySorgulaComponent,
    canActivate: [AuthGuard],
    data: {
      id: 'company_sorgulama',
      authorization: 'COMPANY_MENU'
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class CompanyRoutingModule {
}
