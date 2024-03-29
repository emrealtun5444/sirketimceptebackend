import {Component, OnDestroy, OnInit} from '@angular/core';
import {AbstractBaseComponent} from '../shared/abstract-base-component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AppStore} from '../shared/app.store';
import {AuthService} from '../shared/service/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../shared/service/token-storage.service';
import {CustomValidator} from '../shared/validation/custom-validator';

@Component({
  selector: 'app-login',
  templateUrl: './app.login.component.html',
})
export class AppLoginComponent extends AbstractBaseComponent implements OnInit, OnDestroy {
  entityForm: FormGroup;

  errorMessage = '';
  returnUrl: string;

  constructor(appStore: AppStore,
              private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute,
              public tokenStorage: TokenStorageService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  private buildForms() {
    this.entityForm = this.formBuilder.group({
      username: [null, Validators.compose([CustomValidator.required])],
      password: [null, Validators.compose([CustomValidator.required])]
    });
  }

  ngOnInit() {
    this.setup();
    this.buildForms();
  }

  private setup() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.subscribeToResponse(this.authService.login(this.entityForm.value), this.loginSuccess, undefined);
  }

  kayitOl() {
    this.router.navigate(['/register']);
  }

  private loginSuccess(data: any) {
    const comp = data.companies;
    if (comp.length === 0) {
      this.appStore.addMessage({
        severity: 'error',
        summary: this.appStore.translate.instant('error.register.process.not.completed')
      }, true);
    } else {
      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(data);
      if (comp.length === 1) {
        this.navigateToDashboard(comp[0].value);
      }
    }

  }

  onSelectCompany(selectedItem) {
    this.navigateToDashboard(selectedItem.value);
  }

  navigateToDashboard(entity) {
    this.tokenStorage.saveSelectedCompany(entity);
    this.router.navigate([this.returnUrl]);
  }

  ngOnDestroy(): void {

  }
}
