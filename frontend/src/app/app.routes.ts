import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders, Provider} from '@angular/core';
import {DashboardComponent} from './dashboard/view/dashboard.component';
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./shared/auth/AuthGuard";
import {RegisterComponent} from "./register/register.component";
import {LogoutComponent} from "./logout/logout.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {AccessDeniedComponent} from "./access-denied/access-denied.component";

export const routes: Routes = [
    {path: '', component: DashboardComponent, canActivate: [AuthGuard]},
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
    {
        path: 'marketPlace',
        loadChildren: () => import('src/app/domain/market-place/market-place.module').then(m => m.MarketPlaceModule),
    },
    {path: 'accessDenied', component: AccessDeniedComponent},
    {path: '404', component: NotFoundComponent},
    {path: '**', redirectTo: '/404'}

];
declare module "@angular/core" {
    interface ModuleWithProviders<T = any> {
        ngModule: Type<T>;
        providers?: Provider[];
    }
}
export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled', relativeLinkResolution: 'legacy' });
