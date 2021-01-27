import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractBaseComponent} from "../../../../shared/abstract-base-component";
import {AppStore} from "../../../../shared/app.store";
import {AsenkronRaporBilgiSorguSonucu} from "../../dto/asenkron-rapor-bilgi-sorgu-sonucu";
import {AsenkronRaporService} from "../../service/asenkron-rapor.service";
import {RaporOlusmaDurumu, RaporTuru, SiparisDurumu, SiparisYonu} from "../../../../shared/constants";
import {RaporSorguKriterleri} from "../../dto/rapor-sorgu-kriterleri";
import {SelectItem} from "primeng/api";


@Component({
    selector: 'app-merkezi-rapor',
    templateUrl: './merkezi-rapor.component.html',
    styleUrls: ['./merkezi-rapor.component.css']
})
export class MerkeziRaporComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;
    raporTuruList: SelectItem[] = [
        {label: this.appStore.translate.instant('label.rapor.cari.maliyet'), value: 'CARI_MALIYET_RAPORU'},
        {label: this.appStore.translate.instant('label.rapor.marka.maliyet'), value: 'MARKA_MALIYET_RAPORU'},
    ];

    cols = [
        {field: 'kullanici', header: this.appStore.translate.instant('label.kullanici'), type: this.ColumnType.STRING},
        {
            field: 'raporTuru',
            header: this.appStore.translate.instant('label.rapor.turu'),
            type: this.ColumnType.ENUM,
            enum: RaporTuru
        },
        {
            field: 'islemCevap',
            header: this.appStore.translate.instant('label.islem.cevap'),
            type: this.ColumnType.STRING
        },
        {
            field: 'documentName',
            header: this.appStore.translate.instant('label.belge.adi'),
            type: this.ColumnType.STRING
        },
        {
            field: 'raporOlusmaDurumu',
            header: this.appStore.translate.instant('label.rapor.durumu'),
            type: this.ColumnType.ENUM,
            enum: RaporOlusmaDurumu
        },
        {
            field: 'raporOlusmaTarihi',
            header: this.appStore.translate.instant('label.rapor.olusturma.tarihi'),
            type: this.ColumnType.DATE
        }
    ]

    operations = [
        {
            id: 'indir',
            event: this.raporIndir,
            rendered: this.belgeIndirilebilirMi,
            tooltip: 'label.indir',
            class: 'pi pi-download'
        }
    ]


    resultList: AsenkronRaporBilgiSorguSonucu[];

    constructor(public appStore: AppStore,
                private asenkronRaporService: AsenkronRaporService,
                private formBuilder: FormBuilder) {
        super(appStore);
    }

    ngOnInit(): void {
        this.buildForms();
        this.sorgula();
    }

    private buildForms() {
        this.sorguForm = this.formBuilder.group({
            raporTuru: RaporTuru.MARKA_MALIYET_RAPORU,
            raporTarihi: null
        });
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

    private raporIndir(row) {
        this.subscribeToResponseBase(this.asenkronRaporService.raporIndir(row.id), this.raporIndirSuccess);
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
            baslangicTarihi: formModel.raporTarihi != null ? formModel.raporTarihi[0] : null,
            bitisTarihi: formModel.raporTarihi != null ? formModel.raporTarihi[1] : null,
            raporTuru: formModel.raporTuru
        };
    }

    onRefresh() {
        this.sorgula();
    }

}
