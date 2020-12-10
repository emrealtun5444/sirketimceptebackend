import {Component, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AppStore} from '../shared/app.store';
import {AuthService} from '../shared/service/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../shared/service/token-storage.service';
import {CustomValidator} from '../shared/validation/custom-validator';
import {AbstractBaseComponent} from '../shared/abstract-base-component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

    entityForm: FormGroup;

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
            name: [null, Validators.compose([CustomValidator.required])],
            surname: [null, Validators.compose([CustomValidator.required])],
            email: [null, Validators.compose([CustomValidator.email, Validators.required])],
            username: [null, Validators.compose([CustomValidator.required])],
            password: [null, Validators.compose([CustomValidator.required])]
        });
    }

    ngOnInit() {
        this.setup();
        this.buildForms();
    }

    private setup() {
    }

    onSubmit() {
        this.subscribeToResponse(this.authService.register(this.entityForm.value), this.registerSuccess, undefined);
    }

    private registerSuccess(data: any) {
        this.appStore.addMessage({
            severity: 'success',
            summary: this.appStore.translate.instant('success.kullanici.olusturuldu')
        }, false);
        this.router.navigate(['/login']);
    }

    ngOnDestroy(): void {
    }


}
