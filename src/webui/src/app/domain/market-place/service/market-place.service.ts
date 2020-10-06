import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AbstractService} from '../../../shared/abstract-service';
import {HttpClient} from '@angular/common/http';
import {AppResponse} from '../../../shared/app-response';

@Injectable()
export class MarketPlaceService extends AbstractService {

    readonly SERVICE_PATH = `${this.BASE_URL}/market-place`;

    constructor(private http: HttpClient) {
        super();
    }

    loadMarketPlaces(): Observable<AppResponse> {
        return this.http.get<AppResponse>(`${this.SERVICE_PATH}/loadMarketPlaces`);
    }

    loadMarketPlace(id: number): Observable<AppResponse> {
        return this.http.get<AppResponse>(`${this.SERVICE_PATH}/loadMarketPlace/${id}`);
    }
}

