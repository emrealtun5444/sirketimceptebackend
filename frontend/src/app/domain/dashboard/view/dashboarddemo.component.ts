import {Component, OnInit} from '@angular/core';
import {SelectItem} from 'primeng/api';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import {AbstractBaseComponent} from '../../../shared/abstract-base-component';
import {AppStore} from '../../../shared/app.store';
import {DashboardService} from '../service/dashboard.service';
import {SiparisYonu} from '../../../shared/constants';
import {Siparis} from "../../siparis/dto/siparis";
import {SiparisSorguKriterleri} from "../../siparis/dto/siparis-sorgu-kriterleri";
import {UserSorguSonucu} from "../../settings/user/dto/user-sorgu-sonucu";
import {PersonelCiroDagilim} from "../dto/PersonelCiroDagilim";

@Component({
    templateUrl: './dashboard.component.html'
})
export class DashboardDemoComponent extends AbstractBaseComponent implements OnInit {

    talepTipis: SelectItem[];
    siparisList: Siparis[] = [];
    totalSiparis: number;

    userList: UserSorguSonucu[] = [];
    selectedTalepTipi: any;
    fullcalendarOptions: any;

    // istatistik tutarlar
    currentNumberOfSales: number;
    monthlyNumberOfSales: number;
    currentAmountOfSales: number;
    monthlyAmountOfSales: number;
    yearlyAmountOfSales: number = 0;

    // aylık aktivite
    personelCiroDagilim: PersonelCiroDagilim[] = [];
    donemCiroDagilim: any;

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
        this.subscribeToResponseBase(this.dashboardService.onPersonelCiroDagilim(), this.onPersonelCiroDagilim, undefined);
        this.subscribeToResponseBase(this.dashboardService.allUsers(), data => {
            this.userList = data;
        }, undefined);
        this.getSiparisList(0);
    }

    getSiparisList(page) {
        this.subscribeToResponseBase(this.dashboardService.siparisSorgula(this.prepareData(page)), data => {
            this.siparisList = data['resultList'];
            this.totalSiparis = data['totalRecords'];
        }, undefined);
    }


    private prepareData(page): SiparisSorguKriterleri {
        let event = {first: page * 4, rows: 4};
        return {
            staff: null,
            siparisNo: null,
            cariKodu: null,
            cariAdi: null,
            siparisDurumu: null,
            siparisYonu: SiparisYonu.ALINAN_SIPARIS,
            baslangicTarihi: null,
            bitisTarihi: null,
            stokKodu: null,
            urunAdi: null,
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

    private onPersonelCiroDagilim(data) {
        this.personelCiroDagilim = data;
    }

    onPageChange(event) {
        this.getSiparisList(event.page);
    }

}
