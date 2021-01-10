import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ReportService} from "../../../service/report.service";

@Component({
  selector: 'app-ciro-marka-dagilim',
  templateUrl: './ciro-marka-dagilim.component.html',
  styleUrls: ['./ciro-marka-dagilim.component.scss']
})
export class CiroMarkaDagilimComponent extends AbstractBaseComponent implements OnInit {

  @Input() userName: string = null;
  @Input() year: number = (new Date()).getFullYear();

  ciroMarkaDagilim: any;
  resList: any[];
  toplamTutar: number = 0;


  constructor(public appStore: AppStore,
              private reportService: ReportService) {
    super(appStore);
  }

  ngOnInit(): void {
    this.subscribeToResponseBase(this.reportService.onDonemCiroMarkaDagilim(this.year, this.userName ? this.userName : 'all'), this.onDonemCiroMarkaDagilim, undefined);
  }

  private onDonemCiroMarkaDagilim(data) {
    this.resList = data;
    data.forEach(row => {
      this.toplamTutar += row.tutar;
    });
    const labels = data.map(x => x.marka);
    const totals = data.map(x => x.tutar);
    this.ciroMarkaDagilim = {
      labels: labels,
      datasets: [
        {
          backgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56",
            "#ff56b3",
            "#ff6f56",
            "#56ff9a",
            "#da56ff",
            "#f9ff56",
            "#56ffeb",
            "#9156ff",
          ],
          hoverBackgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56",
            "#ff56b3",
            "#ff6f56",
            "#56ff9a",
            "#da56ff",
            "#f9ff56",
            "#56ffeb",
            "#9156ff",
          ],
          data: totals
        }
      ]
    }
  }

}
