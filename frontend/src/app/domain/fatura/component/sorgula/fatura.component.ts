import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AbstractBaseComponent, ColumnType, Operations} from '../../../../shared/abstract-base-component';
import {AppStore} from '../../../../shared/app.store';
import {FaturaService} from '../../service/fatura.service';
import {FaturaSorguKriterleri} from '../../dto/fatura-sorgu-kriterleri';
import {Fatura} from '../../dto/fatura';
import {LazyLoadEvent} from 'primeng/api';

@Component({
    selector: 'app-fatura',
    templateUrl: './fatura.component.html',
    styleUrls: ['./fatura.component.css']
})
export class FaturaComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;
    operations: Operations[] = [
        {
            id: 'goruntule',
            route: '/detay',
            tooltip: 'label.fatura.detay',
            class: 'pi pi-search'
        }
    ];

    cols: any[] = [
        {type: ColumnType.DATE, field: 'faturaTarihi', header: this.appStore.translate.instant('label.fatura.tarihi')},
        {type: ColumnType.LINK, field: 'faturaNo', header: this.appStore.translate.instant('label.fatura.no')},
        {type: ColumnType.STRING, field: 'faturaYonu', header: this.appStore.translate.instant('label.fatura.yonu')},
        {type: ColumnType.STRING, field: 'cariKodu', header: this.appStore.translate.instant('label.cari.kodu')},
        {type: ColumnType.STRING, field: 'cariAdi', header: this.appStore.translate.instant('label.cari.adi')},
        {type: ColumnType.PARA, field: 'malBedeli', header: this.appStore.translate.instant('label.mal.bedeli')},
        {type: ColumnType.PARA, field: 'iskonto', header: this.appStore.translate.instant('label.iskonto')},
        {type: ColumnType.PARA, field: 'netToplam', header: this.appStore.translate.instant('label.net.toplam')},
        {type: ColumnType.PARA, field: 'kdvTutari', header: this.appStore.translate.instant('label.kdv.tutari')},
        {type: ColumnType.PARA, field: 'toplamTutar', header: this.appStore.translate.instant('label.toplam.tutar')},
    ];

    totalRecords: number;
    loading: boolean;
    resultList: Fatura[];

    constructor(public appStore: AppStore,
                private faturaService: FaturaService,
                private formBuilder: FormBuilder) {
        super(appStore);
    }

    ngOnInit(): void {
        this.loading = false;
        this.buildForms();
    }

    private buildForms() {
        this.sorguForm = this.formBuilder.group({
            cariKodu: null,
            cariAdi: null,
            faturaTarihi: null
        });
    }

    sorgula() {
        this.loading = true;
        let event = {first: 0, rows: 20};
        this.subscribeToResponseBase(this.faturaService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    loadLazy(event: LazyLoadEvent) {
        this.loading = true;
        this.subscribeToResponseBase(this.faturaService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    private sorgulaSuccess(data: any) {
        this.loading = false;
        this.resultList = data['resultList'];
        this.totalRecords = data['totalRecords'];
    }

    private prepareData(lazyLoadEvent: LazyLoadEvent): FaturaSorguKriterleri {
        const formModel = this.sorguForm.value;
        return {
            cariKodu: formModel.cariKodu,
            cariAdi: formModel.cariAdi,
            faturaBaslangicTarihi: formModel.faturaTarihi != null ? formModel.faturaTarihi[0] : null,
            faturaBitisTarihi: formModel.faturaTarihi != null ? formModel.faturaTarihi[1] : null,
            lazyLoadEvent: lazyLoadEvent
        };
    }

}
