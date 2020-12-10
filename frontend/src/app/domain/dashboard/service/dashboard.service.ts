import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AbstractService} from '../../../shared/abstract-service';
import {AppResponse} from '../../../shared/app-response';


@Injectable()
export class DashboardService extends AbstractService {

    readonly SERVICE_PATH = `${this.BASE_URL}/dashboard`;

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

    faturaKirilim(): Observable<AppResponse> {
        return this.http.get<AppResponse>(`${this.SERVICE_PATH}/faturaKirilim`);
    }
}

