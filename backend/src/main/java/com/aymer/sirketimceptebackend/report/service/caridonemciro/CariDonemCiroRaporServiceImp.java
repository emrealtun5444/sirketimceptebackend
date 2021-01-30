package com.aymer.sirketimceptebackend.report.service.caridonemciro;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.report.dto.RaporSorguKriteri;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.ColumnDataType;
import com.aymer.sirketimceptebackend.report.model.ReportBaseEnum;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.report.repository.ReportRepository;
import com.aymer.sirketimceptebackend.report.service.AbstractAsenkronVeriHazirlamaRaporServiceImp;
import com.aymer.sirketimceptebackend.report.service.AsenkronRaporGeneratorService;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cariDonemCiroRaporService")
public class CariDonemCiroRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements CariDonemCiroRaporService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    public CariDonemCiroRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
        RaporSorguKriteri sorguKriteri = (RaporSorguKriteri) createSorguKriteri(asenkronRaporBilgi);
        return reportRepository.cariCiroDonemList(sorguKriteri.getDonem(), sorguKriteri.getYil(), EDurum.AKTIF, EOdemeYonu.BORC, asenkronRaporBilgi.getSirket());
    }

    @Override
    public CariDonemReportHeader[] getReportHeaders() {
        return CariDonemReportHeader.values();
    }

    enum CariDonemReportHeader implements ReportBaseEnum<String> {
        CARI_TIPI("cariTipi", "Cari Tipi", ColumnDataType.STRING),
        SORUMLU_PERSONEL("sorumluPersonel", "Sorumlu Personel", ColumnDataType.STRING),
        CARI_KODU("cariKodu", "Cari Kodu", ColumnDataType.STRING),
        CARI_ADI("cariAdi", "Cari Adı", ColumnDataType.STRING),
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
        TOPLAM("toplam", "Toplam", ColumnDataType.MONEY);

        private String field;
        private String header;
        private ColumnDataType columnDataType;

        CariDonemReportHeader(String field, String header, ColumnDataType columnDataType) {
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
