import {SorguSonucu} from "../../../shared/datatable/sorgu-sonucu";

export interface AsenkronRaporBilgiSorguSonucu extends SorguSonucu {
    id: number;
    raporTuru: string;
    raporOlusmaDurumu: string;
    islemCevap: string;
    documentName: string;
    raporOlusmaTarihi: Date;
    kullanici: string;
}
