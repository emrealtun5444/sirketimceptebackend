import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AbstractService} from "../abstract-service";


@Injectable({
    providedIn: 'root'
})
export class UserService extends AbstractService {

    readonly SERVICE_PATH = `${this.BASE_URL}/user`;

    constructor(private http: HttpClient) {
        super();
    }

    getPublicContent(): Observable<any> {
        return this.http.get(`${this.SERVICE_PATH}/all`, {responseType: 'text'});
    }

    getUserBoard(): Observable<any> {
        return this.http.get(`${this.SERVICE_PATH}/user`, {responseType: 'text'});
    }

    getModeratorBoard(): Observable<any> {
        return this.http.get(`${this.SERVICE_PATH}/mod`, {responseType: 'text'});
    }

    getAdminBoard(): Observable<any> {
        return this.http.get(`${this.SERVICE_PATH}/admin`, {responseType: 'text'});
    }
}
