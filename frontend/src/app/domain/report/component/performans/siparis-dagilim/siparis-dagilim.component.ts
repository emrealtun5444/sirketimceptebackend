import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ReportService} from "../../../service/report.service";

@Component({
  selector: 'app-siparis-dagilim',
  templateUrl: './siparis-dagilim.component.html',
  styleUrls: ['./siparis-dagilim.component.scss']
})
export class SiparisDagilimComponent extends AbstractBaseComponent implements OnInit {

  @Input() userName: string = null;
  @Input() year: number = (new Date()).getFullYear();

  siparisDagilim: any;
  totalAdet: number = 0;
  teslimAdet: number = 0;
  toplamTutar: number = 0;


  constructor(public appStore: AppStore,
              private reportService: ReportService) {
    super(appStore);
  }

  ngOnInit(): void {
    this.subscribeToResponseBase(this.reportService.onDonemSiparisDagilim(this.year, this.userName ? this.userName : 'all'), this.onDonemSiparisDagilim, undefined);
  }

  private onDonemSiparisDagilim(data) {
    data.forEach(row => {
      this.totalAdet += row.toplamAdet;
      this.teslimAdet += row.teslimAdet;
      this.toplamTutar += row.toplamTutar;
    });
    data.sort((a, b) => a.donem < b.donem ? -1 : a.donem > b.donem ? 1 : 0);
    const labels = data.map(x => x.donem);
    const totals = data.map(x => x.toplamAdet);
    const teslims = data.map(x => x.teslimAdet);
    this.siparisDagilim = {
      labels: labels,
      datasets: [
        {
          label: 'Toplam Adet',
          backgroundColor: '#42A5F5',
          borderColor: '#1E88E5',
          data: totals
        },
        {
          label: 'Teslim Adet',
          backgroundColor: '#9CCC65',
          borderColor: '#7CB342',
          data: teslims
        }
      ]
    }
  }

}
