package com.aymer.sirketimceptebackend.report.service.bekleyensiparis;

import com.aymer.sirketimceptebackend.common.model.enums.EEvetHayir;
import com.aymer.sirketimceptebackend.report.dto.RaporSorguKriteri;
import com.aymer.sirketimceptebackend.report.dto.ReportGroup;
import com.aymer.sirketimceptebackend.report.dto.SiparisReportDto;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.ColumnDataType;
import com.aymer.sirketimceptebackend.report.model.ReportBaseEnum;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.report.service.AbstractAsenkronVeriHazirlamaRaporServiceImp;
import com.aymer.sirketimceptebackend.report.service.AsenkronRaporGeneratorService;
import com.aymer.sirketimceptebackend.siparis.mapper.SiparisMapper;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.siparis.model.SiparisDurumu;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("bekleyenSiparisRaporService")
public class BekleyenSiparisRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements BekleyenSiparisRaporService {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private SiparisMapper siparisMapper;


    @Autowired
    public BekleyenSiparisRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
        RaporSorguKriteri sorguKriteri = (RaporSorguKriteri) createSorguKriteri(asenkronRaporBilgi);
        List<Siparis> siparisList = siparisRepository.findAllBySiparisDurumuNotAndSirketOrderByIslemTarihiDesc(SiparisDurumu.TAMAMLANDI, asenkronRaporBilgi.getSirket());
        return siparisMapper.toReportDtoList(siparisList);
    }

    @Override
    public BekleyenSiparisReportHeader[] getReportHeaders() {
        return BekleyenSiparisReportHeader.values();
    }

    @Override
    public ReportGroup getReportGroup(List result) {
        Map<String, List> map = (Map<String, List>) result.stream().collect(Collectors.groupingBy(SiparisReportDto::getCariAdi));
        return ReportGroup.builder().status(EEvetHayir.EVET_VAR).group(map).build();
    }

    enum BekleyenSiparisReportHeader implements ReportBaseEnum<String> {
        SIPARIS_NO("siparisNo", "Sipariş No", ColumnDataType.STRING),
        SIPARIS_TARIHI("siparisTarihi", "Sipariş Tarihi", ColumnDataType.DATE),
        STOK_KODU("stokKodu", "Stok Kodu", ColumnDataType.STRING),
        URUN_ADI("urunAdi", "Ürün Adı", ColumnDataType.STRING),
        MIKTAR("miktar", "Miktar", ColumnDataType.NUMBER),
        TESLIM_MIKTAR("teslimMiktar", "Teslim Miktar", ColumnDataType.NUMBER),
        KALAN_MIKTAR("kalanMiktar", "Kalan Miktar", ColumnDataType.NUMBER),
        STOK_MIKTAR("stokMiktar", "Stok Miktar", ColumnDataType.NUMBER),
        SIPARIS_DURUMU("siparisDurumu", "Sipariş Durumu", ColumnDataType.STRING);


        private String field;
        private String header;
        private ColumnDataType columnDataType;

        BekleyenSiparisReportHeader(String field, String header, ColumnDataType columnDataType) {
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
