import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ConfirmType} from '../confirm-data';
import {AbstractBaseComponent, Col, Operations} from '../abstract-base-component';
import {Table} from "primeng/table";
import {LazyLoadEvent, MenuItem} from "primeng";
import {AppStore} from "../app.store";
import {FilterUtils} from "primeng/utils";
import {ExcelService} from "../datatable/excel.service";

@Component({
    selector: 'app-common-lazy-dt',
    templateUrl: './common-lazy-datatable.component.html',
    styleUrls: ['./common-lazy-datatable.component.css'],
})
export class CommonLazyDatatableComponent extends AbstractBaseComponent implements OnInit {

    showFilter: false;
    items: MenuItem[];
    columnOptions: Col[] = [];
    columns: Col[] = [];
    islemler = false;

    @Input() loading;
    @Input() totalRecords;
    @Input() header: string;
    @Input() title: string;
    @Input() emptyLabel;
    @Input() checkbox = false;
    @Input() export = true;
    @Input() radio = false;
    @Input() hiddenColumn: string[] = ['updateTime', 'guncelleyenKullanici', 'creationTime', 'tanitanKullanici'];
    @Input() selected: number[] = [];
    @Input() cols: Col[];
    @Input() that;
    @Input() class = 'ui-g-12';
    @Input() footer = false;


    @Input() operations: Operations[];
    @Input() goruntule = false;
    @Input() guncelle = false;
    @Input() sil = false;
    @Input() silObject = false;
    @Input() tarihce = false;
    @Input() sortField;
    @Input() silOnay = true;
    @Input() disabledCheckBox = false;

    @Input() rowClass;
    @Input() rowFunc: (any) => any;

    @Input() datatableOpen = true;
    @Output() datatableToggle: EventEmitter<any> = new EventEmitter<any>();

    @Output() selection: EventEmitter<any> = new EventEmitter<any>();
    @Output() filtered: EventEmitter<any> = new EventEmitter<any>();
    @Output() silEvent: EventEmitter<any> = new EventEmitter<any>();
    @Output() loadLazyEvent: EventEmitter<any> = new EventEmitter<any>();

    secilecekKayitSayisi: number = 0;
    secilenKayitSayisi: number = 0;

    constructor(public excelService: ExcelService,
                appStore: AppStore) {
        super(appStore);
    }

    private _selection: any[] = [];
    private _filtered: any[] = [];

    get selectionArray() {
        return this._selection;
    }

    set selectionArray(val: any) {
        this._selection = val;
        this.emitSelection();
    }

    get filteredArray() {
        return this._filtered;
    }

    set filteredArray(val: any) {
        this._filtered = val;
        this.emitFiltered();
    }

    private _datatable: Table;

    get datatable(): Table {
        return this._datatable;
    }

    @ViewChild('dt')
    set datatable(theElementRef: Table) {
        this._datatable = theElementRef;
    }

    private _data: any[];

    @Input()
    get data() {
        return this._data;
    }

    set data(val) {
        this._data = this.buildData(val, this.cols);
        this.selectionArrayDetect();
        this.afterDataSet();
        if (this.datatable != undefined && !this.datatable.lazy)
            this.datatable.first = 0;
    }

    ngOnInit(): void {
        if (this.goruntule || this.guncelle || this.sil || this.silObject || this.tarihce ||
            (this.operations && this.operations.length > 0)) {
            this.islemler = true;
        }
        if (this.cols) {
            this.cols.forEach(col => {
                if (col.header != null) {
                    col.label = this.appStore.translate.instant(col.header);
                    this.columnOptions.push(col);
                    if (this.hiddenColumn.indexOf(col.field) === -1) {
                        this.columns.push(col);
                    }
                }
            });
        }
        if (this.data) {
            this.data = this.data;
        }
        FilterUtils['dateTimeFilterCustom'] = (value, filter): boolean => {
            if (filter === undefined || filter === null) {
                return true;
            }
            if (value === undefined || value === null) {
                return false;
            }
            let valueDate = new Date(value);
            valueDate.setHours(0, 0, 0, 0);
            return filter == valueDate.getTime();
        }

        FilterUtils['paraFilterCustom'] = (value, filter): boolean => {
            if (filter === undefined || filter === null) {
                return true;
            }
            if (value === undefined || value === null) {
                return false;
            }
            return value.toString().includes(filter);
        }
        this.initDataTable();
    }

