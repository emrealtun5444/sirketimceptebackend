import {APP_INITIALIZER, Injector, LOCALE_ID, NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HashLocationStrategy, LocationStrategy, registerLocaleData} from '@angular/common';
import {AppRoutingModule} from './app-routing.module';

// Application Components
import {AppCodeModule} from './app.code.component';
import {AppComponent} from './app.component';
import {AppMainComponent} from './app.main.component';
import {AppConfigComponent} from './app.config.component';
import {AppMenuComponent} from './app.menu.component';
import {AppMenuitemComponent} from './app.menuitem.component';
import {AppTopBarComponent} from './app.topbar.component';
import {AppFooterComponent} from './app.footer.component';

// Demo pages
import {DashboardDemoComponent} from './domain/dashboard/view/dashboarddemo.component';
import {AppInvoiceComponent} from './pages/app.invoice.component';
import {AppHelpComponent} from './pages/app.help.component';
import {AppNotfoundComponent} from './pages/app.notfound.component';
import {AppErrorComponent} from './pages/app.error.component';
import {AppAccessdeniedComponent} from './pages/app.accessdenied.component';
import {AppLoginComponent} from './pages/app.login.component';

// Application services
import {MenuService} from './app.menu.service';
import {LogoutComponent} from './logout/logout.component';
import {RegisterComponent} from './register/register.component';

// additional
import {TranslateLoader, TranslateModule, TranslateService} from '@ngx-translate/core';
import {WebpackTranslateLoader} from './shared/webpack-translate-loader';
import {SharedModule} from './shared/shared.module';
import {translateInitializerFactory} from './shared/translate-initializer-factory';
import {AuthInterceptor, authInterceptorProviders} from './helpers/auth.interceptor';
import {AppStore} from './shared/app.store';
import {AuthService} from './shared/service/auth.service';
import {AuthGuard} from './shared/auth/AuthGuard';
import {ConfirmationService, MessageService} from 'primeng/api';

import localeTr from '@angular/common/locales/tr';
import {DashboardService} from './domain/dashboard/service/dashboard.service';

registerLocaleData(localeTr);

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppCodeModule,
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
    AppMainComponent,
    AppConfigComponent,
    AppMenuComponent,
    AppMenuitemComponent,
    AppTopBarComponent,
    AppFooterComponent,
    DashboardDemoComponent,
    AppLoginComponent,
    AppInvoiceComponent,
    AppHelpComponent,
    AppNotfoundComponent,
    AppErrorComponent,
    AppAccessdeniedComponent,
    LogoutComponent,
    RegisterComponent
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
    authInterceptorProviders,
    MenuService, AppStore, AuthService, AuthGuard, MessageService,
    ConfirmationService, DashboardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
