export enum HttpStatus {
  OK = 200, // Success
  BAD_REQUEST = 400, // ServiceException, HukukServiceException
  INTERNAL_SERVER_ERROR = 500, // Throwable
}

export class Constants {
  static readonly TURKIYE = 100000;
  static sessionTimeOut = 3600;
  static readonly DATE_FMT = 'dd.MM.yyyy';
  static readonly DATE_TIME_FMT = `${Constants.DATE_FMT} HH:mm:ss`;
  static readonly PARA_FMT = '1.2-2';
  static readonly paraBirimiList = [
    {icon: 'fa fa-lira-sign', value: '949', label: 'TRY'},
    {icon: 'fa fa-dollar-sign', value: '840', label: 'USD'},
    {icon: 'fa fa-euro-sign', value: '978', label: 'EUR'},
    {icon: '', value: '960', label: 'SDR'},
    {icon: '', value: '36', label: 'AUD'},
    {icon: '', value: '214', label: 'DKK'},
    {icon: 'fa fa-pound-sign', value: '826', label: 'GBP'},
    {icon: '', value: '756', label: 'CHF'},
    {icon: '', value: '752', label: 'SEK'},
    {icon: '', value: '124', label: 'CAD'},
    {icon: '', value: '414', label: 'KWD'},
    {icon: '', value: '578', label: 'NOK'},
    {icon: '', value: '682', label: 'SAR'},
    {icon: 'fa fa-yen-sign', value: '392', label: 'JPY'},
    {icon: '', value: '975', label: 'BGN'},
    {icon: '', value: '946', label: 'RON'},
    {icon: 'fa fa-ruble-sign', value: '643', label: 'RUB'},
    {icon: '', value: '364', label: 'IRR'},
    {icon: 'fa fa-yen-sign', value: '156', label: 'CNY'},
    {icon: '', value: '586', label: 'PKR'},
    {icon: 'fa fa-coins', value: '479', label: 'GR'},
  ];
  static readonly LAZY_DATATABLE_EXCEL_REPORT_WARNING_LIMIT = 30000;
}

export enum BelgeTipi {
  FIYAT_LISTESI = 'FIYAT_LISTESI',
  KATALOG = 'KATALOG',
  KAMPANYA = 'KAMPANYA',
  FATURA = 'FATURA',
  BELGE = 'BELGE',
  SOZLESME = 'SOZLESME',
  DIGER = 'DIGER'
}

export enum ParaBirimi {
  TRY = 'TRY',
  USD = 'USD',
  EURO = 'EURO'
}

export enum KdvOrani {
  KDV_ORANI_8 = 'KDV_ORANI_8',
  KDV_ORANI_18 = 'KDV_ORANI_18'
}

export enum CariTipi {
  BAGLANTILI_CALISAN = 'BAGLANTILI_CALISAN',
  ACIKHESAP_CALISAN = 'ACIKHESAP_CALISAN',
  ODEME_YAPARAK_CALISAN = 'ODEME_YAPARAK_CALISAN',
  PROJE = 'PROJE',
  PERAKENDE = 'PERAKENDE',
  ETICARET = 'ETICARET'
}

export enum Durum {
  AKTIF = 'AKTIF',
  PASIF = 'PASIF',
  MANTIKSAL_SILINMIS = 'MANTIKSAL_SILINMIS'
}

export enum FaturaYonu {
  BORC = 'BORC',
  ALACAK = 'ALACAK'
}

export enum MarketPlace {
  N11 = 'N11',
  TRENDYOL = 'TRENDYOL',
  HEPSIBURADA = 'HEPSIBURADA',
  PTTAVM = 'PTTAVM',
  AMAZON = 'AMAZON',
  GITTIGIDIYOR = 'GITTIGIDIYOR',
  ALIBABA = 'ALIBABA',
  CICEKSEPETI = 'CICEKSEPETI'
}

export enum SiparisDurumu {
  ACIK = 'ACIK',
  KISMI_SEVKEDILDI = 'KISMI_SEVKEDILDI',
  TAMAMLANDI = 'TAMAMLANDI'
}

export enum SiparisYonu {
  ALINAN_SIPARIS = 'ALINAN_SIPARIS',
  VERILEN_SIPARIS = 'VERILEN_SIPARIS'
}

export enum OdemeYonu {
  BORC = 'BORC',
  ALACAK = 'ALACAK'
}

export enum OdemeTipi {
  NAKIT = 'NAKIT',
  KREDI_KARTI = 'KREDI_KARTI',
  CEK = 'CEK',
  SENET = 'SENET'
}

export enum RaporTuru {
  CARI_MALIYET_RAPORU = "CARI_MALIYET_RAPORU",
  MARKA_MALIYET_RAPORU = "MARKA_MALIYET_RAPORU",
  CARI_DONEM_CIRO_RAPORU = "CARI_DONEM_CIRO_RAPORU",
  CAR_DNM_TAHSILAT_RAPORU = "CAR_DNM_TAHSILAT_RAPORU",
  STOK_BEKLEYEN = "STOK_BEKLEYEN",
  SIPARIS_BEKLEYEN = "SIPARIS_BEKLEYEN",
  BOS = "BOS"
}

export enum RaporOlusmaDurumu {
  ISLENMEYI_BEKLIYOR = "ISLENMEYI_BEKLIYOR",
  ISLENIYOR = "ISLENIYOR",
  BASARILI = "BASARILI",
  BASARISIZ = "BASARISIZ"
}