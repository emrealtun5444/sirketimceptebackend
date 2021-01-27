package com.aymer.sirketimceptebackend.report.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.report.dto.AsenkronRaporBilgiSorguSonucu;
import com.aymer.sirketimceptebackend.report.dto.RaporSorguKriteri;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.report.service.AsenkronRaporGeneratorService;
import com.aymer.sirketimceptebackend.report.service.AsenkronReportThread;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/asenkronrapor")
public class AsenkronReportController {

    private static final int MAX_ACTIVE_THREAD = 10;
    private final ExecutorService executorService;
    private final AsenkronRaporGeneratorService asenkronRaporGeneratorService;
    private final SessionUtils sessionUtils;
    private final ApplicationContext context;

    @Autowired
    public AsenkronReportController(ApplicationContext context, AsenkronRaporGeneratorService asenkronRaporGeneratorService, SessionUtils sessionUtils) {
        this.asenkronRaporGeneratorService = asenkronRaporGeneratorService;
        this.sessionUtils = sessionUtils;
        this.context = context;
        this.executorService = Executors.newFixedThreadPool(MAX_ACTIVE_THREAD);
    }

    @PostMapping("/raporla")
    @PreAuthorize("hasAuthority('MERKEZI_REPORT_MENU')")
    public AppResponse raporla(@Valid @RequestBody RaporSorguKriteri sorguKriteri) {
        AsenkronRaporBilgi asenkronRaporBilgi = asenkronRaporGeneratorService.create(sorguKriteri.getRaporTuru(), sorguKriteri, sessionUtils);
        AsenkronReportThread thread = context.getBean("asenkronReportThread", AsenkronReportThread.class);
        thread.init(asenkronRaporBilgi);
        executorService.execute(thread);
        return AppResponse.nullResponse();
    }

    @GetMapping("/{id}/indir")
    @PreAuthorize("hasAuthority('MERKEZI_REPORT_MENU')")
    public AppResponse<String> fetchBelge(@Valid @PathVariable(name = "id") Long id) {
        String documentClassName = " com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi";
        String documentId = String.valueOf(id);
        String requestContextPath = "";
        String url = requestContextPath + "/.downloadfile?id=" + documentId + "&class=" + documentClassName;
        return new AppResponse<String>(url);
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasAuthority('MERKEZI_REPORT_MENU')")
    public AppResponse<List<AsenkronRaporBilgiSorguSonucu>> asenkronRaporlariGetir(@RequestBody String raporTuru) {
        RaporTuru _raporTuru = Enum.valueOf(RaporTuru.class, raporTuru);
        List<AsenkronRaporBilgiSorguSonucu> asenkronRaporBilgiSorguSonucuList = asenkronRaporGeneratorService.getAsenkronRaporBilgiList(sessionUtils, _raporTuru);
        return new AppResponse<List<AsenkronRaporBilgiSorguSonucu>>(asenkronRaporBilgiSorguSonucuList);
    }

}