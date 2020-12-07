import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AbstractService} from "../../../shared/abstract-service";
import {AppResponse} from "../../../shared/app-response";


@Injectable({
    providedIn: 'root'
})
export class UserService extends AbstractService {

    readonly SERVICE_PATH = `${this.BASE_URL}/user`;

    constructor(private http: HttpClient) {
        super();
    }

    userById(userId: number): Observable<AppResponse> {
        return this.http.get<AppResponse>(`${this.SERVICE_PATH}/${userId}`);
    }


}
