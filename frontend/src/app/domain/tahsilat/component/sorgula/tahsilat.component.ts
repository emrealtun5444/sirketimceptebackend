import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AbstractBaseComponent, ColumnType} from '../../../../shared/abstract-base-component';
import {AppStore} from '../../../../shared/app.store';
import {LazyLoadEvent, SelectItem} from 'primeng/api';
import {OdemeYonu} from "../../../../shared/constants";
import {Router} from "@angular/router";
import {TahsilatService} from "../../service/tahsilat.service";
import {TahsilatSorguKriterleri} from "../../dto/tahsilat-sorgu-kriterleri";
import {Tahsilat} from "../../dto/tahsilat";
import {UserService} from "../../../settings/user/service/user.service";

@Component({
    selector: 'app-tahsilat',
    templateUrl: './tahsilat.component.html',
    styleUrls: ['./tahsilat.component.css']
})
export class TahsilatComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;

    odemeTipiList: SelectItem[] = [
        {label: this.appStore.translate.instant('label.select.all'), value: null},
        {label: this.appStore.translate.instant('label.odeme.tipi.nakit'), value: 'NAKIT'},
        {label: this.appStore.translate.instant('label.odeme.tipi.cek'), value: 'CEK'},
        {label: this.appStore.translate.instant('label.odeme.tipi.senet'), value: 'SENET'},
    ];

    odemeYonuList: SelectItem[] = [
        {label: this.appStore.translate.instant('label.select.all'), value: null},
        {label: this.appStore.translate.instant('label.odeme.yonu.borc'), value: 'BORC'},
        {label: this.appStore.translate.instant('label.odeme.yonu.alacak'), value: 'ALACAK'},
    ];

    cols: any[] = [
        {type: ColumnType.STRING, field: 'evrakNo', header: this.appStore.translate.instant('label.evrak.no')},
        {type: ColumnType.DATE, field: 'islemTarihi', header: this.appStore.translate.instant('label.islem.tarihi')},
        {type: ColumnType.STRING, field: 'cariKodu', header: this.appStore.translate.instant('label.cari.kodu')},
        {type: ColumnType.STRING, field: 'cariAdi', header: this.appStore.translate.instant('label.cari.adi')},
        {type: ColumnType.STRING, field: 'odemeYonu', header: this.appStore.translate.instant('label.odeme.yonu')},
        {type: ColumnType.STRING, field: 'odemeTipi', header: this.appStore.translate.instant('label.odeme.tipi')},
        {type: ColumnType.PARA, field: 'tutar', header: this.appStore.translate.instant('label.tutar')},
        {type: ColumnType.DATE, field: 'vadeTarihi', header: this.appStore.translate.instant('label.vade.tarihi')},
        {type: ColumnType.STRING, field: 'kdvOrani', header: this.appStore.translate.instant('label.kdv.orani')},
        {type: ColumnType.STRING, field: 'aciklama', header: this.appStore.translate.instant('label.aciklama')},

        {type: ColumnType.STRING, field: 'banka', header: this.appStore.translate.instant('label.banka')},
        {type: ColumnType.STRING, field: 'subeAdi', header: this.appStore.translate.instant('label.sube.adi')},
        {type: ColumnType.STRING, field: 'bankaHesapNo', header: this.appStore.translate.instant('label.banka.hesap.no')},
        {type: ColumnType.STRING, field: 'bankaCekNo', header: this.appStore.translate.instant('label.banka.cek.no')},
        {type: ColumnType.STRING, field: 'borcluAdi', header: this.appStore.translate.instant('label.borclu.adi')},
        {type: ColumnType.STRING, field: 'borcluAdresi', header: this.appStore.translate.instant('label.borclu.adresi')},

    ];

    hiddenColumn: string[] = ['banka', 'subeAdi', 'bankaHesapNo', 'bankaCekNo', 'borcluAdi', 'borcluAdresi', 'cariKodu', 'odemeYonu', 'kdvOrani'];

    totalRecords: number;
    loading: boolean;
    resultList: Tahsilat[];
    staffs: SelectItem[] = [];

    constructor(public appStore: AppStore,
                private userService: UserService,
                private tahsilatService: TahsilatService,
                private router: Router,
                private formBuilder: FormBuilder) {
        super(appStore);
    }

    ngOnInit(): void {
        this.loadData();
        this.buildForms();
    }

    loadData() {
        this.loading = false;
        this.subscribeToResponse(this.userService.grantedUsers(), data => {
            this.staffs = data;
            this.staffs.unshift({label: this.appStore.translate.instant('label.select.all'), value: null});
        }, undefined);
    }

    private buildForms() {
        this.sorguForm = this.formBuilder.group({
            evrakNo: null,
            cariKodu: null,
            cariAdi: null,
            odemeYonu: OdemeYonu.ALACAK,
            odemeTipi: null,
            islemTarihi: null,
            vadeTarihi: null,
            staff: null
        });
    }

    sorgula() {
        this.loading = true;
        let event = {first: 0, rows: 20};
        this.subscribeToResponseBase(this.tahsilatService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    loadLazy(event: LazyLoadEvent) {
        this.loading = true;
        this.subscribeToResponseBase(this.tahsilatService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    private sorgulaSuccess(data: any) {
        this.loading = false;
        this.resultList = data['resultList'];
        this.totalRecords = data['totalRecords'];
    }

    private prepareData(lazyLoadEvent: LazyLoadEvent): TahsilatSorguKriterleri {
        const formModel = this.sorguForm.value;
        return {
            staff: formModel.staff,
            evrakNo: formModel.evrakNo,
            cariKodu: formModel.cariKodu,
            cariAdi: formModel.cariAdi,
            odemeTipi: formModel.odemeTipi,
            odemeYonu: formModel.odemeYonu,
            baslangicTarihi: formModel.islemTarihi != null ? formModel.islemTarihi[0] : null,
            bitisTarihi: formModel.islemTarihi != null ? formModel.islemTarihi[1] : null,
            vadeBaslangicTarihi: formModel.vadeTarihi != null ? formModel.vadeTarihi[0] : null,
            vadeBitisTarihi: formModel.vadeTarihi != null ? formModel.vadeTarihi[1] : null,
            lazyLoadEvent: lazyLoadEvent
        };
    }

}
