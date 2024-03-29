import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../../shared/auth/AuthGuard";
import {MarketPlaceComponent} from "./component/sorgula/market-place.component";

const routes: Routes = [
    {
        path: '',
        component: MarketPlaceComponent,
        canActivate: [AuthGuard],
        data: {
            id: 'market_place_sorgula',
          authorization: 'ENTEGRASYON_AYAR'
        }
    }
];

@NgModule({

    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class MarketPlaceRoutingModule {
}
