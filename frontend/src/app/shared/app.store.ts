import {Injectable} from '@angular/core';
import {Location} from '@angular/common';
import {ConfirmationService, Message, MessageService, SelectItem} from 'primeng/primeng';
import {MenuItem} from 'primeng/api';
import {ConfirmData, ConfirmType} from "./confirm-data";
import {TranslateService} from "@ngx-translate/core";
import {TokenStorageService} from "./service/token-storage.service";
import {User} from "../domain/user/dto/user";
import {SelectService} from "./select/select.service";
import {Para} from "./para/para";

@Injectable()
export class AppStore {
    static instance: AppStore;
    public tr = {
        firstDayOfWeek: 0,
        dayNamesMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        today: 'Today',
        clear: 'Clear'
    };
    para = Para;
    dateFormat = 'dd.mm.yy';
    breadcrumbs: MenuItem[] = [];
    sirket;
    yetkiMap = new Map();
    private _confirmData: ConfirmData;
    _message;

    constructor(private messageService: MessageService,
                private _translate: TranslateService,
                private _location: Location,
                public tokenStorage: TokenStorageService,
                private _selectService: SelectService,
                private _confirmationService: ConfirmationService) {
        AppStore.instance = this;
    }

    private _loading = false;
    get loading() {
        return this._loading;
    }

    set loading(_loading: boolean) {
        this._loading = _loading;
    }

    get user(): User {
        return this.tokenStorage.getUser();
    }

    get location(): Location {
        return this._location;
    }

    get translate(): TranslateService {
        return this._translate;
    }

    get selectService(): SelectService {
        return this._selectService;
    }

    static getNativeWindow() {
        return window;
    }

    private static sortStatic(a: SelectItem, b: SelectItem): number {
        return a.label.localeCompare(b.label);
    }

    addMessage(message: Message, instant: boolean = true) {
        if (instant) {
            this.messageService.clear();
            this.messageService.add(message);
            this.scrollTop();
        } else {
            this._message = message;
        }
    }

    clearMessage() {
        this.messageService.clear();
    }

    addAllMessage(messages: Message[]) {
        this.messageService.clear();
        this.messageService.addAll(messages);
        this.scrollTop();
    }

    public confirm(confirmData: ConfirmData) {
        this._confirmData = this.buildConfirmData(confirmData);
        this._confirmationService.confirm({
            message: this._confirmData.message,
            header: this._confirmData.header,
            icon: this._confirmData.icon,
            accept: () => {
                if (this._confirmData.acceptFunction !== undefined) {
                    this._confirmData.acceptFunction.bind(this).call();
                } else {
                    this.addInstantSuccessMessage();
                }
            },
            reject: () => {
                if (this._confirmData.rejectFunction !== undefined) {
                    this._confirmData.rejectFunction.bind(this).call();
                } else {
                    if (this._confirmData.type == ConfirmType.SIL) {
                        this.addInstantRejectMessage();
                    } else {
                        this.addRejectMessage();
                        this.location.back();
                    }
                }
            }
        });
    }

    public broadcastMessage() {
        this.clearMessage();
        this.addMessage(this._message);
        this._message = '';
    }

    public addSuccessMessage() {
        this._message = {
            severity: 'success',
            summary: this._confirmData.acceptSummary,
            detail: this._confirmData.acceptDetail
        };
    }

    public addInstantSuccessMessage() {
        this.addSuccessMessage();
        this.broadcastMessage();
    }

    public addRejectMessage() {
        this._message = {
            severity: 'info',
            summary: this._confirmData.rejectSummary,
            detail: this._confirmData.rejectDetail
        };
    }

    public addInstantRejectMessage() {
        this.addRejectMessage();
        this.broadcastMessage();
    }

    sort(list: any[], nullable ?: boolean): any[] {
        if (!list) {
            return list
        }
        const sortList = list.slice();
        sortList.sort(AppStore.sortStatic);
        if (nullable) {
            sortList.unshift({label: this.translate.instant('SECINIZ'), value: null});
        }
        return sortList;
    }

    getData(key: string): any {
        const item = localStorage.getItem(key);
        this.prepareDataForTest(key, item);
        localStorage.removeItem(key);
        if (item) {
            try {
                return JSON.parse(item);
            } catch (e) {
                return item;
            }
        } else {
            return null;
        }
    }

    getDataWithoutJsonParse(key: string): any {
        const item = localStorage.getItem(key);
        this.prepareDataForTest(key, item);
        localStorage.removeItem(key);
        return item;
    }

    setData(key: string, value: any) {
        if (typeof value !== 'number' && typeof value !== 'string') {
            value = JSON.stringify(value);
        }
        localStorage.setItem(key, value);
        console.info('Deprecated.');
    }

    private prepareDataForTest(key: string, item: any) {
        localStorage.setItem('test_key', key);
        localStorage.setItem('test_item', item);
    }

    private buildConfirmData(confirmData: ConfirmData) {
        const _confirmData = confirmData;
        _confirmData.type = confirmData.type || ConfirmType.ONAY;
        _confirmData.acceptDetail = confirmData.acceptDetail || '';
        _confirmData.rejectDetail = confirmData.rejectSummary || '';
        _confirmData.icon = confirmData.icon || 'fa fa-question-circle';
        if (_confirmData.message === undefined) {
            _confirmData.message = this._translate.instant('label.onay.message');
        }
        let label = '';
        if (_confirmData.type === ConfirmType.ONAY) {
            label = 'onay';
        } else if (_confirmData.type === ConfirmType.GUNCELLE) {
            label = 'guncelle';
        } else if (_confirmData.type === ConfirmType.RED) {
            label = 'red';
        } else if (_confirmData.type === ConfirmType.KAYDET) {
            label = 'kaydet';
        } else if (_confirmData.type === ConfirmType.SIL) {
            _confirmData.icon = confirmData.icon || 'fa fa-trash-alt';
            label = 'sil';
        }
        if (_confirmData.header === undefined) {
            _confirmData.header = this._translate.instant('label.' + label + '.header');
        }
        if (_confirmData.acceptSummary === undefined) {
            _confirmData.acceptSummary = this._translate.instant('label.' + label + '.accept.summary');
        }
        if (_confirmData.rejectSummary === undefined) {
            _confirmData.rejectSummary = this._translate.instant('label.' + label + '.reject.summary');
        }
        return _confirmData;
    }

    private scrollTop() {
        let scrollToTop = window.setInterval(() => {
            let pos = window.pageYOffset;
            if (pos > 0) {
                window.scrollTo(0, pos - 50);
            } else {
                window.clearInterval(scrollToTop);
            }
        }, 16);
    }

    public getBreadcrumbPath() {
        var path = "";
        if (this.breadcrumbs != undefined) {
            for (let i = 0; i < this.breadcrumbs.length; i++) {
                if (path !== "") {
                    path = path.concat("->");
                }
                path = path.concat(this.breadcrumbs[i].label);
            }
        }
        return path;
    }
}
