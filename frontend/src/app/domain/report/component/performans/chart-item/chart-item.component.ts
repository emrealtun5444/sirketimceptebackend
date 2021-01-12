import {Component, Input, OnInit} from '@angular/core';
import {AbstractBaseComponent} from "../../../../../shared/abstract-base-component";
import {AppStore} from "../../../../../shared/app.store";

@Component({
    selector: 'app-chart-item',
    templateUrl: './chart-item.component.html',
    styleUrls: ['./chart-item.component.scss']
})
export class ChartItemComponent extends AbstractBaseComponent implements OnInit {

    @Input() userName: string;
    @Input() year: number;
    @Input() cariKart: string = null;


    constructor(public appStore: AppStore) {
        super(appStore);
    }

    ngOnInit(): void {

    }

}
