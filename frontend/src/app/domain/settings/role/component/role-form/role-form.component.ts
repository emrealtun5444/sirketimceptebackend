import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";
import {CustomValidator} from "../../../../../shared/validation/custom-validator";
import {ConfirmationService, TreeNode} from "primeng/api";
import {RoleService} from "../../service/role.service";
import {Role} from "../../dto/role";

@Component({
  selector: 'app-role-form',
  templateUrl: './role-form.component.html',
  styleUrls: ['./role-form.component.scss']
})
export class RoleFormComponent extends AbstractBaseComponent implements OnInit, OnDestroy {

  @Input() display: boolean;
  @Input() roleId: number;
  @Output() displayChange = new EventEmitter();

  entityForm: FormGroup;
  allAuthorizations: TreeNode[];
  selectedNodes: TreeNode[] = [];

  constructor(private roleService: RoleService,
              appStore: AppStore,
              private confirmationService: ConfirmationService,
              private formBuilder: FormBuilder) {
    super(appStore);
  }

  ngOnInit(): void {
    this.subscribeToResponse(this.roleService.authorizations(), data => {
      this.allAuthorizations = data;
      if (this.roleId) {
        this.subscribeToResponse(this.roleService.roleById(this.roleId), roleData => {
          this.allAuthorizations.forEach(auth => {
            this.expandRecursive(auth, roleData.authorizations);
          });
          this.buildForms(roleData);
        }, undefined);
      } else {
        this.buildForms(null);
      }
    }, undefined);
  }

  private expandRecursive(node: TreeNode, selectedItems: string[]) {
    if (selectedItems.includes(node.data)) {
      this.selectedNodes.push(node);
      node.expanded = true;
    }
    if (node.children) {
      node.children.forEach(childNode => {
        this.expandRecursive(childNode, selectedItems);
      });
    }
  }

  private buildForms(roleData) {
    this.entityForm = this.formBuilder.group({
      name: [roleData ? roleData.name : null, Validators.compose([CustomValidator.required])],
    });
  }

  confirm(event: Event) {
    this.confirmationService.confirm({
      key: 'update',
      message: this.appStore.translate.instant('info.sure.continue.process'),
      accept: () => {
        this.onSubmit();
      }
    });
  }

  onSubmit() {
    if (this.roleId) {
      this.subscribeToResponse(this.roleService.update(this.roleId, this.prepareData()), data => {
        this.appStore.addMessage({
          severity: 'success',
          summary: this.appStore.translate.instant('success.role.guncellendi')
        }, true);
        this.onClose();
      }, undefined);
    } else {
      this.subscribeToResponse(this.roleService.add(this.prepareData()), data => {
        this.appStore.addMessage({
          severity: 'success',
          summary: this.appStore.translate.instant('success.role.olusturuldu')
        }, true);
        this.onClose();
      }, undefined);
    }
  }

  private prepareData(): Role {
    const selectedAuth = this.selectedNodes.map(row => row.data);
    const formModel = this.entityForm.value;
    return {
      id: this.roleId,
      name: formModel.name,
      authorizations: selectedAuth
    };
  }

  onClose() {
    this.displayChange.emit(false);
  }

  // Work against memory leak if component is destroyed
  ngOnDestroy() {
    this.displayChange.unsubscribe();
  }

}
