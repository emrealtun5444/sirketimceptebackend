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
  options: any;

  toplamTutar: number = 0;
  toplamNakit: number = 0;
  toplamCek: number = 0;
  toplamSenet: number = 0;


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
      this.toplamNakit += row.nakitTutar;
      this.toplamCek += row.cekTutar;
      this.toplamSenet += row.senetTutar;
    });
    data.sort((a, b) => a.donem < b.donem ? -1 : a.donem > b.donem ? 1 : 0);
    const labels = data.map(x => x.donem);
    const tutar = data.map(x => x.tutar);
    const nakitTutar = data.map(x => x.nakitTutar);
    const cekTutar = data.map(x => x.cekTutar);
    const senetTutar = data.map(x => x.senetTutar);
    this.tahsilatDagilim = {
      labels: labels,
      datasets: [
        {
          label: 'Toplam Tutar',
          backgroundColor: '#42A5F5',
          borderColor: '#1E88E5',
          data: tutar
        },
        {
          label: 'Nakit',
          backgroundColor: '#56ff9a',
          borderColor: '#56ff9a',
          data: nakitTutar
        },
        {
          label: 'Cek',
          backgroundColor: '#FFCE56',
          borderColor: '#FFCE56',
          data: cekTutar
        },
        {
          label: 'Senet',
          backgroundColor: '#FF6384',
          borderColor: '#FF6384',
          data: senetTutar
        }
      ]
    }

    this.options = {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true
          }
        }]
      }
    }
  }

}
