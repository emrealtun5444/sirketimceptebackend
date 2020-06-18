import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {StokKartRoutingModule} from "./stok-kart-routing.module";
import {StokKartComponent} from "./component/sorgula/stok-kart.component";
import {StokKartService} from "./service/stok-kart.service";
import {StokKartStore} from "./service/stok-kart.store";


@NgModule({
    imports: [
        SharedModule,
        StokKartRoutingModule
    ],
    declarations: [
        StokKartComponent
    ],
    providers: [
        StokKartService,
        StokKartStore
    ]
})

export class StokKartModule {
}

