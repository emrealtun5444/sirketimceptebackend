package com.aymer.sirketimceptebackend.report.service.markamaliyet;

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
import com.aymer.sirketimceptebackend.stokkart.model.Marka;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.stokkart.repository.BrandRepository;
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

@Service("markaMaliyetRaporService")
public class MarkaMaliyetRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements MarkaMaliyetRaporService {

    @Autowired
    private MaliyetRepository maliyetRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private FaturaDetayRepository faturaDetayRepository;


    @Autowired
    public MarkaMaliyetRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
        RaporSorguKriteri sorguKriteri = (RaporSorguKriteri) createSorguKriteri(asenkronRaporBilgi);
        final List<Maliyet> maliyetList = maliyetRepository.findAll();
        List<MarkaMaliyetDto> markaMaliyetDtoList = reportRepository.markaCiroDonemList(sorguKriteri.getDonem(),sorguKriteri.getYil(),EDurum.AKTIF,EOdemeYonu.BORC,asenkronRaporBilgi.getSirket());
        markaMaliyetDtoList.forEach(markaMaliyetDto -> {
            Karlilik karlilik = getkarlilikByMarka(sorguKriteri, markaMaliyetDto.getMarka(), maliyetList);
            markaMaliyetDto.initKarlilik(karlilik);
        });
        return markaMaliyetDtoList;
    }

    private Karlilik getkarlilikByMarka(RaporSorguKriteri sorguKriteri, Marka marka, List<Maliyet> maliyetList) {
        List<FaturaDetay> faturaDetays = faturaDetayRepository.faturaKalems(marka, null, EDurum.AKTIF, EOdemeYonu.BORC, sorguKriteri.getDonem(), sorguKriteri.getYil());
        BigDecimal toplamMaliyetTutari = BigDecimal.ZERO;
        BigDecimal toplamSatisTutari = BigDecimal.ZERO;
        BigDecimal toplamKarTutari = BigDecimal.ZERO;
        BigDecimal karlilikOrani = BigDecimal.ZERO;
        for (FaturaDetay faturaDetay : faturaDetays) {
            StokKart stokKart = faturaDetay.getStokKart();
            Maliyet maliyet = null;
            if (stokKart.getMarka() != null) {
                maliyet = maliyetList.stream()
                        .filter(m -> stokKart.getMarka().getId().equals(m.getId()) && (m.getBaslangicTarihi().compareTo(faturaDetay.getIslemTarihi()) * faturaDetay.getIslemTarihi().compareTo(m.getBitisTarihi()) >= 0))
                        .findAny()
                        .orElse(null);
            }

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
        MARKA("markaAdi", "Marka Adı", ColumnDataType.STRING),
        OCAK("donem1", "Ocak", ColumnDataType.MONEY),
        SUBAK("donem2", "Şubat", ColumnDataType.MONEY),
        MART("donem3", "Mart", ColumnDataType.MONEY),
        NISAN("donem4", "Nisan", ColumnDataType.MONEY),
        MAYIS("donem5", "Mayıs", ColumnDataType.MONEY),
        HAZIRAN("donem6", "Haziran", ColumnDataType.MONEY),
        TEMMUZ("donem7", "Temmuz", ColumnDataType.MONEY),
        AGUSTOS("donem8", "Ağustos", ColumnDataType.MONEY),
        EYLUL("donem9", "Eylül", ColumnDataType.MONEY),
        EKIM("donem10", "Ekim", ColumnDataType.MONEY),
        KASIM("donem11", "Kasım", ColumnDataType.MONEY),
        ARALIK("donem12", "Aralık", ColumnDataType.MONEY),
        TOPLAM_SATIS("toplamCiro", "Toplam Ciro", ColumnDataType.MONEY),
        TOPLAM_KARLILIK("toplamKar", "Toplam Kar", ColumnDataType.MONEY),
        KARLILIK_ORANI("karlilikOrani", "Karlılık Oranı", ColumnDataType.MONEY);


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
