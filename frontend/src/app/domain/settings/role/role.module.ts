import {NgModule} from '@angular/core';
import {SharedModule} from '../../../shared/shared.module';
import {RoleSorgulaComponent} from "./component/sorgula/role-sorgula.component";
import {RoleFormComponent} from "./component/role-form/role-form.component";
import {RoleService} from "./service/role.service";
import {RoleRoutingModule} from "./role-routing.module";

@NgModule({
    imports: [
        SharedModule,
        RoleRoutingModule
    ],
    declarations: [
        RoleSorgulaComponent,
        RoleFormComponent
    ],
    providers: [
        RoleService
    ]
})

export class RoleModule {}

