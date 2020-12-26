import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {SiparisRoutingModule} from "./siparis-routing.module";
import {SiparisComponent} from "./component/sorgula/siparis.component";
import {SiparisService} from "./service/siparis.service";
import {SiparisDetayComponent} from "./component/detay/siparis-detay.component";


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
    SiparisService
  ]
})

export class SiparisModule {
}

