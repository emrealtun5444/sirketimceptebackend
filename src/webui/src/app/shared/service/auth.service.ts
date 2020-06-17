import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppResponse} from "../app-response";
import {AbstractService} from "../abstract-service";

const URL = 'auth/';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class AuthService extends AbstractService {

    constructor(private http: HttpClient) {
        super();
    }

    login(credentials): Observable<AppResponse> {
        return this.http.post<AppResponse>(this.BASE_URL + URL + 'signin', {
            username: credentials.username,
            password: credentials.password
        }, httpOptions);
    }

    register(user): Observable<AppResponse> {
        return this.http.post<AppResponse>(this.BASE_URL + URL + 'signup', {
            name: user.name,
            surname: user.surname,
            username: user.username,
            email: user.email,
            password: user.password
        }, httpOptions);
    }
}
