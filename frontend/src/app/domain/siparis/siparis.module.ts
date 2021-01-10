import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {SiparisRoutingModule} from "./siparis-routing.module";
import {SiparisComponent} from "./component/sorgula/siparis.component";
import {SiparisService} from "./service/siparis.service";
import {SiparisDetayComponent} from "./component/detay/siparis-detay.component";
import {UserService} from "../settings/user/service/user.service";


@NgModule({
  imports: [
    SharedModule,
    SiparisRoutingModule
  ],
  declarations: [
    SiparisComponent,
    SiparisDetayComponent
  ],
  providers: [
    SiparisService,
    UserService
  ]
})

export class SiparisModule {
}

