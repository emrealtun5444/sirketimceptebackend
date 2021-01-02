package com.aymer.sirketimceptebackend.dashboard.service;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.dashboard.dto.SorumluPersonelCiroDto;
import com.aymer.sirketimceptebackend.dashboard.mapper.DashboardMapper;
import com.aymer.sirketimceptebackend.fatura.dto.SorumluPersonelCiro;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.report.dto.CiroDto;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private FaturaRepository faturaRepository;
    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long currentNumberOfSales() {
        return faturaRepository.numberOfSales(DateUtils.getToday(), EDurum.AKTIF, EOdemeYonu.BORC);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long monthlyNumberOfSales() {
        return faturaRepository.numberOfSales(DateUtils.firstDayOfMounth(), EDurum.AKTIF, EOdemeYonu.BORC);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public BigDecimal currentAmountOfSales() {
        return faturaRepository.amountOfSales(DateUtils.getToday(), EDurum.AKTIF, EOdemeYonu.BORC);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public BigDecimal monthlyAmountOfSales() {
        return faturaRepository.amountOfSales(DateUtils.firstDayOfMounth(), EDurum.AKTIF, EOdemeYonu.BORC);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SorumluPersonelCiroDto> personelCiroDagilim() {
        List<SorumluPersonelCiro> sorumluPersonelCiroList = faturaRepository.personelCiroDagilim(DateUtils.firstDayOfMounth(), EDurum.AKTIF, EOdemeYonu.BORC);
        return dashboardMapper.toDtoList(sorumluPersonelCiroList);
    }

}
