import { __decorate } from "tslib";
import { Component, Input } from '@angular/core';
import { NavigationEnd } from '@angular/router';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { filter } from 'rxjs/operators';
let AppMenuitemComponent = class AppMenuitemComponent {
    constructor(app, router, cd, menuService) {
        this.app = app;
        this.router = router;
        this.cd = cd;
        this.menuService = menuService;
        this.active = false;
        this.menuSourceSubscription = this.menuService.menuSource$.subscribe(key => {
            // deactivate current active menu
            if (this.active && this.key !== key && key.indexOf(this.key) !== 0) {
                this.active = false;
            }
        });
        this.menuResetSubscription = this.menuService.resetSource$.subscribe(() => {
            this.active = false;
        });
        this.router.events.pipe(filter(event => event instanceof NavigationEnd))
            .subscribe(params => {
            if (this.app.isHorizontal()) {
                this.active = false;
            }
            else {
                if (this.item.routerLink) {
                    this.updateActiveStateFromRoute();
                }
                else {
                    this.active = false;
                }
            }
        });
    }
    ngOnInit() {
        if (!this.app.isHorizontal() && this.item.routerLink) {
            this.updateActiveStateFromRoute();
        }
        this.key = this.parentKey ? this.parentKey + '-' + this.index : String(this.index);
    }
    updateActiveStateFromRoute() {
        this.active = this.router.isActive(this.item.routerLink[0], this.item.items ? false : true);
    }
    itemClick(event) {
        // avoid processing disabled items
        if (this.item.disabled) {
            event.preventDefault();
            return true;
        }
        // navigate with hover in horizontal mode
        if (this.root) {
            this.app.menuHoverActive = !this.app.menuHoverActive;
        }
        // notify other items
        this.menuService.onMenuStateChange(this.key);
        // execute command
        if (this.item.command) {
            this.item.command({ originalEvent: event, item: this.item });
        }
        // toggle active state
        if (this.item.items) {
            this.active = !this.active;
        }
        else {
            // activate item
            this.active = true;
            // reset horizontal menu
            if (this.app.isHorizontal()) {
                this.menuService.reset();
            }
            if (this.app.isMobile() || this.app.menuMode === 'overlay' || this.app.menuMode === 'popup') {
                this.app.menuActive = false;
            }
            this.app.menuHoverActive = false;
        }
    }
    onMouseEnter() {
        // activate item on hover
        if (this.root && this.app.menuHoverActive && this.app.isHorizontal() && !this.app.isMobile() && !this.app.isTablet()) {
            this.menuService.onMenuStateChange(this.key);
            this.active = true;
        }
    }
    ngOnDestroy() {
        if (this.menuSourceSubscription) {
            this.menuSourceSubscription.unsubscribe();
        }
        if (this.menuResetSubscription) {
            this.menuResetSubscription.unsubscribe();
        }
    }
};
__decorate([
    Input()
], AppMenuitemComponent.prototype, "item", void 0);
__decorate([
    Input()
], AppMenuitemComponent.prototype, "index", void 0);
__decorate([
    Input()
], AppMenuitemComponent.prototype, "root", void 0);
__decorate([
    Input()
], AppMenuitemComponent.prototype, "parentKey", void 0);
AppMenuitemComponent = __decorate([
    Component({
        /* tslint:disable:component-selector */
        selector: '[app-menuitem]',
        /* tslint:enable:component-selector */
        template: `
          <ng-container>
              <a [attr.href]="item.url" (click)="itemClick($event)" *ngIf="!item.routerLink || item.items" (mouseenter)="onMouseEnter()"
                 (keydown.enter)="itemClick($event)" [attr.target]="item.target" [attr.tabindex]="0">
				  <i [ngClass]="item.icon"></i>
				  <span>{{item.label}}</span>
				  <i class="fa fa-fw fa-angle-down layout-submenu-toggler" *ngIf="item.items"></i>
				  <span class="menuitem-badge" *ngIf="item.badge" [ngClass]="item.badgeStyleClass">{{item.badge}}</span>
              </a>
              <a (click)="itemClick($event)" (mouseenter)="onMouseEnter()" *ngIf="item.routerLink && !item.items"
                  [routerLink]="item.routerLink" routerLinkActive="active-menuitem-routerlink"
                  [routerLinkActiveOptions]="{exact: true}" [attr.target]="item.target" [attr.tabindex]="0">
				  <i [ngClass]="item.icon"></i>
				  <span>{{item.label}}</span>
				  <i class="fa fa-fw fa-angle-down layout-submenu-toggler" *ngIf="item.items"></i>
				  <span class="menuitem-badge" *ngIf="item.badge" [ngClass]="item.badgeStyleClass">{{item.badge}}</span>
              </a>
              <ul *ngIf="item.items && active" [@children]="(active ? 'visibleAnimated' : 'hiddenAnimated')">
                  <ng-template ngFor let-child let-i="index" [ngForOf]="item.items">
                      <li app-menuitem [item]="child" [index]="i" [parentKey]="key" [class]="child.badgeClass"></li>
                  </ng-template>
              </ul>
          </ng-container>
      `,
        host: {
            '[class.active-menuitem]': 'active'
        },
        animations: [
            trigger('children', [
                state('void', style({
                    height: '0px'
                })),
                state('hiddenAnimated', style({
                    height: '0px'
                })),
                state('visibleAnimated', style({
                    height: '*'
                })),
                transition('visibleAnimated => hiddenAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
                transition('hiddenAnimated => visibleAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
                transition('void => visibleAnimated, visibleAnimated => void', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
            ])
        ]
    })
], AppMenuitemComponent);
export { AppMenuitemComponent };
//# sourceMappingURL=app.menuitem.component.js.map