import {LazyLoadEvent} from 'primeng/api';

export interface CariKartSorguKriterleri {

    staff: number;
    cariKodu:string;
    cariAdi:string;

    lazyLoadEvent?: LazyLoadEvent;

}
