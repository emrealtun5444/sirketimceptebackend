import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../shared/auth/AuthGuard";
import {PerformansReportComponent} from "./component/performans/performans-report/performans-report.component";

const routes: Routes = [
  {
    path: 'performans',
    component: PerformansReportComponent,
    canActivate: [AuthGuard],
    data: {
      id: 'report_performans',
      authorization: 'PERFORMANS_REPORT_MENU'
    }
  }
];

@NgModule({

  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class ReportRoutingModule {
}
