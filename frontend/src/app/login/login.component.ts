import {Component, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/service/auth.service";
import {TokenStorageService} from "../shared/service/token-storage.service";
import {AbstractBaseComponent} from "../shared/abstract-base-component";
import {AppStore} from "../shared/app.store";
import {CustomValidator} from "../shared/validation/custom-validator";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

    entityForm: FormGroup;

    errorMessage = '';
    roles: string[] = [];
    returnUrl: string;

    constructor(appStore: AppStore,
                private authService: AuthService,
                private renderer: Renderer2,
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
        this.renderer.addClass(document.body, "login-body");
        // get return url from route parameters or default to '/'
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
        this.renderer.removeClass(document.body, "login-body");
    }

}
