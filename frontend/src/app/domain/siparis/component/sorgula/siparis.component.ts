import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AbstractBaseComponent, ColumnType, Operations} from '../../../../shared/abstract-base-component';
import {AppStore} from '../../../../shared/app.store';
import {LazyLoadEvent, SelectItem} from 'primeng/api';
import {SiparisService} from "../../service/siparis.service";
import {SiparisDurumu, SiparisYonu} from "../../../../shared/constants";
import {SiparisSorguKriterleri} from "../../dto/siparis-sorgu-kriterleri";
import {Siparis} from "../../dto/siparis";
import {Router} from "@angular/router";

@Component({
  selector: 'app-siparis',
  templateUrl: './siparis.component.html',
  styleUrls: ['./siparis.component.css']
})
export class SiparisComponent extends AbstractBaseComponent implements OnInit {

  sorguForm: FormGroup;
  operations: Operations[] = [
    {
      id: 'goruntule',
      route: '/detay',
      tooltip: 'label.siparis.detay',
      class: 'pi pi-search'
    },
    {
      id: 'fatura',
      rendered: this.faturaRendered,
      tooltip: 'label.fatura.detay',
      class: 'pi pi-book'
    }
  ];

  siparisDurumuList: SelectItem[] = [
    {label: this.appStore.translate.instant('label.select.all'), value: null},
    {label: this.appStore.translate.instant('label.siparis.durum.acik'), value: 'ACIK'},
    {label: this.appStore.translate.instant('label.siparis.durum.kismen.teslim'), value: 'KISMI_SEVKEDILDI'},
    {label: this.appStore.translate.instant('label.siparis.durum.tamamlandi'), value: 'TAMAMLANDI'},
  ];

  siparisYonuList: SelectItem[] = [
    {label: this.appStore.translate.instant('label.select.all'), value: null},
    {label: this.appStore.translate.instant('label.siparis.yonu.alinan'), value: 'ALINAN_SIPARIS'},
    {label: this.appStore.translate.instant('label.siparis.yonu.verilen'), value: 'VERILEN_SIPARIS'},
  ];

  cols: any[] = [
    {type: ColumnType.STRING, field: 'siparisNo', header: this.appStore.translate.instant('label.siparis.no')},
    {type: ColumnType.DATE, field: 'islemTarihi', header: this.appStore.translate.instant('label.islem.tarihi')},
    {type: ColumnType.STRING, field: 'cariKodu', header: this.appStore.translate.instant('label.cari.kodu')},
    {type: ColumnType.STRING, field: 'cariAdi', header: this.appStore.translate.instant('label.cari.adi')},
    {type: ColumnType.STRING, field: 'malKodu', header: this.appStore.translate.instant('label.stok.kodu')},
    {type: ColumnType.STRING, field: 'urunAdi', header: this.appStore.translate.instant('label.urun.adi')},
    {type: ColumnType.NUMBER, field: 'miktar', header: this.appStore.translate.instant('label.miktar')},
    {type: ColumnType.NUMBER, field: 'teslimMiktari', header: this.appStore.translate.instant('label.teslim.miktari')},
    {type: ColumnType.NUMBER, field: 'kalanMiktar', header: this.appStore.translate.instant('label.kalan.miktari')},
    {type: ColumnType.NUMBER, field: 'stokMiktari', header: this.appStore.translate.instant('label.stok.miktari')},
    {type: ColumnType.DATE, field: 'teslimTarihi', header: this.appStore.translate.instant('label.teslim.tarihi')},


    {type: ColumnType.PARA, field: 'birimFiyati', header: this.appStore.translate.instant('label.birim.fiyati')},
    {type: ColumnType.PARA, field: 'tutari', header: this.appStore.translate.instant('label.tutar')},
    {type: ColumnType.PARA, field: 'iskonto', header: this.appStore.translate.instant('label.iskonto')},
    {type: ColumnType.PARA, field: 'kdvTutari', header: this.appStore.translate.instant('label.kdv.tutari')},
    {type: ColumnType.LINK, field: 'faturaNo', header: this.appStore.translate.instant('label.fatura.no')},
    {type: ColumnType.STRING, field: 'siparisDurumu', header: this.appStore.translate.instant('label.siparis.durumu')},
  ];

  hiddenColumn: string[] = ['cariKodu', 'birimFiyati', 'tutari', 'iskonto', 'kdvTutari', 'faturaNo', 'teslimTarihi'];

  totalRecords: number;
  loading: boolean;
  resultList: Siparis[];

  constructor(public appStore: AppStore,
              private siparisService: SiparisService,
              private router: Router,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.loading = false;
    this.buildForms();
  }

  private buildForms() {
    this.sorguForm = this.formBuilder.group({
      siparisNo: null,
      cariKodu: null,
      cariAdi: null,
      siparisDurumu: SiparisDurumu.ACIK,
      siparisYonu: SiparisYonu.ALINAN_SIPARIS,
      islemTarihi: null
    });
  }

  sorgula() {
    this.loading = true;
    let event = {first: 0, rows: 20};
    this.subscribeToResponseBase(this.siparisService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
  }

  loadLazy(event: LazyLoadEvent) {
    this.loading = true;
    this.subscribeToResponseBase(this.siparisService.sorgula(this.prepareData(event)), this.sorgulaSuccess, undefined);
  }

  private sorgulaSuccess(data: any) {
    this.loading = false;
    this.resultList = data['resultList'];
    this.totalRecords = data['totalRecords'];
  }

  private prepareData(lazyLoadEvent: LazyLoadEvent): SiparisSorguKriterleri {
    const formModel = this.sorguForm.value;
    return {
      siparisNo: formModel.siparisNo,
      cariKodu: formModel.cariKodu,
      cariAdi: formModel.cariAdi,
      siparisDurumu: formModel.siparisDurumu,
      siparisYonu: formModel.siparisYonu,
      baslangicTarihi: formModel.islemTatihi != null ? formModel.islemTatihi[0] : null,
      bitisTarihi: formModel.islemTatihi != null ? formModel.islemTatihi[1] : null,
      lazyLoadEvent: lazyLoadEvent
    };
  }

  faturaRendered(row) {
    return !!row.faturaNo;
  }

  faturaGit(row) {
    this.subscribeToResponseBase(this.siparisService.getFaturaId(row.id), data => {
      this.router.navigate(['/fatura/' + data + '/detay']);
    }, undefined);
  }

}
