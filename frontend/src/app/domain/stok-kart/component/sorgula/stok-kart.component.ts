import {Component, OnInit} from '@angular/core';
import {StokKartStore} from '../../service/stok-kart.store';
import {FormBuilder, FormGroup} from '@angular/forms';
import {StokKartSorguKriterleri} from '../../dto/stok-kart-sorgu-kriterleri';
import {AbstractBaseComponent} from '../../../../shared/abstract-base-component';
import {AppStore} from '../../../../shared/app.store';
import {StokKartService} from '../../service/stok-kart.service';
import {StokKart} from '../../dto/stok-kart';

import {LazyLoadEvent} from 'primeng/api';

@Component({
    selector: 'app-stok-kart',
    templateUrl: './stok-kart.component.html',
    styleUrls: ['./stok-kart.component.css']
})
export class StokKartComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;

    exportColumns: any[];
    cols: any[] = [
        {field: 'stokKodu', header: this.appStore.translate.instant('label.stok.kodu')},
        {field: 'urunAdi', header: this.appStore.translate.instant('label.urun.adi')},
        {field: 'urunFiyat', header:  this.appStore.translate.instant('label.urun.fiyati')},
        {field: 'stokAdedi', header:  this.appStore.translate.instant('label.stok.adedi')},
    ];

    totalRecords: number;
    loading: boolean;
    resultList: StokKart[];

    constructor(public entityStore: StokKartStore,
                public appStore: AppStore,
                private stokKartService: StokKartService,
                private formBuilder: FormBuilder) {
        super(appStore);
    }

    ngOnInit(): void {
        this.loading = false;
        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));
        this.buildForms();
    }

    private buildForms() {
        this.sorguForm = this.formBuilder.group({
            stokKodu: null,
            urunAdi: null,
            stokAdedi: null
        });
    }

    sorgula() {
        this.loading = true;
        let event = {first: 0, rows: 20};
        this.subscribeToResponseBase(this.stokKartService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    loadLazy(event: LazyLoadEvent) {
        this.loading = true;
        this.subscribeToResponseBase(this.stokKartService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    private sorgulaSuccess(data: any) {
        this.loading = false;
        this.resultList = data['resultList'];
        this.totalRecords = data['totalRecords'];
    }

    private prepareData(lazyLoadEvent: LazyLoadEvent): StokKartSorguKriterleri {
        const formModel = this.sorguForm.value;
        return {
            stokKodu: formModel.stokKodu,
            urunAdi: formModel.urunAdi,
            stokAdedi: formModel.stokAdedi,
            lazyLoadEvent: lazyLoadEvent
        };
    }
}
