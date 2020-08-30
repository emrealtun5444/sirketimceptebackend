import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {FaturaRoutingModule} from "./fatura-routing.module";
import {FaturaComponent} from "./component/sorgula/fatura.component";
import {FaturaService} from "./service/fatura.service";


@NgModule({
    imports: [
        SharedModule,
        FaturaRoutingModule
    ],
    declarations: [
        FaturaComponent
    ],
    providers: [
        FaturaService
    ]
})

export class FaturaModule {}

