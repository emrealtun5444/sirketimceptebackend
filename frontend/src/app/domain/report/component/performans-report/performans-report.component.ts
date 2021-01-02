import {Component, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../shared/abstract-base-component";
import {AppStore} from "../../../../shared/app.store";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ReportService} from "../../service/report.service";
import {CustomValidator} from "../../../../shared/validation/custom-validator";
import {PerformansOzetDto} from "../../dto/performans-ozet-dto";
import {UserSorguSonucu} from "../../../settings/user/dto/user-sorgu-sonucu";

@Component({
  selector: 'app-performans-report',
  templateUrl: './performans-report.component.html',
  styleUrls: ['./performans-report.component.scss']
})
export class PerformansReportComponent extends AbstractBaseComponent implements OnInit {

  reportForm: FormGroup;
  ozetPerformans: PerformansOzetDto;
  staffs: UserSorguSonucu[] = [];


  activeIndex: number = 0;

  constructor(public appStore: AppStore,
              private reportService: ReportService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.buildForms();
    this.loadData();
  }

  private buildForms() {
    this.reportForm = this.formBuilder.group({
      year: [(new Date()).getFullYear(), Validators.compose([CustomValidator.required])]
    });
  }

  private loadData() {
    this.subscribeToResponseBase(this.reportService.loadPerformansOzet(this.reportForm.value.year), this.onOzetSuccess, undefined);
    this.subscribeToResponseBase(this.reportService.loadStaffs(), data => {
      this.staffs = data;
    }, undefined);
  }

  private onOzetSuccess(data) {
    this.ozetPerformans = data;
  }



}
