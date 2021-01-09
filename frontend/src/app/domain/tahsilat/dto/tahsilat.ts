import {KdvOrani, OdemeTipi, OdemeYonu} from "../../../shared/constants";

export class Tahsilat {
    id: number;
    cariKodu: string;
    cariAdi: string;
    islemTarihi: Date;
    evrakNo: string;
    odemeYonu: OdemeYonu;
    odemeTipi: OdemeTipi;
    tutar: number;
    vadeTarihi: Date;
    kdvOrani: KdvOrani;
    aciklama: string;

    banka: string;
    subeAdi: string;
    bankaHesapNo: string;
    bankaCekNo: string;
    borcluAdi: string;
    borcluAdresi: string;

}
