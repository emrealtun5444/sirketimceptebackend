package com.aymer.sirketimceptebackend.controller.dashboard;

import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import com.aymer.sirketimceptebackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/currentNumberOfSales")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AppResponse<Long> currentNumberOfSales() {
        Long currentNumberOfSales = dashboardService.currentNumberOfSales();
        return new AppResponse<Long>(currentNumberOfSales);
    }

    @GetMapping("/monthlyNumberOfSales")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AppResponse<Long> monthlyNumberOfSales() {
        Long monthlyNumberOfSales = dashboardService.monthlyNumberOfSales();
        return new AppResponse<Long>(monthlyNumberOfSales);
    }

    @GetMapping("/currentAmountOfSales")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AppResponse<BigDecimal> currentAmountOfSales() {
        BigDecimal currentAmountOfSales = dashboardService.currentAmountOfSales();
        return new AppResponse<BigDecimal>(currentAmountOfSales);
    }

    @GetMapping("/monthlyAmountOfSales")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AppResponse<BigDecimal> monthlyAmountOfSales() {
        BigDecimal monthlyAmountOfSales = dashboardService.monthlyAmountOfSales();
        return new AppResponse<BigDecimal>(monthlyAmountOfSales);
    }

    @GetMapping("/faturaKirilim")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public AppResponse<Map> faturaKirilim() {
        Map<ECariTipi, BigDecimal> pageObject = dashboardService.faturaKirilim();
        return new AppResponse<Map>(pageObject);
    }


}
