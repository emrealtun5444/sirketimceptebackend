import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ReportService} from "../../../service/report.service";

@Component({
  selector: 'app-tahsilat-dagilim',
  templateUrl: './tahsilat-dagilim.component.html',
  styleUrls: ['./tahsilat-dagilim.component.scss']
})
export class TahsilatDagilimComponent extends AbstractBaseComponent implements OnInit {

  @Input() userName: string = null;
  @Input() year: number = (new Date()).getFullYear();

  tahsilatDagilim: any;
  toplamTutar: number = 0;


  constructor(public appStore: AppStore,
              private reportService: ReportService) {
    super(appStore);
  }

  ngOnInit(): void {
    this.subscribeToResponseBase(this.reportService.onDonemTahsilatDagilim(this.year, this.userName ? this.userName : 'all'), this.onDonemTahsilatDagilim, undefined);
  }

  private onDonemTahsilatDagilim(data) {
    data.forEach(row => {
      this.toplamTutar += row.tutar;
    });
    data.sort((a, b) => a.donem < b.donem ? -1 : a.donem > b.donem ? 1 : 0);
    const labels = data.map(x => x.donem);
    const totals = data.map(x => x.tutar);
    this.tahsilatDagilim = {
      labels: labels,
      datasets: [
        {
          label: 'Toplam Tutar',
          backgroundColor: '#42A5F5',
          borderColor: '#1E88E5',
          data: totals
        }
      ]
    }
  }

}
