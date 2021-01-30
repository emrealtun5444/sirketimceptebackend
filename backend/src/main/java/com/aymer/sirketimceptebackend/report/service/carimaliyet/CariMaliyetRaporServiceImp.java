package com.aymer.sirketimceptebackend.report.service.carimaliyet;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaDetayRepository;
import com.aymer.sirketimceptebackend.report.dto.*;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.ColumnDataType;
import com.aymer.sirketimceptebackend.report.model.Maliyet;
import com.aymer.sirketimceptebackend.report.model.ReportBaseEnum;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.report.repository.MaliyetRepository;
import com.aymer.sirketimceptebackend.report.repository.ReportRepository;
import com.aymer.sirketimceptebackend.report.service.AbstractAsenkronVeriHazirlamaRaporServiceImp;
import com.aymer.sirketimceptebackend.report.service.AsenkronRaporGeneratorService;
import com.aymer.sirketimceptebackend.siparis.model.SiparisYonu;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

@Service("cariMaliyetRaporService")
public class CariMaliyetRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements CariMaliyetRaporService {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private FaturaDetayRepository faturaDetayRepository;

    @Autowired
    private MaliyetRepository maliyetRepository;

    @Autowired
    public CariMaliyetRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
        RaporSorguKriteri sorguKriteri = (RaporSorguKriteri) createSorguKriteri(asenkronRaporBilgi);
        SessionUtils sessionInfo = createSessionInfo(asenkronRaporBilgi);
        final List<Maliyet> maliyetList = maliyetRepository.findAll();

        List list = new LinkedList<CariMaliyetDto>();
        List<CariKart> cariKartList = cariKartRepository.findCariKartByDurumAndSirketOrderBySorumluPersonelAscYillikHedefDesc(EDurum.AKTIF, asenkronRaporBilgi.getSirket());
        cariKartList.forEach(cariKart -> {
            CiroDto ciroDto = reportRepository.amountOfSalesForPeriod(sorguKriteri.getDonem(), sorguKriteri.getYil(), EDurum.AKTIF, EOdemeYonu.BORC, asenkronRaporBilgi.getSirket(), cariKart);
            TahsilatDto tahsilatDto = reportRepository.amountOfTahsilatForPeriod(sorguKriteri.getDonem(), sorguKriteri.getYil(), EDurum.AKTIF, EOdemeYonu.ALACAK, asenkronRaporBilgi.getSirket(), cariKart);
            SiparisDto siparisDto = reportRepository.amountOfSiparisForPeriod(sorguKriteri.getDonem(), sorguKriteri.getYil(), EDurum.AKTIF, SiparisYonu.ALINAN_SIPARIS, asenkronRaporBilgi.getSirket(), cariKart);
            Karlilik karlilik = getkarlilikByCariKart(sorguKriteri, cariKart, maliyetList);
            CariMaliyetDto maliyetDto = CariMaliyetDto.builder()
                    .cariTipi(cariKart.getCariTipi().name())
                    .sorumluPersonel(cariKart.getSorumluPersonel() != null ? cariKart.getSorumluPersonel().getAciklama() : null)
                    .cariKodu(cariKart.getCariKodu())
                    .cariAdi(cariKart.getCariAdi())
                    .toplamCiro(ciroDto.getTutar())
                    .toplamTahsilat(tahsilatDto.getTutar())
                    .bekleyenSiparis(siparisDto.getKalanTutar())
                    .hedef(cariKart.getYillikHedef())
                    .toplamBorc(cariKart.getToplamBorc())
                    .toplamAlacak(cariKart.getToplamAlacak())
                    .bakiye(cariKart.getBakiye())
                    .karlilikOrani(karlilik.getKarlilikOrani())
                    .toplamKar(karlilik.getToplamKarTutari())
                    .build();

            list.add(maliyetDto);
        });
        return list;
    }


    private Karlilik getkarlilikByCariKart(RaporSorguKriteri sorguKriteri, CariKart cariKart, List<Maliyet> maliyetList) {
        List<FaturaDetay> faturaDetays = faturaDetayRepository.faturaKalems(cariKart, EDurum.AKTIF, EOdemeYonu.BORC, sorguKriteri.getDonem(), sorguKriteri.getYil());
        BigDecimal toplamMaliyetTutari = BigDecimal.ZERO;
        BigDecimal toplamSatisTutari = BigDecimal.ZERO;
        BigDecimal toplamKarTutari = BigDecimal.ZERO;
        BigDecimal karlilikOrani = BigDecimal.ZERO;
        for (FaturaDetay faturaDetay : faturaDetays) {
            Maliyet maliyet = maliyetList.stream()
                    .filter(m -> faturaDetay.getStokKart().getMarka().getId().equals(m.getId()) && (m.getBaslangicTarihi().compareTo(faturaDetay.getIslemTarihi()) * faturaDetay.getIslemTarihi().compareTo(m.getBitisTarihi()) >= 0))
                    .findAny()
                    .orElse(null);

            toplamMaliyetTutari = toplamMaliyetTutari.add(faturaDetay.getMaliyetTutari(maliyet));
            toplamSatisTutari = toplamSatisTutari.add(faturaDetay.getSatisTutari());
        }
        toplamKarTutari = toplamSatisTutari.subtract(toplamMaliyetTutari);
        karlilikOrani = toplamSatisTutari.equals(BigDecimal.ZERO) ? BigDecimal.ZERO : toplamKarTutari.divide(toplamSatisTutari, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        return new Karlilik(toplamKarTutari, karlilikOrani);
    }


    @Override
    public MaliyetReportHeader[] getReportHeaders() {
        return MaliyetReportHeader.values();
    }

    enum MaliyetReportHeader implements ReportBaseEnum<String> {
        CARI_TIPI("cariTipi", "Cari Tipi", ColumnDataType.STRING),
        SORUMLU_PERSONEL("sorumluPersonel", "Sorumlu Personel", ColumnDataType.STRING),
        CARI_KODU("cariKodu", "Cari Kodu", ColumnDataType.STRING),
        CARI_ADI("cariAdi", "Cari Adı", ColumnDataType.STRING),
        TOPLAM_CIRO("toplamCiro", "Toplam Ciro", ColumnDataType.MONEY),
        TOPLAM_TAHSILAT("toplamTahsilat", "Toplam Tahsilat", ColumnDataType.MONEY),
        BEKLEYEN_SIPARIS("bekleyenSiparis", "Bekleyen Sipariş Tutarı", ColumnDataType.MONEY),
        HEDEF("hedef", "Yıllık Hedef", ColumnDataType.MONEY),
        TOPLAM_BORC("toplamBorc", "Toplam Borç", ColumnDataType.MONEY),
        TOPLAM_ALACAK("toplamAlacak", "Toplam Alacak", ColumnDataType.MONEY),
        TOPLAM_BAKIYE("bakiye", "Bakiye", ColumnDataType.MONEY),
        TOPLAM_KAR("toplamKar", "Toplam Kar Tutarı", ColumnDataType.MONEY),
        KAR_YUZDE("karlilikOrani", "Karlılık Oranı", ColumnDataType.MONEY);


        private String field;
        private String header;
        private ColumnDataType columnDataType;

        MaliyetReportHeader(String field, String header, ColumnDataType columnDataType) {
            this.field = field;
            this.header = header;
            this.columnDataType = columnDataType;
        }

        @Override
        public ColumnDataType getColumnDataType() {
            return columnDataType;
        }

        @Override
        public String getValue() {
            return field;
        }

        @Override
        public String getLabel() {
            return header;
        }

    }

}
