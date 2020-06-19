import { __decorate } from "tslib";
import { APP_INITIALIZER, Injector, LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserModule, Title } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HashLocationStrategy, LocationStrategy, registerLocaleData } from '@angular/common';
import { AppRoutes } from './app.routes';
import localeTr from '@angular/common/locales/tr';
registerLocaleData(localeTr);
// Application Components
import { AppComponent } from './app.component';
import { AppMenuComponent } from './app.menu.component';
import { AppMenuitemComponent } from './app.menuitem.component';
import { AppTopBarComponent } from './app.topbar.component';
import { AppFooterComponent } from './app.footer.component';
// Demo pages
import { DashboardDemoComponent } from './demo/view/dashboarddemo.component';
import { SampleDemoComponent } from './demo/view/sampledemo.component';
import { FormsDemoComponent } from './demo/view/formsdemo.component';
import { DataDemoComponent } from './demo/view/datademo.component';
import { PanelsDemoComponent } from './demo/view/panelsdemo.component';
import { OverlaysDemoComponent } from './demo/view/overlaysdemo.component';
import { MenusDemoComponent } from './demo/view/menusdemo.component';
import { MessagesDemoComponent } from './demo/view/messagesdemo.component';
import { MiscDemoComponent } from './demo/view/miscdemo.component';
import { EmptyDemoComponent } from './demo/view/emptydemo.component';
import { ChartsDemoComponent } from './demo/view/chartsdemo.component';
import { FileDemoComponent } from './demo/view/filedemo.component';
import { UtilsDemoComponent } from './demo/view/utilsdemo.component';
import { DocumentationComponent } from './demo/view/documentation.component';
// Demo services
import { CarService } from './demo/service/carservice';
import { CountryService } from './demo/service/countryservice';
import { EventService } from './demo/service/eventservice';
import { NodeService } from './demo/service/nodeservice';
// Application services
import { MenuService } from './app.menu.service';
import { AuthInterceptor, authInterceptorProviders } from "./helpers/auth.interceptor";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { translateInitializerFactory } from "./shared/translate-initializer-factory";
import { TranslateLoader, TranslateModule, TranslateService } from "@ngx-translate/core";
import { ConfirmationService, MessageService } from "primeng";
import { AppStore } from "./shared/app.store";
import { AuthService } from "./shared/service/auth.service";
import { SharedModule } from "./shared/shared.module";
import { WebpackTranslateLoader } from "./shared/webpack-translate-loader";
import { AuthGuard } from "./shared/auth/AuthGuard";
import { LogoutComponent } from './logout/logout.component';
// auth
let AppModule = class AppModule {
};
AppModule = __decorate([
    NgModule({
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
            DashboardDemoComponent,
            SampleDemoComponent,
            FormsDemoComponent,
            DataDemoComponent,
            PanelsDemoComponent,
            OverlaysDemoComponent,
            MenusDemoComponent,
            MessagesDemoComponent,
            MessagesDemoComponent,
            MiscDemoComponent,
            ChartsDemoComponent,
            EmptyDemoComponent,
            FileDemoComponent,
            UtilsDemoComponent,
            DocumentationComponent,
            LoginComponent,
            RegisterComponent,
            LogoutComponent
        ],
        providers: [
            {
                provide: APP_INITIALIZER,
                useFactory: translateInitializerFactory,
                deps: [TranslateService, Injector],
                multi: true
            },
            { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
            { provide: LOCALE_ID, useValue: 'tr' },
            { provide: LocationStrategy, useClass: HashLocationStrategy },
            CarService,
            CountryService,
            EventService,
            NodeService,
            MenuService,
            authInterceptorProviders,
            MessageService,
            ConfirmationService,
            Title,
            AppStore,
            AuthService,
            AuthGuard
        ],
        bootstrap: [AppComponent]
    })
], AppModule);
export { AppModule };
//# sourceMappingURL=app.module.js.map