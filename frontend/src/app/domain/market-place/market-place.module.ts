import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {MarketPlaceRoutingModule} from "./market-place-routing.module";
import {MarketPlaceComponent} from "./component/sorgula/market-place.component";
import {MarketPlaceService} from "./service/market-place.service";


@NgModule({
    imports: [
        SharedModule,
        MarketPlaceRoutingModule
    ],
    declarations: [
        MarketPlaceComponent,
    ],
    providers: [
        MarketPlaceService
    ]
})

export class MarketPlaceModule {
}

