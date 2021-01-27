package com.aymer.sirketimceptebackend.report.service.carimaliyet;

import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.ColumnDataType;
import com.aymer.sirketimceptebackend.report.model.ReportBaseEnum;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.report.service.AbstractAsenkronVeriHazirlamaRaporServiceImp;
import com.aymer.sirketimceptebackend.report.service.AsenkronRaporGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("cariMaliyetRaporService")
public class CariMaliyetRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements CariMaliyetRaporService {

    @Autowired
    public CariMaliyetRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Set sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
        return null;
    }


    @Override
    public MaliyetReportHeader[] getReportHeaders() {
        return MaliyetReportHeader.values();
    }

    enum MaliyetReportHeader implements ReportBaseEnum<String> {
        EKLENME_SEBEBI("eklenmeSebebi", "Eklenme Sebebi", ColumnDataType.STRING);


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
