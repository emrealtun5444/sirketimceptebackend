import {Component} from '@angular/core';
import {MenuService} from './app.menu.service';
import {AppStore} from "./shared/app.store";
import {Router} from "@angular/router";
import {BnNgIdleService} from "bn-ng-idle";
import {Constants} from "./shared/constants";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent {

    public menuMode = 'popup';

    public menuActive = false;

    public topbarMenuActive = false;

    activeTopbarItem: Element;

    menuClick: boolean;

    menuButtonClick: boolean;

    topbarMenuButtonClick: boolean;

    menuHoverActive: boolean;

    constructor(private menuService: MenuService,
                public appStore: AppStore,
                private router: Router,
                private bnIdle: BnNgIdleService) {
        this.bnIdle.startWatching(Constants.sessionTimeOut).subscribe((res) => {
            if (res) {
                console.log("session expired");
                this.signOut();
            }
        })
    }

    onMenuButtonClick(event: Event) {
        this.menuButtonClick = true;
        this.menuActive = !this.menuActive;
        event.preventDefault();
    }

    onTopbarMenuButtonClick(event: Event) {
        this.topbarMenuButtonClick = true;
        this.topbarMenuActive = !this.topbarMenuActive;
        event.preventDefault();
    }

    onTopbarItemClick(event: Event, item: Element) {
        this.topbarMenuButtonClick = true;

        if (this.activeTopbarItem === item) {
            this.activeTopbarItem = null;
        } else {
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

    signOut() {
        this.router.navigate(['logout']);
    }

    isLoggedIn() {
        return !!this.appStore.tokenStorage.getToken();
    }
}
