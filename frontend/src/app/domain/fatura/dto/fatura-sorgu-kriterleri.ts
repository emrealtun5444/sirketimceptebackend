import {LazyLoadEvent} from 'primeng/api';

export interface FaturaSorguKriterleri {
  staff: number;
  faturaNo: string;
  cariKodu: string;
  cariAdi: string;
  faturaBaslangicTarihi: Date;
  faturaBitisTarihi: Date;
  lazyLoadEvent?: LazyLoadEvent;
}
