import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';
import {StokKartSorguKriterleri} from "../dto/stok-kart-sorgu-kriterleri";

@Injectable()
export class StokKartService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/stokKart`;

  constructor(private http: HttpClient) {
    super();
  }

  sorgula(sorguKriteri: StokKartSorguKriterleri): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}/sorgula`, sorguKriteri);
  }

}

