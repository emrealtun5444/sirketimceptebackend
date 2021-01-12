import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {AbstractBaseComponent, ColumnType} from '../../../../shared/abstract-base-component';
import {CariKart} from '../../../cari-kart/dto/cari-kart';
import {AppStore} from '../../../../shared/app.store';
import {FaturaService} from '../../service/fatura.service';
import {FaturaDetay} from '../../dto/fatura-detay';
import {FaturaKalem} from '../../dto/fatura-kalem';
import {Fatura} from '../../dto/fatura';
import {ActivatedRoute, Params} from '@angular/router';
import { Location } from '@angular/common';


@Component({
    selector: 'app-fatura-detay',
    templateUrl: './fatura-detay.component.html',
    styleUrls: ['./fatura-detay.component.css']
})
export class FaturaDetayComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;
    fatDetCols: any[] = [
        {type: ColumnType.STRING, field: 'stokKodu', header: this.appStore.translate.instant('label.stok.kodu')},
        {type: ColumnType.STRING, field: 'urunAdi', header: this.appStore.translate.instant('label.urun.adi')},
        {type: ColumnType.STRING, field: 'miktar', header: this.appStore.translate.instant('label.miktar')},
        {type: ColumnType.PARA, field: 'birimFiyati', header: this.appStore.translate.instant('label.birim.fiyati')},
        {type: ColumnType.PARA, field: 'tutar', header: this.appStore.translate.instant('label.tutar')},
        {type: ColumnType.PARA, field: 'iskonto', header: this.appStore.translate.instant('label.iskonto')},
        {type: ColumnType.PARA, field: 'iskontoOrani', header: this.appStore.translate.instant('label.oran')},
        {type: ColumnType.PARA, field: 'kdvTutari', header: this.appStore.translate.instant('label.kdv.tutari')},
        {type: ColumnType.STRING, field: 'kdvOrani', header: this.appStore.translate.instant('label.kdv.orani')},
        {type: ColumnType.PARA, field: 'toplamTutar', header: this.appStore.translate.instant('label.toplam.tutar')},
    ];

    totalRecords: number;
    loading: boolean;

    // returnd values
    cariKart: CariKart;
    fatura: Fatura;
    faturaDetayList: FaturaDetay[];
    faturaKalemList: FaturaKalem[];

    constructor(public appStore: AppStore,
                private faturaService: FaturaService,
                public location: Location,
                private route: ActivatedRoute) {
        super(appStore);
    }

    ngOnInit(): void {
        this.loading = false;
        this.route.params.subscribe((params: Params) => {
            this.loadFatura(params['id']);
        });
    }

    loadFatura(id) {
        this.loading = true;
        this.subscribeToResponseBase(this.faturaService.loadFatura(id), this.sorgulaSuccess, undefined);
    }

    private sorgulaSuccess(data: any) {
        this.loading = false;
        this.cariKart = data['cariKart'];
        this.fatura = data['fatura'];
        this.faturaDetayList = data['faturaDetayList'];
        this.faturaKalemList = data['faturaKalemList'].filter(value => value.satirAciklama !== null && value.satirAciklama !== '' && value.satirTipi !== 'A');
    }

  print() {
    window.print();
  }

}
