import {Component, OnInit} from '@angular/core';
import {LazyLoadEvent, SelectItem} from 'primeng/api';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import {AbstractBaseComponent} from '../../../shared/abstract-base-component';
import {AppStore} from '../../../shared/app.store';
import {DashboardService} from '../service/dashboard.service';
import {CariTipi, SiparisDurumu, SiparisYonu} from '../../../shared/constants';
import {Siparis} from "../../siparis/dto/siparis";
import {SiparisService} from "../../siparis/service/siparis.service";
import {SiparisSorguKriterleri} from "../../siparis/dto/siparis-sorgu-kriterleri";

@Component({
  templateUrl: './dashboard.component.html'
})
export class DashboardDemoComponent extends AbstractBaseComponent implements OnInit {

  talepTipis: SelectItem[];
  siparisList: Siparis[] = [];
  selectedTalepTipi: any;
  fullcalendarOptions: any;

  // istatistik tutarlar
  currentNumberOfSales: number;
  monthlyNumberOfSales: number;
  currentAmountOfSales: number;
  monthlyAmountOfSales: number;

  // aylık aktivite
  monthlyEticaretSales: number;
  monthlyPerakendeSales: number;
  monthlyOtherSales: number;


  constructor(public appStore: AppStore,
              private dashboardService: DashboardService) {
    super(appStore);
  }

  ngOnInit() {
    this.loadData();
    this.subscribeToResponseBase(this.dashboardService.currentNumberOfSales(), this.onCurrentNumberOfSales, undefined);
    this.subscribeToResponseBase(this.dashboardService.monthlyNumberOfSales(), this.onMonthlyNumberOfSales, undefined);
    this.subscribeToResponseBase(this.dashboardService.currentAmountOfSales(), this.onCurrentAmountOfSales, undefined);
    this.subscribeToResponseBase(this.dashboardService.monthlyAmountOfSales(), this.onMonthlyAmountOfSales, undefined);
    this.subscribeToResponseBase(this.dashboardService.faturaKirilim(), this.onFaturaKirilim, undefined);
   this.getSiparisList();
  }

  getSiparisList() {
    this.subscribeToResponseBase(this.dashboardService.siparisSorgula(this.prepareData()), data => {
      this.siparisList = data['resultList'];
    }, undefined);
  }

  private prepareData(): SiparisSorguKriterleri {
    let event = {first: 0, rows: 10};
    return {
      siparisNo: null,
      cariKodu: null,
      cariAdi: null,
      siparisDurumu: SiparisDurumu.ACIK,
      siparisYonu: SiparisYonu.ALINAN_SIPARIS,
      baslangicTarihi: null,
      bitisTarihi: null,
      lazyLoadEvent: event
    };
  }

  loadData() {
    this.talepTipis = [];
    this.talepTipis.push({label: 'Mesaj Tipi', value: null});
    this.talepTipis.push({label: 'Sipariş', value: {id: 1, name: 'New York', code: 'NY'}});
    this.talepTipis.push({label: 'Ürün', value: {id: 2, name: 'Rome', code: 'RM'}});
    this.talepTipis.push({label: 'Fiyat', value: {id: 3, name: 'London', code: 'LDN'}});
    this.talepTipis.push({label: 'Talep', value: {id: 4, name: 'Istanbul', code: 'IST'}});

    this.fullcalendarOptions = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
      header: {
        right: 'prev,next, today',
        left: 'title'
      }
    };
  }

  private onCurrentNumberOfSales(data) {
    this.currentNumberOfSales = data;
  }

  private onMonthlyNumberOfSales(data) {
    this.monthlyNumberOfSales = data;
  }

  private onCurrentAmountOfSales(data) {
    this.currentAmountOfSales = data;
  }

  private onMonthlyAmountOfSales(data) {
    this.monthlyAmountOfSales = data;
  }

  private onFaturaKirilim(data) {
    this.monthlyEticaretSales = data[CariTipi.ETICARET];
    this.monthlyPerakendeSales = data[CariTipi.PERAKENDE];
    this.monthlyOtherSales = data[CariTipi.BAGLANTILI_CALISAN];
  }
}
