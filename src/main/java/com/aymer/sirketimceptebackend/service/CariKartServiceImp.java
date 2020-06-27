package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.controller.carikart.dto.CariKartSorguKriteri;
import com.aymer.sirketimceptebackend.controller.carikart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.listener.carikart.CariKartViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.repository.SirketRepository;
import com.aymer.sirketimceptebackend.repository.UserRepository;
import com.aymer.sirketimceptebackend.repository.specs.BaseSpesification;
import com.aymer.sirketimceptebackend.repository.specs.SearchCriteria;
import com.aymer.sirketimceptebackend.repository.specs.SearchOperation;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class CariKartServiceImp implements CariKartService {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private SirketRepository sirketRepository;

    @Autowired
    private CariKartMapper cariKartMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void syncCariKart(CariKartViewHolder cariKartViewHolder) {
        Optional<Sirket> sirket = sirketRepository.findById(cariKartViewHolder.getSirketId());

        CariKart cariKart = null;
        boolean cariKartExists = cariKartRepository.existsByCariKodu(cariKartViewHolder.getHesapKodu());
        if (cariKartExists) {
            cariKart = cariKartRepository.findByCariKodu(cariKartViewHolder.getHesapKodu());
        } else {
            cariKart = CariKart.builder().durum(EDurum.AKTIF).sirket(sirket.get()).build();
        }
        // sorumlu personel bulunur.
        Optional<User> sorumluPersonel = userRepository.findByUsername(cariKartViewHolder.getOzelKod());
        if (sorumluPersonel.isPresent()) {
            cariKart.setSorumluPersonel(sorumluPersonel.get());
        }
        cariKartMapper.updateCariKart(cariKartViewHolder, cariKart);

        // değişiklikler kaydediliyor
        cariKartRepository.save(cariKart);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<CariKart> findCariKartByCriteria(CariKartSorguKriteri cariKartSorguKriteri, int page, int rows) {
        BaseSpesification spesification = new BaseSpesification<CariKart>();
        if (StringUtils.isNotEmpty(cariKartSorguKriteri.getCariKodu())) {
            spesification.add(new SearchCriteria("cariKodu", cariKartSorguKriteri.getCariKodu(), SearchOperation.MATCH));
        }
        if (StringUtils.isNotEmpty(cariKartSorguKriteri.getCariAdi())) {
            spesification.add(new SearchCriteria("cariAdi", cariKartSorguKriteri.getCariAdi(), SearchOperation.MATCH));
        }
        Pageable pageable = PageRequest.of(page, rows, Sort.by("cariAdi").ascending());
        Page<CariKart> cariKartPage = cariKartRepository.findAll(spesification, pageable);
        return cariKartPage;
    }


}
