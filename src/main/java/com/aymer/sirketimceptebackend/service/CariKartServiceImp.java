package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.controller.carikart.dto.CariKartSorguKriteri;
import com.aymer.sirketimceptebackend.controller.carikart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.listener.carikart.visitor.CariKartVisitor;
import com.aymer.sirketimceptebackend.listener.carikart.CariVisitorFactory;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.repository.specs.common.BaseSpesification;
import com.aymer.sirketimceptebackend.repository.specs.common.SearchCriteria;
import com.aymer.sirketimceptebackend.repository.specs.common.SearchOperation;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private CariKartMapper cariKartMapper;

    @Autowired
    private CariVisitorFactory cariVisitorFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void syncCariKart(CariKartViewHolder cariKartViewHolder) {
        List<CariKartVisitor> cariKartVisitorList = cariVisitorFactory.getCariVisitorList();
        cariKartVisitorList.forEach(visitor -> {
            visitor.visit(cariKartViewHolder);
        });
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
