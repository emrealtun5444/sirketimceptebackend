import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';
import {TahsilatSorguKriterleri} from "../dto/tahsilat-sorgu-kriterleri";

@Injectable()
export class TahsilatService extends AbstractService {

    readonly SERVICE_PATH = `${this.BASE_URL}/tahsilat`;

    constructor(private http: HttpClient) {
        super();
    }

    sorgula(sorguKriteri: TahsilatSorguKriterleri): Observable<AppResponse> {
        return this.http.post<AppResponse>(`${this.SERVICE_PATH}/sorgula`, sorguKriteri);
    }

}

