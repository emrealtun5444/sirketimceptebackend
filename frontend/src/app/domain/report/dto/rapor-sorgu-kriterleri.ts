import {RaporTuru} from "../../../shared/constants";

export interface RaporSorguKriterleri {
    baslangicTarihi: Date;
    bitisTarihi: Date;
    raporTuru: RaporTuru;
}
