import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ReportService} from "../../../service/report.service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-performans-item',
  templateUrl: './performans-item.component.html',
  styleUrls: ['./performans-item.component.scss']
})
export class PerformansItemComponent extends AbstractBaseComponent implements OnInit {

  @Input() userName: string;
  @Input() year: number;

  constructor(public appStore: AppStore,
              private reportService: ReportService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {

  }

}
