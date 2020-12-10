import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {DashboardDemoComponent} from './domain/dashboard/view/dashboarddemo.component';
import {AppMainComponent} from './app.main.component';
import {AppNotfoundComponent} from './pages/app.notfound.component';
import {AppErrorComponent} from './pages/app.error.component';
import {AppAccessdeniedComponent} from './pages/app.accessdenied.component';
import {AppLoginComponent} from './pages/app.login.component';
import {AppInvoiceComponent} from './pages/app.invoice.component';
import {AppHelpComponent} from './pages/app.help.component';
import {LogoutComponent} from './logout/logout.component';
import {RegisterComponent} from './register/register.component';
import {AuthGuard} from './shared/auth/AuthGuard';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppMainComponent,
                children: [
                    {
                        path: '', component: DashboardDemoComponent, canActivate: [AuthGuard],
                    },
                    {path: 'pages/invoice', component: AppInvoiceComponent},
                    {path: 'pages/help', component: AppHelpComponent},
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
                    {
                        path: 'marketPlace',
                        loadChildren: () => import('src/app/domain/market-place/market-place.module').then(m => m.MarketPlaceModule),
                    }
                ]
            },
            {path: 'logout', component: LogoutComponent},
            {path: 'register', component: RegisterComponent},
            {path: 'error', component: AppErrorComponent},
            {path: 'access', component: AppAccessdeniedComponent},
            {path: 'notfound', component: AppNotfoundComponent},
            {path: 'login', component: AppLoginComponent},
            {path: '**', redirectTo: '/notfound'},
        ], {scrollPositionRestoration: 'enabled', relativeLinkResolution: 'legacy'})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
