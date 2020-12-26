import {SiparisDurumu, SiparisYonu} from "../../../shared/constants";

export class Siparis {
  id: number;
  malKodu: string;
  urunAdi: string;
  islemTarihi: Date;
  siparisYonu: SiparisYonu;
  cariKodu: string;
  cariAdi: string;
  siparisNo: string;
  miktar: number;
  kalanMiktar: number;
  stokMiktari: number;
  birimFiyati: number;
  tutari: number;
  iskonto: number;
  kdvTutari: number;
  faturaNo: string;
  teslimMiktari: number;
  teslimTarihi: Date;
  siparisDurumu: SiparisDurumu;
}
