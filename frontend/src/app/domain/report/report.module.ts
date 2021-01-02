import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {ReportService} from "./service/report.service";
import {PerformansReportComponent} from "./component/performans-report/performans-report.component";
import {ReportRoutingModule} from "./report-routing.module";


@NgModule({
  imports: [
    SharedModule,
    ReportRoutingModule
  ],
  declarations: [
    PerformansReportComponent,
  ],
  providers: [
    ReportService
  ]
})

export class ReportModule {
}

