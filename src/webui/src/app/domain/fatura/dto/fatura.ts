import { Durum, FaturaYonu} from "../../../shared/constants";

export class Fatura {
    id: number;
    cariKodu: string;
    cariAdi: string;
    faturaYonu: FaturaYonu;
    faturaTarihi: Date;
    faturaNo: string;

    malBedeli: number;
    iskonto: number;
    netToplam: number;
    kdvTutari: number;
    toplamTutar: number;

    durum: Durum;

}
