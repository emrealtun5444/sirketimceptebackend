import {Component, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/service/auth.service";
import {TokenStorageService} from "../shared/service/token-storage.service";
import {AbstractBaseComponent} from "../shared/abstract-base-component";
import {AppStore} from "../shared/app.store";
import {CustomValidator} from "../shared/validation/custom-validator";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

    entityForm: FormGroup;

    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];

    constructor(appStore: AppStore,
                private authService: AuthService,
                private renderer: Renderer2,
                private router: Router,
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
        if (this.tokenStorage.getToken()) {
            // this.isLoggedIn = true;
            // this.roles = this.tokenStorage.getUser().roles;
            this.router.navigate(['dashboard']);
        }
        this.renderer.addClass(document.body, "login-body");
        this.buildForms();
    }

    onSubmit() {
        this.subscribeToResponse(this.authService.login(this.entityForm.value), this.loginSuccess, this.loginFailed);
    }

    private loginSuccess(data: any) {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
    }

    private loginFailed(data: any) {
        this.errorMessage = data.error.message;
        this.isLoginFailed = true;
    }

    reloadPage() {
        window.location.reload();
    }

    ngOnDestroy(): void {
        this.renderer.removeClass(document.body, "login-body");
    }

}
