import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared/shared.module';
import {ProfileComponent} from "./component/profile/profile.component";
import {UserRoutingModule} from "./user-routing.module";
import {UserService} from "./service/user.service";
import {KullaniciSorgulaComponent} from "./component/sorgula/kullanici-sorgula.component";
import {UserFormComponent} from "./component/user-form/user-form.component";

@NgModule({
    imports: [
        SharedModule,
        UserRoutingModule
    ],
    declarations: [
        ProfileComponent,
        KullaniciSorgulaComponent,
        UserFormComponent
    ],
    providers: [
        UserService
    ]
})

export class UserModule {}

