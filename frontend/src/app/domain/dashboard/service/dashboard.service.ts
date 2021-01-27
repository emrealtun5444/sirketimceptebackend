import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AbstractService} from '../../../shared/abstract-service';
import {AppResponse} from '../../../shared/app-response';
import {SiparisSorguKriterleri} from "../../siparis/dto/siparis-sorgu-kriterleri";


@Injectable()
export class DashboardService extends AbstractService {

  readonly SERVICE_PATH = `${this.BASE_URL}/dashboard`;
  readonly SIPARIS_SERVICE_PATH = `${this.BASE_URL}/siparis`;
  readonly USER_SERVICE_PATH = `${this.BASE_URL}/user`;

  constructor(private http: HttpClient) {
    super();
  }

  currentNumberOfSales(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/currentNumberOfSales`);
  }

  monthlyNumberOfSales(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/monthlyNumberOfSales`);
  }

  currentAmountOfSales(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/currentAmountOfSales`);
  }

  monthlyAmountOfSales(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/monthlyAmountOfSales`);
  }

  onPersonelCiroDagilim(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.SERVICE_PATH}/personelCiroDagilim`);
  }

  siparisSorgula(sorguKriteri: SiparisSorguKriterleri): Observable<AppResponse> {
    return this.http.post<AppResponse>(`${this.SIPARIS_SERVICE_PATH}/sorgula`, sorguKriteri);
  }

  allUsers(): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${this.USER_SERVICE_PATH}/grantedUsersData`);
  }

}

