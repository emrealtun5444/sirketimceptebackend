import {Durum, MarketPlace} from "../../../shared/constants";

export class MarketPlaceConf {
    id: number;
    marketPlace?: MarketPlace;
    sellerId: string;
    apiKey: string;
    apiSecret: string;
    durum: Durum;
}
