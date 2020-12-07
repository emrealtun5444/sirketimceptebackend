import {Durum, KdvOrani} from "../../../shared/constants";

export class FaturaDetay {
    id: number;
    stokKodu: string;
    urunAdi: string;
    islemTarihi: Date;
    miktar: number;
    birimFiyati: number;
    tutar: number;
    iskonto: number;
    kdvTutari: number;
    toplamTutar: number;

    kdvOrani: KdvOrani;
    durum: Durum;

}
