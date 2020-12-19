import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractBaseComponent, ColumnType, Operations} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {ConfirmationService} from "primeng/api";
import {RoleService} from "../../service/role.service";
import {RoleSorguSonucu} from "../../dto/role-sorgu-sonucu";


@Component({
  selector: 'app-role-sorgula',
  templateUrl: './role-sorgula.component.html',
  styleUrls: ['./role-sorgula.component.css']
})
export class RoleSorgulaComponent extends AbstractBaseComponent implements OnInit {

  sorguForm: FormGroup;
  showRoleForm = false;
  selectedRoleId: number;

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
    {type: ColumnType.STRING, field: 'name', header: this.appStore.translate.instant('label.role.adi')},
  ];


  resultList: RoleSorguSonucu[];

  constructor(public appStore: AppStore,
              private roleService: RoleService,
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
    this.subscribeToResponseBase(this.roleService.all(), this.sorgulaSuccess, undefined);
  }

  private sorgulaSuccess(data: any) {
    this.resultList = data;
  }

  showUpdatedialog(row) {
    this.selectedRoleId = row.id;
    this.showRoleForm = true;
  }

  onAddNewRole() {
    this.showRoleForm = true;
    this.selectedRoleId = null;
  }

  onChangeShowRoleFormClose(event) {
    this.showRoleForm = event;
    this.sorgula();
  }

  deleteConfirm(row) {
    this.confirmationService.confirm({
      key: 'delete',
      message: this.appStore.translate.instant('info.sure.continue.process'),
      accept: () => {
        this.deleteRole(row);
      }
    });
  }

  deleteRole(row) {
    this.subscribeToResponseBase(this.roleService.delete(row.id), data => {
      this.appStore.addMessage({
        severity: 'success',
        summary: this.appStore.translate.instant('success.role.silindi')
      }, true);
      this.sorgula();
    }, undefined);
  }

}
