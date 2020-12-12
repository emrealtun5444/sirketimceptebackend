import {Injectable} from "@angular/core";
import {HTTP_INTERCEPTORS, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "../shared/service/token-storage.service";

const TOKEN_HEADER_KEY = 'Authorization';
const COMPANY_HEADER_KEY = 'Company';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let headers = req.headers;
    const token = this.token.getToken();
    const company = this.token.getSelectedCompany();

    if (token != null) {
      headers = headers.set(TOKEN_HEADER_KEY, 'fk ' + token);
    }
    if (company !== null && company !== undefined) {
      headers = headers.set(COMPANY_HEADER_KEY, company);
    }
    const authReq = req.clone({headers});
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];
