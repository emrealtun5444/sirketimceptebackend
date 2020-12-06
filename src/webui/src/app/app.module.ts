import {APP_INITIALIZER, Injector, LOCALE_ID, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserModule, Title} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HashLocationStrategy, LocationStrategy, registerLocaleData} from '@angular/common';
import {AppRoutes} from './app.routes';

import localeTr from '@angular/common/locales/tr';
registerLocaleData(localeTr);

// Application Components
import {AppComponent} from './app.component';
import {AppMenuComponent} from './app.menu.component';
import {AppMenuitemComponent} from './app.menuitem.component';
import {AppTopBarComponent} from './app.topbar.component';
import {AppFooterComponent} from './app.footer.component';
// Demo pages
import {DashboardComponent} from './dashboard/view/dashboard.component';
// Application services
import {MenuService} from './app.menu.service';
import {AuthInterceptor, authInterceptorProviders} from "./helpers/auth.interceptor";
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {translateInitializerFactory} from "./shared/translate-initializer-factory";
import {TranslateLoader, TranslateModule, TranslateService} from "@ngx-translate/core";
import {ConfirmationService, MessageService} from "primeng";
import {AppStore} from "./shared/app.store";
import {AuthService} from "./shared/service/auth.service";
import {SharedModule} from "./shared/shared.module";
import {WebpackTranslateLoader} from "./shared/webpack-translate-loader";
import {AuthGuard} from "./shared/auth/AuthGuard";
import {LogoutComponent} from './logout/logout.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import {BnNgIdleService} from "bn-ng-idle";
import {DashboardService} from "./dashboard/service/dashboard.service";

// auth


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutes,
        HttpClientModule,
        BrowserAnimationsModule,

        SharedModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useClass: WebpackTranslateLoader
            }
        })
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent,
        AppMenuComponent,
        AppMenuitemComponent,
        AppTopBarComponent,
        AppFooterComponent,
        DashboardComponent,
        LoginComponent,
        RegisterComponent,
        LogoutComponent,
        NotFoundComponent,
        AccessDeniedComponent
    ],
    providers: [
        {
            provide: APP_INITIALIZER,
            useFactory: translateInitializerFactory,
            deps: [TranslateService, Injector],
            multi: true
        },
        {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
        {provide: LOCALE_ID, useValue: 'tr'},
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        MenuService,
        authInterceptorProviders,
        MessageService,
        ConfirmationService,
        Title,
        AppStore,
        AuthService,
        AuthGuard,
        BnNgIdleService,
        DashboardService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
