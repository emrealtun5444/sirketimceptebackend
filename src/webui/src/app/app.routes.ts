import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {DashboardDemoComponent} from './demo/view/dashboarddemo.component';
import {SampleDemoComponent} from './demo/view/sampledemo.component';
import {FormsDemoComponent} from './demo/view/formsdemo.component';
import {DataDemoComponent} from './demo/view/datademo.component';
import {PanelsDemoComponent} from './demo/view/panelsdemo.component';
import {OverlaysDemoComponent} from './demo/view/overlaysdemo.component';
import {MenusDemoComponent} from './demo/view/menusdemo.component';
import {MessagesDemoComponent} from './demo/view/messagesdemo.component';
import {MiscDemoComponent} from './demo/view/miscdemo.component';
import {EmptyDemoComponent} from './demo/view/emptydemo.component';
import {ChartsDemoComponent} from './demo/view/chartsdemo.component';
import {FileDemoComponent} from './demo/view/filedemo.component';
import {UtilsDemoComponent} from './demo/view/utilsdemo.component';
import {DocumentationComponent} from './demo/view/documentation.component';
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./shared/auth/AuthGuard";
import {RegisterComponent} from "./register/register.component";
import {LogoutComponent} from "./logout/logout.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {AccessDeniedComponent} from "./access-denied/access-denied.component";

export const routes: Routes = [
    {path: '', component: DashboardDemoComponent, canActivate: [AuthGuard]},
    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'register', component: RegisterComponent},
    {
        path: 'stokKart',
        loadChildren: () => import('src/app/domain/stok-kart/stok-kart.module').then(m => m.StokKartModule),
    },
    {
        path: 'cariKart',
        loadChildren: () => import('src/app/domain/cari-kart/cari-kart.module').then(m => m.CariKartModule),
    },
    {
        path: 'fatura',
        loadChildren: () => import('src/app/domain/fatura/fatura.module').then(m => m.FaturaModule),
    },
    {
        path: 'profile',
        loadChildren: () => import('src/app/domain/user/user.module').then(m => m.UserModule),
    },
    {path: 'components/sample', component: SampleDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/forms', component: FormsDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/data', component: DataDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/panels', component: PanelsDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/overlays', component: OverlaysDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/menus', component: MenusDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/messages', component: MessagesDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/misc', component: MiscDemoComponent, canActivate: [AuthGuard]},
    {path: 'pages/empty', component: EmptyDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/charts', component: ChartsDemoComponent, canActivate: [AuthGuard]},
    {path: 'components/file', component: FileDemoComponent, canActivate: [AuthGuard]},
    {path: 'utils', component: UtilsDemoComponent, canActivate: [AuthGuard]},
    {path: 'documentation', component: DocumentationComponent, canActivate: [AuthGuard]},

    {path: 'accessDenied', component: AccessDeniedComponent},
    {path: '404', component: NotFoundComponent},
    {path: '**', redirectTo: '/404'}

];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'});
