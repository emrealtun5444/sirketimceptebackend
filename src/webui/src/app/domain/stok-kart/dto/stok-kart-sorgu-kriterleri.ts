import {LazyLoadEvent} from "primeng";

export interface StokKartSorguKriterleri {

    stokKodu: string;

    urunAdi: string;

    stokAdedi: number;

    lazyLoadEvent?: LazyLoadEvent;

}
