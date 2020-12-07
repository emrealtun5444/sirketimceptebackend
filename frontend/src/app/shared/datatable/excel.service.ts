import {Injectable} from '@angular/core';
import * as XLSX from 'xlsx';
import {TranslateService} from "@ngx-translate/core";
import {Constants} from "../constants";
import {Col, ColumnType} from "../abstract-base-component";

const EXCEL_EXTENSION = '.xlsx';

@Injectable({providedIn: 'root'})
export class ExcelService {

  constructor(private translate: TranslateService) {
  }

  public exportAsExcelFile(json: any[], allCols: Col[], cols: Col[], excelFileName: string): void {

    let retData: any[] = [];
    let header = [];
    let newCols = [];

    cols.forEach((c, i) => {
      newCols.push(c);
      if (c.type === ColumnType.PARA_OBJECT) {
        newCols.push({field: 'paraBirimi' + i, header: 'label.para.birimi', type: ColumnType.STRING})
      }
    });

    newCols.forEach(col => {
      header[col.field] = this.translate.instant(col.header);
    });

    retData.push(header);
    json.forEach(data => {
      let insertData : any = [];
      cols.forEach((col,i) => {
        if (col.type === ColumnType.ENUM) {
          insertData[col.field] = data[col.field + '_ack']
        }else if (col.type === ColumnType.TRANSLATE) {
          insertData[col.field] = data[col.field + '_trns']
        }else if (col.type === ColumnType.DATE_TIME || col.type == ColumnType.DATE) {
          insertData[col.field] = data[col.field] ? new Date(data[col.field]): null;
        } else if (col.type === ColumnType.PARA_OBJECT) {
          if (data[col.field]) {
            insertData[col.field] = data[col.field].value;
            insertData['paraBirimi'+i] = this.getParaBirimi(data[col.field].paraBirimi).label;
          }
        } else {
          insertData[col.field] = data[col.field];
        }
      });
      retData.push(insertData)
    });

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(retData, {
      header: header,
      skipHeader: true,
      dateNF: Constants.DATE_FMT
    });
    // this.styleHeader(worksheet);

    const workbook: XLSX.WorkBook = {Sheets: {'data': worksheet}, SheetNames: ['data']};

    XLSX.writeFile(workbook, excelFileName + new Date().getTime() + EXCEL_EXTENSION);
  }

  private getParaBirimi(paraBirimiId: string): any {
    return Constants.paraBirimiList.find((value) => {
      return value.value === paraBirimiId;
    });
  }

  private styleHeader(worksheet: XLSX.WorkSheet) {
    const headerStyle = {
      alignment: {vertical: 'center', horizontal: 'center'},
      font: {bold: true, color: {rgb: "FFFFFFFF"}},
      fill: {
        bgColor: {theme: "4", tint: "-0.25"},
        fgColor: {theme: "4", tint: "-0.25"}
      }
    };
    const first = 'A';
    const last = worksheet["!ref"].split(':').reverse()[0].replace(/([0-9]+)/, '');

    for (let i = first.charCodeAt(0); i <= last.charCodeAt(0); i++) {
      this.setCellStyle(worksheet[String.fromCharCode(i) + '1'], headerStyle);
    }
  }

  private setCellStyle(cell: XLSX.CellObject, style: {}) {
    cell.s = style;
  }

}
