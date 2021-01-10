import {Component, OnInit} from '@angular/core';
import {AbstractBaseComponent, ColumnType} from '../../../../shared/abstract-base-component';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AppStore} from '../../../../shared/app.store';
import {CariKart} from '../../dto/cari-kart';
import {CariKartService} from '../../service/cari-kart.service';
import {CariKartSorguKriterleri} from '../../dto/cari-kart-sorgu-kriterleri';
import {LazyLoadEvent, SelectItem} from 'primeng/api';
import {UserService} from "../../../settings/user/service/user.service";

@Component({
    selector: 'app-cari-kart',
    templateUrl: './cari-kart.component.html',
    styleUrls: ['./cari-kart.component.css']
})
export class CariKartComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;
    cols: any[] = [
        {type: ColumnType.STRING, field: 'sorumluPersonel', header: this.appStore.translate.instant('label.sorumlu.personel')},
        {type: ColumnType.STRING, field: 'cariTipi', header: this.appStore.translate.instant('label.cari.tipi')},
        {type: ColumnType.STRING, field: 'cariKodu', header: this.appStore.translate.instant('label.cari.kodu')},
        {type: ColumnType.STRING, field: 'cariAdi', header: this.appStore.translate.instant('label.cari.adi')},
        {type: ColumnType.PARA, field: 'toplamBorc', header: this.appStore.translate.instant('label.toplam.borc')},
        {type: ColumnType.PARA, field: 'toplamAlacak', header: this.appStore.translate.instant('label.toplam.alacak')},
        {type: ColumnType.PARA, field: 'bakiye', header: this.appStore.translate.instant('label.bakiye')},
    ];

    totalRecords: number;
    loading: boolean;
    resultList: CariKart[];
    staffs: SelectItem[] = [];

    constructor(public appStore: AppStore,
                private userService: UserService,
                private cariKartService: CariKartService,
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
            staff: null,
            cariKodu: null,
            cariAdi: null
        });
    }

    sorgula() {
        this.loading = true;
        let event = {first: 0, rows: 20};
        this.subscribeToResponseBase(this.cariKartService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    loadLazy(event: LazyLoadEvent) {
        this.loading = true;
        this.subscribeToResponseBase(this.cariKartService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
    }

    private sorgulaSuccess(data: any) {
        this.loading = false;
        this.resultList = data['resultList'];
        this.totalRecords = data['totalRecords'];
    }

    private prepareData(lazyLoadEvent: LazyLoadEvent): CariKartSorguKriterleri {
        const formModel = this.sorguForm.value;
        return {
            staff: formModel.staff,
            cariKodu: formModel.cariKodu,
            cariAdi: formModel.cariAdi,
            lazyLoadEvent: lazyLoadEvent
        };
    }

}
