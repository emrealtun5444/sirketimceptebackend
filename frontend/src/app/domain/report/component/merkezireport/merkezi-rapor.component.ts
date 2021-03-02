import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseComponent} from "../../../../shared/abstract-base-component";
import {AppStore} from "../../../../shared/app.store";
import {AsenkronRaporBilgiSorguSonucu} from "../../dto/asenkron-rapor-bilgi-sorgu-sonucu";
import {AsenkronRaporService} from "../../service/asenkron-rapor.service";
import {RaporOlusmaDurumu, RaporTuru} from "../../../../shared/constants";
import {RaporSorguKriterleri} from "../../dto/rapor-sorgu-kriterleri";
import {SelectItem, SelectItemGroup} from "primeng/api";
import {CustomValidator} from "../../../../shared/validation/custom-validator";


@Component({
    selector: 'app-merkezi-rapor',
    templateUrl: './merkezi-rapor.component.html',
    styleUrls: ['./merkezi-rapor.component.css']
})
export class MerkeziRaporComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;

    raporTuruList: SelectItemGroup[] = [
        {
            label: this.appStore.translate.instant('label.rapor.maliyet'), value: 'MAL_RAP',
            items: [
                {label: this.appStore.translate.instant('label.rapor.cari.maliyet'), value: 'CARI_MALIYET_RAPORU'}
            ]
        },
        {
            label: this.appStore.translate.instant('label.rapor.satis'), value: 'SAT_RAP',
            items: [
                {label: this.appStore.translate.instant('label.rapor.marka.maliyet'), value: 'MARKA_MALIYET_RAPORU'},
                {label: this.appStore.translate.instant('label.rapor.cari.donem.ciro'), value: 'CARI_DONEM_CIRO_RAPORU'}
            ]
        },
        {
            label: this.appStore.translate.instant('label.rapor.tahsilat'), value: 'TAH_RAP',
            items: [
                {
                    label: this.appStore.translate.instant('label.rapor.cari.donem.tahsilat'),
                    value: 'CAR_DNM_TAHSILAT_RAPORU'
                },
            ]
        },
        {
            label: this.appStore.translate.instant('label.rapor.stok'), value: 'STOK_RAP',
            items: [
                {label: this.appStore.translate.instant('label.rapor.stok.bekleyen'), value: 'STOK_BEKLEYEN'},
            ]
        },
        {
            label: this.appStore.translate.instant('label.rapor.siparis'), value: 'SIP_RAP',
            items: [
                {label: this.appStore.translate.instant('label.rapor.siparis.bekleyen'), value: 'SIPARIS_BEKLEYEN'},
            ]
        },
    ];

    yilList: SelectItem[] = [];
    donemList: SelectItem[] = [];

    cols = [
        {field: 'kullanici', header: this.appStore.translate.instant('label.kullanici'), type: this.ColumnType.STRING},
        {
            field: 'raporTuru',
            header: this.appStore.translate.instant('label.rapor.turu'),
            type: this.ColumnType.ENUM,
            enum: RaporTuru
        },
        {
            field: 'documentName',
            header: this.appStore.translate.instant('label.belge.adi'),
            type: this.ColumnType.STRING
        },
        {
            field: 'raporOlusmaTarihi',
            header: this.appStore.translate.instant('label.rapor.olusturma.tarihi'),
            type: this.ColumnType.DATE_TIME
        },
        {
            field: 'raporOlusmaDurumu',
            header: this.appStore.translate.instant('label.rapor.durumu'),
            type: this.ColumnType.ENUM,
            enum: RaporOlusmaDurumu
        },
        {
            field: 'islemCevap',
            header: this.appStore.translate.instant('label.islem.cevap'),
            type: this.ColumnType.STRING
        }
    ]

    operations = [
        {
            id: 'indir',
            event: this.raporIndir,
            rendered: this.belgeIndirilebilirMi,
            tooltip: 'label.indir',
            class: 'pi pi-download'
        },
        {
            id: 'sil',
            event: this.raporSil,
            rendered: this.belgeSilinebilirMi,
            tooltip: 'label.sil',
            class: 'pi pi-trash'
        }
    ]
    hiddenColumn: string[] = ['islemCevap'];


    resultList: AsenkronRaporBilgiSorguSonucu[];

    constructor(public appStore: AppStore,
                private asenkronRaporService: AsenkronRaporService,
                private formBuilder: FormBuilder) {
        super(appStore);
    }

    ngOnInit(): void {
        this.buildForms();
        this.sorgula();
        this.loadData();
    }

    private

    buildForms() {
        this.sorguForm = this.formBuilder.group({
            raporTuru: [null, Validators.compose([CustomValidator.required])],
            yil: new Date().getFullYear(),
            donem: null
        });
    }

    private loadData() {
        var selectedYear = new Date().getFullYear();
        this.yilList.push({label: selectedYear.toString(), value: selectedYear});
        for (let i = selectedYear - 1; i > selectedYear - 5; i--) {
            this.yilList.push({label: i.toString(), value: i});
        }

        this.donemList.unshift({label: this.appStore.translate.instant('label.select.all'), value: null});
        for (let i = 1; i < 13; i++) {
            this.donemList.push({label: i.toString(), value: i});
        }
    }

    sorgula() {
        this.subscribeToResponseBase(this.asenkronRaporService.getRaporListesiByRaporTipi(RaporTuru.BOS), this.sorgulaSuccess, undefined);
    }

    private sorgulaSuccess(data: any) {
        this.resultList = data;
    }

    private belgeIndirilebilirMi(row) {
        return row.raporOlusmaDurumu === RaporOlusmaDurumu.BASARILI
    }

    private belgeSilinebilirMi(row) {
        return true;
    }

    private raporIndir(row) {
        this.subscribeToResponseBase(this.asenkronRaporService.raporIndir(row.id), this.raporIndirSuccess);
    }

    private raporSil(row) {
        this.subscribeToResponseBase(this.asenkronRaporService.raporSil(row.id), this.raporlaSuccess);
    }

    private raporIndirSuccess(url: string) {
        const link = document.createElement('a');
        link.href = url;
        link.click();
    }

    raporla() {
        this.subscribeToResponseBase(this.asenkronRaporService.createReport(this.prepareData()), this.raporlaSuccess, undefined);
    }

    private raporlaSuccess(data: any) {
        this.sorgula();
    }

    private prepareData(): RaporSorguKriterleri {
        const formModel = this.sorguForm.value;
        return {
            yil: formModel.yil,
            donem: formModel.donem,
            raporTuru: formModel.raporTuru
        };
    }

    onRefresh() {
        this.sorgula();
    }

}
