import {LazyLoadEvent} from "primeng";

export interface FaturaSorguKriterleri {

    cariKodu:string;
    cariAdi:string;
    faturaBaslangicTarihi:Date;
    faturaBitisTarihi:Date;

    lazyLoadEvent?: LazyLoadEvent;

}
