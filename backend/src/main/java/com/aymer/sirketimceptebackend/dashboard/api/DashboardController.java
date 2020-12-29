package com.aymer.sirketimceptebackend.dashboard.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.dashboard.dto.DonemCiroDto;
import com.aymer.sirketimceptebackend.dashboard.dto.SorumluPersonelCiroDto;
import com.aymer.sirketimceptebackend.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
        List<SorumluPersonelCiroDto> pageObject = dashboardService.personelCiroDagilim();
        return new AppResponse<List<SorumluPersonelCiroDto>>(pageObject);
    }

    @GetMapping("/donemCiroDagilimi")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    public AppResponse<List<DonemCiroDto>> donemeGoreCiroDagilimi() {
        List<DonemCiroDto> pageObject = dashboardService.donemeGoreCiroDagilimi();
        return new AppResponse<List<DonemCiroDto>>(pageObject);
    }

}
