import {LazyLoadEvent} from 'primeng/api';

export interface StokKartSorguKriterleri {

    stokKodu: string;

    urunAdi: string;

    stokAdedi: number;

    lazyLoadEvent?: LazyLoadEvent;

}
