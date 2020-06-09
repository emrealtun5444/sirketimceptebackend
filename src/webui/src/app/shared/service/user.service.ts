import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AbstractService} from "./abstract.service";

const URL = 'user/';

@Injectable({
    providedIn: 'root'
})
export class UserService extends AbstractService {

    constructor(private http: HttpClient) {
        super();
    }

    getPublicContent(): Observable<any> {
        return this.http.get(this.BASE_URL + URL + 'all', {responseType: 'text'});
    }

    getUserBoard(): Observable<any> {
        return this.http.get(this.BASE_URL + URL + 'user', {responseType: 'text'});
    }

    getModeratorBoard(): Observable<any> {
        return this.http.get(this.BASE_URL + URL + 'mod', {responseType: 'text'});
    }

    getAdminBoard(): Observable<any> {
        return this.http.get(this.BASE_URL + URL + 'admin', {responseType: 'text'});
    }
}
