import { __decorate } from "tslib";
import { Component } from '@angular/core';
let AppComponent = class AppComponent {
    constructor(menuService) {
        this.menuService = menuService;
        this.menuMode = 'horizontal';
        this.menuActive = true;
        this.topbarMenuActive = false;
    }
    onMenuButtonClick(event) {
        this.menuButtonClick = true;
        this.menuActive = !this.menuActive;
        event.preventDefault();
    }
    onTopbarMenuButtonClick(event) {
        this.topbarMenuButtonClick = true;
        this.topbarMenuActive = !this.topbarMenuActive;
        event.preventDefault();
    }
    onTopbarItemClick(event, item) {
        this.topbarMenuButtonClick = true;
        if (this.activeTopbarItem === item) {
            this.activeTopbarItem = null;
        }
        else {
            this.activeTopbarItem = item;
        }
        event.preventDefault();
    }
    onTopbarSubItemClick(event) {
        event.preventDefault();
    }
    onLayoutClick() {
        if (!this.menuButtonClick && !this.menuClick) {
            if (this.menuMode === 'horizontal') {
                this.menuService.reset();
            }
            if (this.isMobile() || this.menuMode === 'overlay' || this.menuMode === 'popup') {
                this.menuActive = false;
            }
            this.menuHoverActive = false;
        }
        if (!this.topbarMenuButtonClick) {
            this.activeTopbarItem = null;
            this.topbarMenuActive = false;
        }
        this.menuButtonClick = false;
        this.menuClick = false;
        this.topbarMenuButtonClick = false;
    }
    onMenuClick() {
        this.menuClick = true;
    }
    isMobile() {
        return window.innerWidth < 1025;
    }
    isHorizontal() {
        return this.menuMode === 'horizontal';
    }
    isTablet() {
        const width = window.innerWidth;
        return width <= 1024 && width > 640;
    }
};
AppComponent = __decorate([
    Component({
        selector: 'app-root',
        templateUrl: './app.component.html'
    })
], AppComponent);
export { AppComponent };
//# sourceMappingURL=app.component.js.map