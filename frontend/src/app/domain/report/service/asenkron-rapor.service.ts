import {Injectable} from '@angular/core';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {AppResponse} from "../../../shared/app-response";
import {RaporTuru} from "../../../shared/constants";
import {RaporSorguKriterleri} from "../dto/rapor-sorgu-kriterleri";

@Injectable()
export class AsenkronRaporService extends AbstractService {

    readonly SERVICE_PATH = `${this.BASE_URL}/asenkronrapor`;

    constructor(private http: HttpClient) {
        super();
    }

    createReport(sorguKriteri: RaporSorguKriterleri): Observable<AppResponse> {
        return this.http.post<AppResponse>(`${this.SERVICE_PATH}/raporAl`, sorguKriteri);
    }

    raporIndir(raporId: number): Observable<AppResponse> {
        return this.http.get<AppResponse>(`${this.SERVICE_PATH}/${raporId}/indir`);
    }

    getRaporListesiByRaporTipi(raporTipi: RaporTuru): Observable<AppResponse> {
        return this.http.post<AppResponse>(`${this.SERVICE_PATH}/sorgula`, raporTipi);
    }

}

