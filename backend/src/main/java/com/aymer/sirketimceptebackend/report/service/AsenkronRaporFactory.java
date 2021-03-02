package com.aymer.sirketimceptebackend.report.service;

import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.report.service.bekleyensiparis.BekleyenSiparisRaporService;
import com.aymer.sirketimceptebackend.report.service.caridonemciro.CariDonemCiroRaporService;
import com.aymer.sirketimceptebackend.report.service.caridonemciro.CariDonemTahsilatRaporService;
import com.aymer.sirketimceptebackend.report.service.carimaliyet.CariMaliyetRaporService;
import com.aymer.sirketimceptebackend.report.service.markamaliyet.MarkaMaliyetRaporService;
import com.aymer.sirketimceptebackend.report.service.stok.StokRaporService;
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
        } else if (reportType.equals(RaporTuru.CARI_DONEM_CIRO_RAPORU)) {
            raporHazirlamaService = (AbstractAsenkronVeriHazirlamaRaporService) context.getBean("cariDonemCiroRaporService", CariDonemCiroRaporService.class);
        } else if (reportType.equals(RaporTuru.CAR_DNM_TAHSILAT_RAPORU)) {
            raporHazirlamaService = (AbstractAsenkronVeriHazirlamaRaporService) context.getBean("cariDonemTahsilatRaporService", CariDonemTahsilatRaporService.class);
        } else if (reportType.equals(RaporTuru.STOK_BEKLEYEN)) {
            raporHazirlamaService = (AbstractAsenkronVeriHazirlamaRaporService) context.getBean("stokRaporService", StokRaporService.class);
        } else if (reportType.equals(RaporTuru.SIPARIS_BEKLEYEN)) {
            raporHazirlamaService = (AbstractAsenkronVeriHazirlamaRaporService) context.getBean("bekleyenSiparisRaporService", BekleyenSiparisRaporService.class);
        }
        return raporHazirlamaService;
    }


}
