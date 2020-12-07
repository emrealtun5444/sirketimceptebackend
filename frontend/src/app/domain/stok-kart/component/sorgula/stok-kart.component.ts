import {Component, OnInit} from '@angular/core';
import {StokKartStore} from "../../service/stok-kart.store";
import {FormBuilder, FormGroup} from "@angular/forms";
import {StokKartSorguKriterleri} from "../../dto/stok-kart-sorgu-kriterleri";
import {AbstractBaseComponent} from "../../../../shared/abstract-base-component";
import {AppStore} from "../../../../shared/app.store";
import {StokKartService} from "../../service/stok-kart.service";
import {LazyLoadEvent} from "primeng";
import {StokKart} from "../../dto/stok-kart";

import jsPDF from "jspdf";
import "jspdf-autotable";

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

    private prepareDataForAll(page: number): StokKartSorguKriterleri {
        const formModel = this.sorguForm.value;
        return {
            stokKodu: formModel.stokKodu,
            urunAdi: formModel.urunAdi,
            stokAdedi: formModel.stokAdedi,
            lazyLoadEvent: {
                first: page,
                rows: 1000
            }
        };
    }

   async exportExcel() {
        var allList = await this.fetchAllData();
        import("xlsx").then(xlsx => {
            const worksheet = xlsx.utils.json_to_sheet(allList);
            const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
            const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
            this.saveAsExcelFile(excelBuffer, "products");
        });
    }

    exportPdf() {
        const doc = new jsPDF('p','pt');
        doc['autoTable'](this.exportColumns, this.resultList);
        doc.save('stok-kartları.pdf');
    }

    saveAsExcelFile(buffer: any, fileName: string): void {
        import("file-saver").then(FileSaver => {
            let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
            let EXCEL_EXTENSION = '.xlsx';
            const data: Blob = new Blob([buffer], {
                type: EXCEL_TYPE
            });
            FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
        });
    }

    async fetchAllData() {
        var allList = [];
        var page = 0;
        while (true) {
            let data = await this.stokKartService.sorgula(this.prepareDataForAll(page)).toPromise();
            var resultList = data.data['resultList'];
            if (resultList.length > 0) {
                allList = allList.concat(resultList);
                page+=1000;
            } else {
                return allList;
            }
        }
    }

}
