import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {TahsilatRoutingModule} from "./tahsilat-routing.module";
import {TahsilatComponent} from "./component/sorgula/tahsilat.component";
import {TahsilatService} from "./service/tahsilat.service";
import {UserService} from "../settings/user/service/user.service";


@NgModule({
    imports: [
        SharedModule,
        TahsilatRoutingModule
    ],
    declarations: [
        TahsilatComponent
    ],
    providers: [
        TahsilatService,
        UserService
    ]
})

export class TahsilatModule {
}

