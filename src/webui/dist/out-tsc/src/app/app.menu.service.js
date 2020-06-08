import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
let MenuService = class MenuService {
    constructor() {
        this.menuSource = new Subject();
        this.resetSource = new Subject();
        this.menuSource$ = this.menuSource.asObservable();
        this.resetSource$ = this.resetSource.asObservable();
    }
    onMenuStateChange(key) {
        this.menuSource.next(key);
    }
    reset() {
        this.resetSource.next();
    }
};
MenuService = __decorate([
    Injectable()
], MenuService);
export { MenuService };
//# sourceMappingURL=app.menu.service.js.map