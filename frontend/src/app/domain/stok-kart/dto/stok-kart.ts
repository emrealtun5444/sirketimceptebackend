import {KdvOrani, ParaBirimi} from "../../../shared/constants";

export class StokKart {

    id: number;
    barkod: string;
    modelKodu: string;
    paraBirimi: ParaBirimi;
    urunAdi: string;
    urunFiyat: number;
    stokAdedi: number;
    stokKodu: string;
    kdvOrani: KdvOrani;
    urunGorsel: string;

}
