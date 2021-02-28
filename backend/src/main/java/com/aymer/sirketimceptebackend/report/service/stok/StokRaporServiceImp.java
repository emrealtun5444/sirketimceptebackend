package com.aymer.sirketimceptebackend.report.service.stok;

import com.aymer.sirketimceptebackend.common.model.enums.EEvetHayir;
import com.aymer.sirketimceptebackend.report.dto.RaporSorguKriteri;
import com.aymer.sirketimceptebackend.report.dto.ReportGroup;
import com.aymer.sirketimceptebackend.report.dto.Stok;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.ColumnDataType;
import com.aymer.sirketimceptebackend.report.model.ReportBaseEnum;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.report.repository.ReportRepository;
import com.aymer.sirketimceptebackend.report.service.AbstractAsenkronVeriHazirlamaRaporServiceImp;
import com.aymer.sirketimceptebackend.report.service.AsenkronRaporGeneratorService;
import com.aymer.sirketimceptebackend.stokkart.repository.StokKartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("stokRaporService")
public class StokRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements StokRaporService {

    @Autowired
    private StokKartRepository stokKartRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    public StokRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
        RaporSorguKriteri sorguKriteri = (RaporSorguKriteri) createSorguKriteri(asenkronRaporBilgi);
        return stokKartRepository.findAllByStokAdediGreaterThan(0L);
    }


    @Override
    public StokReportHeader[] getReportHeaders() {
        return StokReportHeader.values();
    }

    @Override
    public ReportGroup getReportGroup(List result) {
        Map<String, List> map = (Map<String, List>) result.stream().collect(Collectors.groupingBy(Stok::getMarka));
        return ReportGroup.builder().status(EEvetHayir.EVET_VAR).group(map).build();
    }

    enum StokReportHeader implements ReportBaseEnum<String> {
        STOK_KODU("stokKodu", "Stok Kodu", ColumnDataType.STRING),
        STO_ADI("stokAdi", "Stok Adı", ColumnDataType.STRING),
        ADET("adet", "Toplam Adet", ColumnDataType.NUMBER),
        TUTAR("tutar", "Toplam Tutar", ColumnDataType.MONEY);


        private String field;
        private String header;
        private ColumnDataType columnDataType;

        StokReportHeader(String field, String header, ColumnDataType columnDataType) {
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
