import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {CustomValidator} from "../../../../../shared/validation/custom-validator";
import {ConfirmationService, SelectItem} from "primeng/api";
import {CompanyService} from "../../service/company.service";


@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.scss']
})
export class CompanyFormComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

  @Input() display: boolean;
  @Input() companyId: number;
  @Output() displayChange = new EventEmitter();

  entityForm: FormGroup;
  operations: SelectItem[] = [];


  constructor(private companyService: CompanyService,
              appStore: AppStore,
              private confirmationService: ConfirmationService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.loadData();
    if (this.companyId) {
      this.subscribeToResponse(this.companyService.companyById(this.companyId), companyData => {
        this.buildForms(companyData);
      }, undefined);
    } else {
      this.buildForms(null);
    }
  }

  loadData() {
    this.subscribeToResponse(this.companyService.operations(), data => {
      this.operations = data;
    }, undefined);
  }

  private buildForms(companyData) {
    this.entityForm = this.formBuilder.group({
      name: [companyData ? companyData.name : null, Validators.compose([CustomValidator.required])],
      address: [companyData ? companyData.address : null, Validators.compose([CustomValidator.required])],
      telephone: [companyData ? companyData.telephone : null, Validators.compose([CustomValidator.required])],
      taxOffice: [companyData ? companyData.taxOffice : null],
      taxNumber: [companyData ? companyData.taxNumber : null],
      authorizedPerson: [companyData ? companyData.authorizedPerson : null, Validators.compose([CustomValidator.required])],
      authorizedPersonTelephone: [companyData ? companyData.authorizedPersonTelephone : null],
      email: [companyData ? companyData.email : null, Validators.compose([CustomValidator.email, Validators.required])],
      operation: [companyData ? companyData.operation : null],
    });
  }

  confirm(event: Event) {
    if (!this.entityForm.valid) return;
    this.confirmationService.confirm({
      key: 'update',
      message: this.appStore.translate.instant('info.sure.continue.process'),
      accept: () => {
        this.onSubmit();
      }
    });
  }

  onSubmit() {
    if (this.companyId) {
      this.subscribeToResponse(this.companyService.update(this.companyId, this.entityForm.value), data => {
        this.appStore.addMessage({
          severity: 'success',
          summary: this.appStore.translate.instant('success.company.guncellendi')
        }, true);
        this.onClose();
      }, undefined);
    } else {
      this.subscribeToResponse(this.companyService.add(this.entityForm.value), data => {
        this.appStore.addMessage({
          severity: 'success',
          summary: this.appStore.translate.instant('success.company.olusturuldu')
        }, true);
        this.onClose();
      }, undefined);
    }
  }

  onClose() {
    this.displayChange.emit(false);
  }

  // Work against memory leak if component is destroyed
  ngOnDestroy() {
    this.displayChange.unsubscribe();
  }

}
