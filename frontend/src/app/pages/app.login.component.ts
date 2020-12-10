import {Component, OnDestroy, OnInit, Renderer2} from '@angular/core';
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
    roles: string[] = [];
    returnUrl: string;

    constructor(appStore: AppStore,
                private authService: AuthService,
                private router: Router,
                private route: ActivatedRoute,
                private tokenStorage: TokenStorageService,
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
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        this.roles = this.tokenStorage.getUser().roles;
        this.router.navigate([this.returnUrl]);
    }


    ngOnDestroy(): void {

    }
}
