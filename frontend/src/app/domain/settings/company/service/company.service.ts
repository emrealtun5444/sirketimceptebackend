import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AbstractService} from "../../../../shared/abstract-service";
import {AppResponse} from "../../../../shared/app-response";


@Injectable({
  providedIn: 'root'
})
export class CompanyService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/companies`;

  constructor(private http: HttpClient) {
    super();
  }

  companyById(companyId: number): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/${companyId}`);
  }

  all(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}`);
  }

  add(data): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}`, data);
  }

  update(companyId: number, data): Observable<AppResponse> {
    return this.http.put<AppResponse>(`${this.SERVICE_PATH}/${companyId}`, data);
  }

  delete(companyId: number): Observable<AppResponse> {
    return this.http.delete<AppResponse>(`${this.SERVICE_PATH}/${companyId}`);
  }

  operations(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/operations`);
  }

}
