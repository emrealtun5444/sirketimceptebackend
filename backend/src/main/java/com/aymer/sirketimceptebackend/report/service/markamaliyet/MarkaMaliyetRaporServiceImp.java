package com.aymer.sirketimceptebackend.report.service.markamaliyet;

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

import java.util.List;
import java.util.Set;

@Service("markaMaliyetRaporService")
public class MarkaMaliyetRaporServiceImp extends AbstractAsenkronVeriHazirlamaRaporServiceImp implements MarkaMaliyetRaporService {

    @Autowired
    public MarkaMaliyetRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        super(asenkronRaporGeneratorService, asenkronRaporBilgiRepository);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi) {
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
