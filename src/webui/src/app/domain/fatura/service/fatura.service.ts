import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';
import {FaturaSorguKriterleri} from "../dto/fatura-sorgu-kriterleri";

@Injectable()
export class FaturaService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/fatura`;

  constructor(private http: HttpClient) {
    super();
  }

  sorgula(sorguKriteri: FaturaSorguKriterleri): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SERVICE_PATH}/sorgula`, sorguKriteri);
  }

}

