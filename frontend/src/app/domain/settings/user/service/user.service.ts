import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AbstractService} from "../../../../shared/abstract-service";
import {AppResponse} from "../../../../shared/app-response";


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

  all(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}`);
  }

  roles(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/roles`);
  }

  companies(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/companies`);
  }

  changepassword(userId: number, newPasword: string): Observable<AppResponse> {
    return this.http.put<AppResponse>(`${this.SERVICE_PATH}/changepassword/${userId}`, newPasword);
  }

  add(data): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}`, data);
  }

  update(userId: number, data): Observable<AppResponse> {
    return this.http.put<AppResponse>(`${this.SERVICE_PATH}/${userId}`, data);
  }

  delete(userId: number): Observable<AppResponse> {
    return this.http.delete<AppResponse>(`${this.SERVICE_PATH}/${userId}`);
  }

}
