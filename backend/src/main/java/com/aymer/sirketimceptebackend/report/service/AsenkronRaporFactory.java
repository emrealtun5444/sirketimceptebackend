package com.aymer.sirketimceptebackend.report.service;

import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.report.service.carimaliyet.CariMaliyetRaporService;
import com.aymer.sirketimceptebackend.report.service.markamaliyet.MarkaMaliyetRaporService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AsenkronRaporFactory {

    @Autowired
    private ApplicationContext context;

    public AbstractAsenkronVeriHazirlamaRaporService getReportType(RaporTuru reportType) {
        AbstractAsenkronVeriHazirlamaRaporService raporHazirlamaService = null;
        if (reportType.equals(RaporTuru.CARI_MALIYET_RAPORU)) {
            raporHazirlamaService = (AbstractAsenkronVeriHazirlamaRaporService) context.getBean("cariMaliyetRaporService", CariMaliyetRaporService.class);
        } else if (reportType.equals(RaporTuru.MARKA_MALIYET_RAPORU)) {
            raporHazirlamaService = (AbstractAsenkronVeriHazirlamaRaporService) context.getBean("markaMaliyetRaporService", MarkaMaliyetRaporService.class);
        }
        return raporHazirlamaService;
    }


}
