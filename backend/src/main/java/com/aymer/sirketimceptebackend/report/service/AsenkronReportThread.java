package com.aymer.sirketimceptebackend.report.service;

import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.utils.LocaleAwareMessageProvider;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class AsenkronReportThread extends Thread {

    private AsenkronRaporBilgi asenkronRaporBilgi;

    @Autowired
    private AsenkronRaporGeneratorService asenkronRaporGeneratorService;

    @Autowired
    private AsenkronRaporFactory asenkronRaporFactory;

    @Autowired
    private LocaleAwareMessageProvider messageProvider;

    public void init(AsenkronRaporBilgi asenkronRaporBilgi) {
        this.asenkronRaporBilgi = asenkronRaporBilgi;
    }

    @Override
    public void run() {
        try {
            // rapor tinine karar veriri.
            AbstractAsenkronVeriHazirlamaRaporService raporService = asenkronRaporFactory.getReportType(asenkronRaporBilgi.getRaporTuru());
            raporService.prepareReport(asenkronRaporBilgi);
        } catch (ServiceException ex) {
            String exceptionString = "key: " + ex.getMsgKey() + ", args: " + (ex.getMsgArgs() != null && ex.getMsgArgs().length > 0 ? ex.getMsgArgs()[0] : "") + ", " + this.messageProvider.getMessage(ex.getMsgKey(), ex.getMsgArgs()) + " - " + ExceptionUtils.getStackTrace(ex);
            asenkronRaporGeneratorService.fail(asenkronRaporBilgi.getId(), exceptionString);
        } catch (Exception ex) {
            String exceptionString = ExceptionUtils.getStackTrace(ex);
            asenkronRaporGeneratorService.fail(asenkronRaporBilgi.getId(), exceptionString);
        }
    }

}
