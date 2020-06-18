import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {AbstractStore} from '../../../shared/abstract-store';
import {StokKart} from "../dto/stok-kart";

@Injectable()
export class StokKartStore extends AbstractStore {

  private _sorguSonuclari: BehaviorSubject<StokKart[]> = new BehaviorSubject([]);
  public readonly sorguSonuclari: Observable<StokKart[]> = this._sorguSonuclari.asObservable();


  constructor() {
    super();
  }

  public set(sorguSonuclari: StokKart[]) {
    this._sorguSonuclari.next(sorguSonuclari);
    this.tableRendered = true;
  }

  public update(entity: StokKart) {
    const sorguSonuclari = this._sorguSonuclari.getValue().slice();
    const index = sorguSonuclari.findIndex(function (item) {
      return item.id === entity.id;
    });
    sorguSonuclari[index] = entity;
    this._sorguSonuclari.next(sorguSonuclari);
  }

}
