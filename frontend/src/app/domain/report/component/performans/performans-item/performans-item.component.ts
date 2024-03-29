import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent, ColumnType, Operations} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ReportService} from "../../../service/report.service";

@Component({
  selector: 'app-performans-item',
  templateUrl: './performans-item.component.html',
  styleUrls: ['./performans-item.component.scss']
})
export class PerformansItemComponent extends AbstractBaseComponent implements OnInit {

  @Input() userName: string;
  @Input() year: number;
  selectedCariKart: number;

  showCariKartDialog = false;

  operations: Operations[] = [
    {
      id: 'goruntule',
      event: this.showCariPerformance,
      tooltip: 'label.cari.performans',
      class: 'pi pi-chart-bar'
    }
  ];

  cols: any[] = [
    {type: ColumnType.STRING, field: 'cariKodu', header: this.appStore.translate.instant('label.cari.kodu')},
    {type: ColumnType.STRING, field: 'cariAdi', header: this.appStore.translate.instant('label.cari.adi')},
    {type: ColumnType.PARA, field: 'toplamBorc', header: this.appStore.translate.instant('label.toplam.borc')},
    {type: ColumnType.PARA, field: 'toplamAlacak', header: this.appStore.translate.instant('label.toplam.alacak')},
    {type: ColumnType.PARA, field: 'bakiye', header: this.appStore.translate.instant('label.bakiye')},
    {type: ColumnType.PARA, field: 'toplamCiro', header: this.appStore.translate.instant('label.toplam.ciro')},
    {type: ColumnType.PARA, field: 'yillikHedef', header: this.appStore.translate.instant('label.yillik.hedef')},
    {
      type: ColumnType.PARA,
      field: 'gerceklesmeYuzdesi',
      header: this.appStore.translate.instant('label.gerceklesme.yuzdesi')
    },
  ];

  resultList: any[];

  constructor(public appStore: AppStore,
              private reportService: ReportService) {
    super(appStore);
  }

  ngOnInit(): void {
    this.subscribeToResponseBase(this.reportService.onDonemCiroHedefDagilim(this.year, this.userName ? this.userName : 'all'), data => {
      data.sort((a, b) => a.toplamCiro < b.toplamCiro ? 1 : a.toplamCiro > b.toplamCiro ? -1 : 0);
      this.resultList = data;
    }, undefined);
  }

  showCariPerformance(data) {
    this.selectedCariKart = data.cariKodu;
    this.showCariKartDialog = true;
  }

}
