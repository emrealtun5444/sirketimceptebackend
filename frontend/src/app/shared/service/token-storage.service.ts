import {Injectable} from "@angular/core";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const COMPANY_KEY = 'user-selected-company';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() {
  }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public saveSelectedCompany(comp) {
    window.sessionStorage.removeItem(COMPANY_KEY);
    window.sessionStorage.setItem(COMPANY_KEY, comp);
  }

  public getSelectedCompany() {
    return sessionStorage.getItem(COMPANY_KEY);
  }

  public getUser() {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }

  public getCompanies() {
    return this.getUser().companies;
  }

  public hasRole(role: string): boolean {
    let roles = this.getUser().roles;
    return roles.some(x => x === role);
  }

  public isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token;
  }
}
