import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';
import {CariKartSorguKriterleri} from "../dto/cari-kart-sorgu-kriterleri";

@Injectable()
export class CariKartService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/cariKart`;

  constructor(private http: HttpClient) {
    super();
  }

  sorgula(sorguKriteri: CariKartSorguKriterleri): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}/sorgula`, sorguKriteri);
  }

}

