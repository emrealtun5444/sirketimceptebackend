package com.aymer.sirketimceptebackend.dashboard.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.cariKart.model.ECariTipi;
import com.aymer.sirketimceptebackend.dashboard.dto.SorumluPersonelCiroDto;
import com.aymer.sirketimceptebackend.dashboard.service.DashboardService;
import com.aymer.sirketimceptebackend.fatura.dto.SorumluPersonelCiro;
import com.aymer.sirketimceptebackend.system.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/dashboard")
public class DashboardController {

    private DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/currentNumberOfSales")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    public AppResponse<Long> currentNumberOfSales() {
        Long currentNumberOfSales = dashboardService.currentNumberOfSales();
        return new AppResponse<Long>(currentNumberOfSales);
    }

    @GetMapping("/monthlyNumberOfSales")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    public AppResponse<Long> monthlyNumberOfSales() {
        Long monthlyNumberOfSales = dashboardService.monthlyNumberOfSales();
        return new AppResponse<Long>(monthlyNumberOfSales);
    }

    @GetMapping("/currentAmountOfSales")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    public AppResponse<BigDecimal> currentAmountOfSales() {
        BigDecimal currentAmountOfSales = dashboardService.currentAmountOfSales();
        return new AppResponse<BigDecimal>(currentAmountOfSales);
    }

    @GetMapping("/monthlyAmountOfSales")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    public AppResponse<BigDecimal> monthlyAmountOfSales() {
        BigDecimal monthlyAmountOfSales = dashboardService.monthlyAmountOfSales();
        return new AppResponse<BigDecimal>(monthlyAmountOfSales);
    }

    @GetMapping("/personelCiroDagilim")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    public AppResponse<List<SorumluPersonelCiroDto>> personelCiroDagilim() {
        List<SorumluPersonelCiroDto> pageObject = dashboardService.faturaKirilim();
        return new AppResponse<List<SorumluPersonelCiroDto>>(pageObject);
    }


}
