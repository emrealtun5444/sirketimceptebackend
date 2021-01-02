import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';

@Injectable()
export class ReportService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/report`;

  constructor(private http: HttpClient) {
    super();
  }

  loadStaffs(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/staffs`);
  }

  loadPerformansOzet(year: number): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/loadPerformansOzet/${year}`);
  }
}

