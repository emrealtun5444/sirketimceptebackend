import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared/shared.module';
import {CompanyRoutingModule} from "./company-routing.module";
import {CompanySorgulaComponent} from "./component/sorgula/company-sorgula.component";
import {CompanyService} from "./service/company.service";
import {CompanyFormComponent} from "./component/company-form/company-form.component";


@NgModule({
  imports: [
    SharedModule,
    CompanyRoutingModule
  ],
  declarations: [
    CompanySorgulaComponent,
    CompanyFormComponent
  ],
  providers: [
    CompanyService
  ]
})

export class CompanyModule {
}

