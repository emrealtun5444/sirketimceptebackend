import {Component, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomValidator} from "../validation/custom-validator";
import {TokenStorageService} from "../service/token-storage.service";
import {AuthService} from "../service/auth.service";
import {AbstractBaseComponent} from "../abstract-base-component";
import {AppStore} from "../app.store";
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

  @Input() display: boolean;
  @Output() displayChange = new EventEmitter();

  entityForm: FormGroup;

  constructor(private tokenStorage: TokenStorageService,
              private authService: AuthService,
              appStore: AppStore,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.buildForms();
  }

  private buildForms() {
    this.entityForm = this.formBuilder.group({
      old_password: [null, Validators.compose([CustomValidator.required])],
      new_password: [null, Validators.compose([CustomValidator.required])],
      new_password_again: [null, Validators.compose([CustomValidator.required])]
    });
  }

  onSubmit() {
    this.subscribeToResponse(this.authService.changePassword(this.entityForm.value), this.registerSuccess, undefined);
  }

  private registerSuccess(data: any) {
    this.appStore.addMessage({
      severity: 'info',
      summary: this.appStore.translate.instant('success.sifre.degistirildi')
    }, true);
    this.onClose();
  }

  onClose() {
    this.displayChange.emit(false);
  }

  // Work against memory leak if component is destroyed
  ngOnDestroy() {
    this.displayChange.unsubscribe();
  }

}