    protected emitSelection() {
        this.selection.emit(this._selection);
    }

    protected emitFiltered() {
        this.filtered.emit(this._filtered);
    }

    selectionArrayDetect() {
        if (this.checkbox) {
            if (this.selected && this.selected instanceof Array) {
                this.selectionArray = this.data.filter(value => this.selected.find(value2 => value2 === value['id']));
            } else {
                this.selectionArray = [];
            }
        } else {
            if (this.selected && !(this.selected instanceof Array)) {
                this.selectionArray = this.data.find(value => this.selected === value['id']);
            } else {
                this.selectionArray = null;
            }
        }
    }

    isHidden(column: string): boolean {
        return this.columns.findIndex((val) => {
            return val.field === column;
        }) === -1;
    }

    exportToExcel() {
        const data = this.checkbox ? this.selectionArray : this.data;
        this.excelService.exportAsExcelFile(data, this.cols, this.columns, 'report');
    }

    renderEmptyLabel() {
        return (this.emptyLabel && this.data && this.data.length === 0 && !this.loading);
    }

    silConfirm(id) {
        if (this.silOnay) {
            this.appStore.confirm(
                {
                    type: ConfirmType.SIL,
                    acceptFunction: () => {
                        this.silEvent.emit(id);
                    }
                }
            );
        } else {
            this.silEvent.emit(id);
        }
    }

    silObjectConfirm(obj) {
        if (this.silOnay) {
            this.appStore.confirm(
                {
                    type: ConfirmType.SIL,
                    acceptFunction: () => {
                        this.silEvent.emit(obj);
                    }
                }
            );
        } else {
            this.silEvent.emit(obj);
        }
    }

    call(func, ...rowData) {
        if (func) {
            if (rowData) {
                return func.bind(this.that, ...rowData).call();
            } else {
                return func.bind(this.that).call();
            }
        }
    }

    kayitSec() {
        this.secilenKayitSayisi = this.secilecekKayitSayisi || 0;

        this.selected = [];
        for (let i = 0; i < (this.secilecekKayitSayisi || 0); i++) {
            this.selected.push(this.data[i].id);
        }
        this.selectionArrayDetect();
    }

    kayitTemizle() {
        this.secilecekKayitSayisi = 0;
        this.kayitSec();
    }

    kayitKilidiAc() {
        this.secilenKayitSayisi = 0;
    }

    isChecked(data: any) {
        return !!this.selectionArray && !!this.selectionArray.find(value => value.id == (typeof data === 'number' ? data : data.id));
    }

    isCheckedAll() {
        return this.selectionArray && this.selected && this.selectionArray.length === this.selected.length;
    }

    accordionEvent(event: any, opened: boolean) {
        event['opened'] = opened;
        this.datatableToggle.emit(event);
    }

    protected afterDataSet() {
    }

    protected initDataTable(): void {
        this.buildExport();
    }

    private buildExport() {

        let excelLabel: string = this.appStore.translate.instant('label.excel');
        let pdfLabel: string = this.appStore.translate.instant('label.pdf');

        this.items = [
            {
                label: excelLabel, icon: 'far fa-file-excel', command: () => {
                    if (this.checkbox) {
                        this._datatable.exportCSV({selectionOnly: true});
                    } else {
                        this._datatable.exportCSV();
                    }
                }
            },
            {
                label: pdfLabel, icon: 'far fa-file-pdf', command: () => {
                    if (this.checkbox) {
                        this._datatable.exportCSV({selectionOnly: true});
                    } else {
                        this._datatable.exportCSV();
                    }
                }
            }
        ];
    }

    loadLazy(event: LazyLoadEvent) {
        this.loadLazyEvent.emit(event);
    }

    paraFilter(value: any, field: any, dt: Table) {
        if (value) {
            value = value.replace('.', '')
            value = value.replace(',', '.')
        }
        dt.filter(value, field, 'paraFilterCustom');
    }

    paraObjectFilter(value: any, field: any, dt: Table) {
        if (value) {
            value = value.replace('.', '')
            value = value.replace(',', '.')
        }
        dt.filter(value, field + '.value', 'paraFilterCustom');
    }

}
