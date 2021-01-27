import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {ReportService} from "./service/report.service";
import {PerformansReportComponent} from "./component/performans/performans-report/performans-report.component";
import {ReportRoutingModule} from "./report-routing.module";
import {PerformansItemComponent} from './component/performans/performans-item/performans-item.component';
import {CiroDagilimComponent} from './component/performans/ciro-dagilim/ciro-dagilim.component';
import {SiparisDagilimComponent} from "./component/performans/siparis-dagilim/siparis-dagilim.component";
import {TahsilatDagilimComponent} from "./component/performans/tahsilat-dagilim/tahsilat-dagilim.component";
import {CiroMarkaDagilimComponent} from "./component/performans/ciro-marka-dagilim/ciro-marka-dagilim.component";
import {ChartItemComponent} from "./component/performans/chart-item/chart-item.component";
import {AsenkronRaporService} from "./service/asenkron-rapor.service";
import {MerkeziRaporComponent} from "./component/merkezireport/merkezi-rapor.component";


@NgModule({
    imports: [
        SharedModule,
        ReportRoutingModule
    ],
    declarations: [
        PerformansReportComponent,
        PerformansItemComponent,
        CiroDagilimComponent,
        SiparisDagilimComponent,
        TahsilatDagilimComponent,
        CiroMarkaDagilimComponent,
        ChartItemComponent,
        MerkeziRaporComponent
    ],
    providers: [
        ReportService,
        AsenkronRaporService
    ],
    exports: [
        CiroDagilimComponent
    ]
})

export class ReportModule {
}

