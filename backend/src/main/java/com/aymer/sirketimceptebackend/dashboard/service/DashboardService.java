package com.aymer.sirketimceptebackend.dashboard.service;


import com.aymer.sirketimceptebackend.cariKart.model.ECariTipi;
import com.aymer.sirketimceptebackend.dashboard.dto.SorumluPersonelCiroDto;
import com.aymer.sirketimceptebackend.fatura.dto.SorumluPersonelCiro;
import com.aymer.sirketimceptebackend.system.user.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface DashboardService {

    @Transactional(propagation = Propagation.SUPPORTS)
    Long currentNumberOfSales();

    @Transactional(propagation = Propagation.SUPPORTS)
    Long monthlyNumberOfSales();

    @Transactional(propagation = Propagation.SUPPORTS)
    BigDecimal currentAmountOfSales();

    @Transactional(propagation = Propagation.SUPPORTS)
    BigDecimal monthlyAmountOfSales();

    @Transactional(propagation = Propagation.SUPPORTS)
    List<SorumluPersonelCiroDto> faturaKirilim();
}
