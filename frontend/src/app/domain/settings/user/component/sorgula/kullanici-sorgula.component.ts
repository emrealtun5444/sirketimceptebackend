import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractBaseComponent, ColumnType, Operations} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {UserService} from "../../service/user.service";
import {UserSorguSonucu} from "../../dto/user-sorgu-sonucu";
import {ConfirmationService} from "primeng/api";


@Component({
  selector: 'app-kullanici-sorgula',
  templateUrl: './kullanici-sorgula.component.html',
  styleUrls: ['./kullanici-sorgula.component.css']
})
export class KullaniciSorgulaComponent extends AbstractBaseComponent implements OnInit {

  sorguForm: FormGroup;
  showUserForm = false;
  selectedUserId: number;

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
    {type: ColumnType.STRING, field: 'nameSurname', header: this.appStore.translate.instant('label.ad.soyad')},
    {type: ColumnType.STRING, field: 'username', header: this.appStore.translate.instant('label.kullanici.adi')},
    {type: ColumnType.STRING, field: 'email', header: this.appStore.translate.instant('label.email')},
    {type: ColumnType.STRING, field: 'roleNames', header: this.appStore.translate.instant('label.roles')},
    {type: ColumnType.STRING, field: 'companyNames', header: this.appStore.translate.instant('label.companies')},
  ];


  resultList: UserSorguSonucu[];

  constructor(public appStore: AppStore,
              private userService: UserService,
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
    this.subscribeToResponseBase(this.userService.all(), this.sorgulaSuccess, undefined);
  }

  private sorgulaSuccess(data: any) {
    this.resultList = data;
  }

  showUpdatedialog(row) {
    this.selectedUserId = row.id;
    this.showUserForm = true;
  }

  onAddNewUser() {
    this.showUserForm = true;
    this.selectedUserId = null;
  }

  onChangeShowUserFormClose(event) {
    this.showUserForm = event;
    this.sorgula();
  }

  deleteConfirm(row) {
    this.confirmationService.confirm({
      key: 'delete',
      message: this.appStore.translate.instant('info.sure.continue.process'),
      accept: () => {
        this.deleteUser(row);
      }
    });
  }

  deleteUser(row) {
    this.subscribeToResponseBase(this.userService.delete(row.id), data => {
      this.appStore.addMessage({
        severity: 'info',
        summary: this.appStore.translate.instant('success.kullanici.silindi')
      }, true);
      this.sorgula();
    }, undefined);
  }

}
