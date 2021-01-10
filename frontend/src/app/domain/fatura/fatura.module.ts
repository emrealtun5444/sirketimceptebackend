import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {FaturaRoutingModule} from "./fatura-routing.module";
import {FaturaComponent} from "./component/sorgula/fatura.component";
import {FaturaService} from "./service/fatura.service";
import {FaturaDetayComponent} from "./component/detay/fatura-detay.component";
import {UserService} from "../settings/user/service/user.service";


@NgModule({
    imports: [
        SharedModule,
        FaturaRoutingModule
    ],
    declarations: [
        FaturaComponent,
        FaturaDetayComponent
    ],
    providers: [
        FaturaService,
        UserService
    ]
})

export class FaturaModule {}

