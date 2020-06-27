import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {CariKartRoutingModule} from "./cari-kart-routing.module";
import {CariKartComponent} from "./component/sorgula/cari-kart.component";
import {CariKartService} from "./service/cari-kart.service";

@NgModule({
    imports: [
        SharedModule,
        CariKartRoutingModule
    ],
    declarations: [
        CariKartComponent
    ],
    providers: [
        CariKartService
    ]
})

export class CariKartModule {}

