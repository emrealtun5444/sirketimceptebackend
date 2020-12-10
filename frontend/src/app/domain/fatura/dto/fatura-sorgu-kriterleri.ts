import {LazyLoadEvent} from 'primeng/api';

export interface FaturaSorguKriterleri {
    cariKodu: string;
    cariAdi: string;
    faturaBaslangicTarihi: Date;
    faturaBitisTarihi: Date;
    lazyLoadEvent?: LazyLoadEvent;
}
