import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';
import {SiparisSorguKriterleri} from "../dto/siparis-sorgu-kriterleri";

@Injectable()
export class SiparisService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/siparis`;

  constructor(private http: HttpClient) {
    super();
  }

  sorgula(sorguKriteri: SiparisSorguKriterleri): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}/sorgula`, sorguKriteri);
  }

  loadSiparis(siparisNo: string): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/${siparisNo}`);
  }

  getFaturaId(id: number): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/${id}/fatura`);
  }
}

