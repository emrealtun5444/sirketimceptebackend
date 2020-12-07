import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractBaseComponent, ColumnType} from "../../../../shared/abstract-base-component";
import {AppStore} from "../../../../shared/app.store";
import {MarketPlaceService} from "../../service/market-place.service";
import {MarketPlaceConf} from "../../dto/market-place-conf";

@Component({
    selector: 'app-market-place',
    templateUrl: './market-place.component.html',
    styleUrls: ['./market-place.component.css']
})
export class MarketPlaceComponent extends AbstractBaseComponent implements OnInit {

    sorguForm: FormGroup;
    cols: any[] = [
        {type: ColumnType.STRING, field: 'marketPlace', header: this.appStore.translate.instant('label.market.place')},
        {type: ColumnType.STRING, field: 'sellerId', header: this.appStore.translate.instant('label.seller.id')},
        {type: ColumnType.STRING, field: 'apiKey', header: this.appStore.translate.instant('label.api.key')},
        {type: ColumnType.STRING, field: 'apiSecret', header: this.appStore.translate.instant('label.api.secret')},
        {type: ColumnType.STRING, field: 'durum', header: this.appStore.translate.instant('label.durum')},
    ];


    resultList: MarketPlaceConf[];

    constructor(public appStore: AppStore,
                private marketPlaceService: MarketPlaceService,
                private formBuilder: FormBuilder) {
        super(appStore);
    }

    ngOnInit(): void {
        this.sorgula();
    }

    private buildForms() {
        this.sorguForm = this.formBuilder.group({});
    }

    sorgula() {
        let event = {first: 0, rows: 20};
        this.subscribeToResponseBase(this.marketPlaceService.loadMarketPlaces(), this.sorgulaSuccess, undefined);
    }


    private sorgulaSuccess(data: any) {
        this.resultList = data;
    }

    private prepareData(): MarketPlaceConf {
        const formModel = this.sorguForm.value;
        return {
            id: formModel.id,
            sellerId: formModel.sellerId,
            apiKey: formModel.apiKey,
            apiSecret: formModel.apiSecret,
            durum: formModel.durum
        };
    }

}
