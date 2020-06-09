import {AppResponse} from './app-response';
import {Observable} from 'rxjs';
import {HttpResponse} from '@angular/common/http';
import {HttpStatus} from "./constants";
import {AppStore} from "./app.store";

export abstract class AbstractBaseComponent {

  ColumnType: any = {
    NUMBER: ColumnType.NUMBER,
    DECIMAL: ColumnType.DECIMAL,
    PARA: ColumnType.PARA,
    PARA_OBJECT: ColumnType.PARA_OBJECT,
    STRING: ColumnType.STRING,
    ENUM: ColumnType.ENUM,
    DATE: ColumnType.DATE,
    DATE_TIME: ColumnType.DATE_TIME,
    KULLANICI: ColumnType.KULLANICI,
    CUSTOM: ColumnType.CUSTOM,
    HTML: ColumnType.HTML,
    MASTERCOLON: ColumnType.MASTERCOLON,
    TRANSLATE: ColumnType.TRANSLATE
  };

  public appStore: AppStore;

  constructor(appStore: AppStore) {
    this.appStore = appStore;
  }

  protected subscribeToResponseBase(result: Observable<AppResponse>, successFN: Function, errorFN?: Function) {
    result.subscribe((res: AppResponse) =>
        this.onSuccess(res, successFN, errorFN),
      (res: Response) =>
        this.handleError(res.status || HttpStatus.INTERNAL_SERVER_ERROR, 'Unhandled server error.'));
  }

  protected subscribeToResponse(result: Observable<AppResponse>, successFN: Function, errorFN?: Function) {
    this.startFN();
    this.subscribeToResponseBase(result, successFN, errorFN);
  }

  protected onSuccess(result: AppResponse, successFN: Function, errorFN: Function) {
    if (result.status === HttpStatus.OK) {
      if (!!successFN) {
        successFN.bind(this, result.data).call();
      }
      this.endFN();
    } else {
      if (!!errorFN) {
        errorFN.bind(this).call();
      }
      this.handleError(result.status, result.errorMessage);
      this.endFN();
    }
  }

  protected handleError(status, message) {
    this.appStore.addMessage({severity: 'error', summary: '', detail: message});
    this.endFN();
  }

  protected handleHttpResponse(event: HttpResponse<AppResponse>, successFN: Function, errorFN?: Function) {
    event = event || new HttpResponse();
    if (event.status == HttpStatus.OK) {
      this.onSuccess(event.body, successFN, errorFN);
    } else {
      this.handleError(event.status || HttpStatus.INTERNAL_SERVER_ERROR, 'Unhandled server error.');
    }
  }

  protected startFN() {
  }

  protected endFN() {
  }
}

export enum ColumnType {
  NUMBER, DECIMAL, PARA, PARA_OBJECT, STRING, ENUM, DATE, TRANSLATE, DATE_TIME, KULLANICI, DOSYA_NO, CUSTOM,CUSTOM1, HTML, MASTERCOLON, GRUP, ORGANIZASYON, MAHKEME, EFKS_ID
}

export interface Col {
  field: string;
  subfield?: string;
  header?: string;
  type: ColumnType;
  enum?: any;
  label?: string;
  footer?: Function;
  class?: string;
  actionFn?: Function;
  classFn?: Function;
  masterColonFn?: Function;
  toolTip?: Function;
}

export interface Operations {
  id: string;
  tooltip: string;
  class: string;
  route?: string;
  routeBase?: string;
  rendered?: Function;
  event?: Function;
}
