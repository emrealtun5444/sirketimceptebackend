import {OdemeTipi, OdemeYonu} from "../../../shared/constants";
import {LazyLoadEvent} from "primeng/api";

export interface TahsilatSorguKriterleri {
    staff: number;
    evrakNo: string;
    cariKodu: string;
    cariAdi: string;
    odemeTipi: OdemeTipi;
    odemeYonu: OdemeYonu;
    baslangicTarihi: Date;
    bitisTarihi: Date;

    lazyLoadEvent?: LazyLoadEvent;
}
