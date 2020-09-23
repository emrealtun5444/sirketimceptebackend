package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
import com.aymer.sirketimceptebackend.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.repository.specs.predicate.CaritipiCiro;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private FaturaRepository faturaRepository;

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
    public Map<ECariTipi, BigDecimal> faturaKirilim() {
        Map<ECariTipi, BigDecimal> map = new HashMap<>();
        List<CaritipiCiro> caritipiCiroList = faturaRepository.faturaKirilim(DateUtils.firstDayOfMounth(), EDurum.AKTIF, EOdemeYonu.BORC);
        caritipiCiroList.forEach(caritipiCiro -> {
            ECariTipi eCariTipi = ECariTipi.BAGLANTILI_CALISAN;
            if (caritipiCiro.getCariTipi().equals(ECariTipi.ETICARET) || caritipiCiro.getCariTipi().equals(ECariTipi.PERAKENDE)) {
                eCariTipi = caritipiCiro.getCariTipi();
            }
            BigDecimal currentTutar = map.get(eCariTipi) != null ? map.get(eCariTipi) : BigDecimal.ZERO;
            map.put(eCariTipi, caritipiCiro.getTutar().add(currentTutar));
        });
        return map;
    }

}
