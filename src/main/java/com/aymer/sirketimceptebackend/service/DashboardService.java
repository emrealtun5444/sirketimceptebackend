package com.aymer.sirketimceptebackend.service;


import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    Map<ECariTipi, BigDecimal> faturaKirilim();
}
