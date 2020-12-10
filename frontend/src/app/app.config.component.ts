import {Component, OnInit} from '@angular/core';
import { AppMainComponent } from './app.main.component';

@Component({
    selector: 'app-config',
    template: `
        <a style="cursor: pointer" id="layout-config-button" class="layout-config-button" (click)="onConfigButtonClick($event)">
            <i class="pi pi-cog"></i>
        </a>
        <div class="layout-config" [ngClass]="{'layout-config-active': app.configActive}" (click)="app.onConfigClick($event)">
            <h5>Menu Type</h5>
            <div class="p-field-radiobutton">
                <p-radioButton name="menuMode" value="static" [(ngModel)]="app.menuMode" inputId="mode1"></p-radioButton>
                <label for="mode1">Static</label>
            </div>
            <div class="p-field-radiobutton">
                <p-radioButton name="menuMode" value="overlay" [(ngModel)]="app.menuMode" inputId="mode2"></p-radioButton>
                <label for="mode2">Overlay</label>
            </div>
            <div class="p-field-radiobutton">
                <p-radioButton name="menuMode" value="popup" [(ngModel)]="app.menuMode" inputId="mode3"></p-radioButton>
                <label for="mode3">Popup</label>
            </div>
            <div class="p-field-radiobutton">
                <p-radioButton name="menuMode" value="horizontal" [(ngModel)]="app.menuMode" inputId="mode4"></p-radioButton>
                <label for="mode4">Horizontal</label>
            </div>

            <h5>Input Style</h5>
            <div class="p-field-radiobutton">
                <p-radioButton name="inputStyle" value="outlined" [(ngModel)]="app.inputStyle" inputId="inputStyle1"></p-radioButton>
                <label for="inputStyle1">Outlined</label>
            </div>
            <div class="p-field-radiobutton">
                <p-radioButton name="inputStyle" value="filled" [(ngModel)]="app.inputStyle" inputId="inputStyle2"></p-radioButton>
                <label for="inputStyle2">Filled</label>
            </div>

            <hr />

            <h5>Ripple Effect</h5>
			<p-inputSwitch [ngModel]="app.ripple" (onChange)="app.onRippleChange($event)"></p-inputSwitch>

            <h5>Flat Layout Colors</h5>
            <div class="layout-themes">
                <div *ngFor="let f of flatLayouts">
                    <a style="cursor: pointer" (click)="changeLayout(f.name)" [ngStyle]="{'background-color': f.color}">
                        <i class="pi pi-check" *ngIf="layout === f.name"></i>
                    </a>
                </div>
            </div>

            <h5>Special Layout Colors</h5>
            <div class="layout-themes">
                <div *ngFor="let s of specialLayouts">
                    <a style="cursor: pointer" (click)="changeLayout(s.name)"
                       [ngStyle]="{'background-image': 'linear-gradient(to right, ' + s.color1 +','+ s.color2+')'} ">
                        <i class="pi pi-check" *ngIf="layout === s.name"></i>
                    </a>
                </div>
            </div>

            <h5>Themes</h5>
            <div class="layout-themes">
                <div *ngFor="let t of themes">
                    <a style="cursor: pointer" (click)="changeTheme(t.name)" [ngStyle]="{'background-color': t.color}">
                        <i class="pi pi-check" *ngIf="theme === t.name"></i>
                    </a>
                </div>
            </div>
        </div>
    `
})
export class AppConfigComponent implements OnInit {

    flatLayouts: any[];

    specialLayouts: any[];

    themes: any[];

    theme = 'blue';

    layout = 'blue';

    constructor(public app: AppMainComponent) {}

    ngOnInit() {
        this.flatLayouts = [
            {name: 'dark', color: '#3b3b48'},
            {name: 'turquoise', color: '#04838f'},
            {name: 'green', color: '#1e8455'},
            {name: 'blue', color: '#2461cc'},
            {name: 'rose', color: '#79425a'},
            {name: 'teal', color: '#427976'},
            {name: 'bluegrey', color: '#37474f'},
            {name: 'purple', color: '#5d4279'},
        ];

        this.specialLayouts = [
            {name: 'cosmic', color1: '#517fa4', color2: '#243949'},
            {name: 'lawrencium', color1: '#302b63', color2: '#201B4C'},
            {name: 'couple', color1: '#3a6186', color2: '#89253e'},
            {name: 'stellar', color1: '#7474BF', color2: '#348AC7'},
            {name: 'beach', color1: '#00cdac', color2: '#02aab0'},
            {name: 'flow', color1: '#136a8a', color2: '#267871'},
            {name: 'fly', color1: '#7b4397', color2: '#b22f64'},
            {name: 'nepal', color1: '#614385', color2: '#516395'},
            {name: 'celestial', color1: '#734b6d', color2: '#734b6d'},
        ];

        this.themes = [
            {name: 'green', color: '#9fd037'},
            {name: 'teal', color: '#12b886'},
            {name: 'blue', color: '#3ebaf8'},
            {name: 'amber', color: '#f7cb00'},
            {name: 'purple', color: '#966af1'},
            {name: 'turquoise', color: '#2ab1be'},
            {name: 'bluegrey', color: '#546E7A'}
        ];
    }

    changeTheme(theme) {
        this.theme = theme;
        const themeLink: HTMLLinkElement = document.getElementById('theme-css') as HTMLLinkElement;
        const href = 'assets/theme/theme-' + theme + '.css';

        this.replaceLink(themeLink, href);
    }
    changeLayout(layout) {
        this.layout = layout;
        const layoutLink: HTMLLinkElement = document.getElementById('layout-css') as HTMLLinkElement;
        const href = 'assets/layout/css/layout-' + layout + '.css';

        this.replaceLink(layoutLink, href);
    }

    isIE() {
        return /(MSIE|Trident\/|Edge\/)/i.test(window.navigator.userAgent);
    }

    replaceLink(linkElement, href) {
        if (this.isIE()) {
            linkElement.setAttribute('href', href);
        } else {
            const id = linkElement.getAttribute('id');
            const cloneLinkElement = linkElement.cloneNode(true);

            cloneLinkElement.setAttribute('href', href);
            cloneLinkElement.setAttribute('id', id + '-clone');

            linkElement.parentNode.insertBefore(cloneLinkElement, linkElement.nextSibling);

            cloneLinkElement.addEventListener('load', () => {
                linkElement.remove();
                cloneLinkElement.setAttribute('id', id);
            });
        }
    }

    onConfigButtonClick(event) {
        this.app.configActive = !this.app.configActive;
        this.app.configClick = true;
        event.preventDefault();
    }
}
