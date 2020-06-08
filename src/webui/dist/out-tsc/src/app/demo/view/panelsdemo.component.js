import { __decorate } from "tslib";
import { Component } from '@angular/core';
let PanelsDemoComponent = class PanelsDemoComponent {
    ngOnInit() {
        this.items = [
            { label: 'Angular.io', icon: 'fa fa-link', url: 'http://angular.io' },
            { label: 'Theming', icon: 'fa fa-book', routerLink: ['/theming'] }
        ];
    }
};
PanelsDemoComponent = __decorate([
    Component({
        templateUrl: './panelsdemo.component.html',
        styles: [`
        :host ::ng-deep button {
            margin-right: .25em;
        }
    `]
    })
], PanelsDemoComponent);
export { PanelsDemoComponent };
//# sourceMappingURL=panelsdemo.component.js.map