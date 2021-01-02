import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ReportService} from "../../../service/report.service";

@Component({
  selector: 'app-ciro-dagilim',
  templateUrl: './ciro-dagilim.component.html',
  styleUrls: ['./ciro-dagilim.component.scss']
})
export class CiroDagilimComponent extends AbstractBaseComponent implements OnInit {

  @Input() userName: string = null;
  @Input() year: number = (new Date()).getFullYear();

  ciroDagilim: any;
  totalCiro: number = 0;
  totalHedef: number = 0;


  constructor(public appStore: AppStore,
              private reportService: ReportService) {
    super(appStore);
  }

  ngOnInit(): void {
    this.subscribeToResponseBase(this.reportService.onDonemCiroDagilim(this.year, this.userName ? this.userName : 'all'), this.onDonemCiroDagilim, undefined);
  }

  private onDonemCiroDagilim(data) {
    data.forEach(row => {
      this.totalCiro += row.gerceklesenHedefTutari;
      this.totalHedef += row.hedefTutari;
    });
    data.sort((a, b) => a.donem < b.donem ? -1 : a.donem > b.donem ? 1 : 0);
    const labels = data.map(x => x.donem);
    const ciros = data.map(x => x.gerceklesenHedefTutari);
    const hedefs = data.map(x => x.hedefTutari);
    this.ciroDagilim = {
      labels: labels,
      datasets: [
        {
          label: 'Ciro',
          fill: false,
          borderColor: '#4bc0c0',
          data: ciros
        },
        {
          label: 'Hedef',
          fill: false,
          borderColor: '#565656',
          data: hedefs
        }
      ]
    }
  }

}
