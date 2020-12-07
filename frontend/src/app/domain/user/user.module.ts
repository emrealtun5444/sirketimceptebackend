import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {ProfileComponent} from "./component/profile/profile.component";
import {UserRoutingModule} from "./user-routing.module";
import {UserService} from "./service/user.service";

@NgModule({
    imports: [
        SharedModule,
        UserRoutingModule
    ],
    declarations: [
        ProfileComponent
    ],
    providers: [
        UserService
    ]
})

export class UserModule {}

