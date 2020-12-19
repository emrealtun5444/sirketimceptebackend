import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AbstractService} from "../../../../shared/abstract-service";
import {AppResponse} from "../../../../shared/app-response";


@Injectable({
  providedIn: 'root'
})
export class RoleService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/role`;

  constructor(private http: HttpClient) {
    super();
  }

  roleById(roleId: number): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/${roleId}`);
  }

  all(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}`);
  }

  authorizations(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/authorizations`);
  }

  add(data): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}`, data);
  }

  update(roleId: number, data): Observable<AppResponse> {
    return this.http.put<AppResponse>(`${this.SERVICE_PATH}/${roleId}`, data);
  }

  delete(roleId: number): Observable<AppResponse> {
    return this.http.delete<AppResponse>(`${this.SERVICE_PATH}/${roleId}`);
  }

}
