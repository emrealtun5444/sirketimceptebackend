import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {UserService} from "../../service/user.service";
import {CustomValidator} from "../../../../../shared/validation/custom-validator";
import {ConfirmationService, SelectItem} from "primeng/api";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

  @Input() display: boolean;
  @Input() userId: number;
  @Output() displayChange = new EventEmitter();

  entityForm: FormGroup;
  roles: SelectItem[] = [];
  companies: SelectItem[] = [];
  notifications: any[] = [];
  showChangePasword = false;
  newPaword: string;

  constructor(private userService: UserService,
              appStore: AppStore,
              private confirmationService: ConfirmationService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.loadData();
    if (this.userId) {
      this.subscribeToResponse(this.userService.userById(this.userId), data => {
        this.buildForms(data);
      }, undefined);
    } else {
      this.buildForms(null);
    }
  }

  loadData() {
    this.subscribeToResponse(this.userService.roles(), data => {
      this.roles = data;
    }, undefined);
    this.subscribeToResponse(this.userService.companies(), data => {
      this.companies = data;
    }, undefined);
    this.subscribeToResponse(this.userService.notifications(), data => {
      data.forEach(row => {
        this.notifications.push({label: this.appStore.translate.instant('label.' + data), value: row});
      });
    }, undefined);
  }

  private buildForms(userData) {
    this.entityForm = this.formBuilder.group({
      name: [userData ? userData.name : null, Validators.compose([CustomValidator.required])],
      surname: [userData ? userData.surname : null, Validators.compose([CustomValidator.required])],
      email: [userData ? userData.email : null, Validators.compose([CustomValidator.email, Validators.required])],
      username: [userData ? userData.username : null, Validators.compose([CustomValidator.required])],
      passwordInput: [userData ? userData.passwordInput : null, !userData ? Validators.compose([CustomValidator.required]) : null],
      roles: [userData ? userData.roles : null, Validators.compose([CustomValidator.required])],
      companies: [userData ? userData.companies : null, Validators.compose([CustomValidator.required])],
      notifications: [userData ? userData.notifications : null, Validators.compose([])],
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
    if (this.userId) {
      this.subscribeToResponse(this.userService.update(this.userId, this.entityForm.value), data => {
        this.appStore.addMessage({
          severity: 'success',
          summary: this.appStore.translate.instant('success.kullanici.guncellendi')
        }, true);
        this.onClose();
      }, undefined);
    } else {
      this.subscribeToResponse(this.userService.add(this.entityForm.value), data => {
        this.appStore.addMessage({
          severity: 'success',
          summary: this.appStore.translate.instant('success.kullanici.olusturuldu')
        }, true);
        this.onClose();
      }, undefined);
    }
  }

  changePaswordAction() {
    this.subscribeToResponse(this.userService.changepassword(this.userId, this.newPaword), data => {
      this.showChangePasword = false;
      this.appStore.addMessage({
        severity: 'info',
        summary: this.appStore.translate.instant('success.sifre.degistirildi')
      }, true);
    }, undefined);
  }

  onClose() {
    this.displayChange.emit(false);
  }

  // Work against memory leak if component is destroyed
  ngOnDestroy() {
    this.displayChange.unsubscribe();
  }

}
