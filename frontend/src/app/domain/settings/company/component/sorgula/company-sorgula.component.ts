import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractBaseComponent, ColumnType, Operations} from "../../../../../shared/abstract-base-component";
import {CompanySorguSonucu} from "../../dto/company-sorgu-sonucu";
import {AppStore} from "../../../../../shared/app.store";
import {CompanyService} from "../../service/company.service";
import {ConfirmationService} from "primeng/api";



@Component({
  selector: 'app-company-sorgula',
  templateUrl: './company-sorgula.component.html',
  styleUrls: ['./company-sorgula.component.css']
})
export class CompanySorgulaComponent extends AbstractBaseComponent implements OnInit {

  sorguForm: FormGroup;
  showCompanyForm = false;
  selectedCompanyId: number;

  operations: Operations[] = [
    {
      id: 'guncelle',
      event: this.showUpdatedialog,
      tooltip: 'btn.guncelle',
      class: 'pi pi-pencil'
    },
    {
      id: 'sil',
      event: this.deleteConfirm,
      tooltip: 'btn.sil',
      class: 'pi pi-trash'
    }
  ];

  cols: any[] = [
    {type: ColumnType.STRING, field: 'name', header: this.appStore.translate.instant('label.company.name')},
    {type: ColumnType.STRING, field: 'address', header: this.appStore.translate.instant('label.company.address')},
    {type: ColumnType.STRING, field: 'telephone', header: this.appStore.translate.instant('label.company.telephone')},
    {type: ColumnType.STRING, field: 'authorizedPerson', header: this.appStore.translate.instant('label.company.authorizedPerson')},
    {type: ColumnType.STRING, field: 'authorizedPersonTelephone', header: this.appStore.translate.instant('label.company.authorizedPersonTelephone')},
    {type: ColumnType.STRING, field: 'taxOffice', header: this.appStore.translate.instant('label.company.taxOffice')},
    {type: ColumnType.STRING, field: 'taxNumber', header: this.appStore.translate.instant('label.company.taxNumber')},
    {type: ColumnType.STRING, field: 'email', header: this.appStore.translate.instant('label.company.email')},
    {type: ColumnType.STRING, field: 'parentOperationName', header: this.appStore.translate.instant('label.company.parentOperationName')},
    {type: ColumnType.STRING, field: 'type', header: this.appStore.translate.instant('label.company.type')},
  ];


  resultList: CompanySorguSonucu[];

  constructor(public appStore: AppStore,
              private companyService: CompanyService,
              private confirmationService: ConfirmationService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.buildForms();
    this.sorgula();
  }

  private buildForms() {
    this.sorguForm = this.formBuilder.group({});
  }

  sorgula() {
    this.subscribeToResponseBase(this.companyService.all(), this.sorgulaSuccess, undefined);
  }

  private sorgulaSuccess(data: any) {
    this.resultList = data;
  }

  showUpdatedialog(row) {
    this.selectedCompanyId = row.id;
    this.showCompanyForm = true;
  }

  onAddNewCompany() {
    this.showCompanyForm = true;
    this.selectedCompanyId = null;
  }

  onChangeShowCompanyFormClose(event) {
    this.showCompanyForm = event;
    this.sorgula();
  }

  deleteConfirm(row) {
    this.confirmationService.confirm({
      key: 'delete',
      message: this.appStore.translate.instant('info.sure.continue.process'),
      accept: () => {
        this.deleteCompany(row);
      }
    });
  }

  deleteCompany(row) {
    this.subscribeToResponseBase(this.companyService.delete(row.id), data => {
      this.appStore.addMessage({
        severity: 'success',
        summary: this.appStore.translate.instant('success.company.silindi')
      }, true);
      this.sorgula();
    }, undefined);
  }

}
