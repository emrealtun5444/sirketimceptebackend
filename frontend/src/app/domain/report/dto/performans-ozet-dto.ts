import {CiroDto} from "./ciro-dto";
import {TahsilatDto} from "./tahsilat-dto";
import {SiparisDto} from "./siparis-dto";
import {HedefDto} from "./hedef-dto";

export class PerformansOzetDto {

  donem: string;
  aylikCiro: CiroDto;
  yillikCiro: CiroDto;
  aylikHedef: HedefDto;
  yillikHedef: HedefDto;
  aylikTahsilat: TahsilatDto;
  yillikTahsilat: TahsilatDto;
  aylikSiparis: SiparisDto;
  yillikSiparis: SiparisDto;

}
