import {SiparisDurumu, SiparisYonu} from "../../../shared/constants";
import {LazyLoadEvent} from "primeng/api";

export interface SiparisSorguKriterleri {
  staff: number;
  siparisNo: string;
  cariKodu: string;
  cariAdi: string;
  siparisDurumu: SiparisDurumu;
  siparisYonu: SiparisYonu;
  baslangicTarihi: Date;
  bitisTarihi: Date;
  stokKodu: string;
  urunAdi: string;

  lazyLoadEvent?: LazyLoadEvent;
}
