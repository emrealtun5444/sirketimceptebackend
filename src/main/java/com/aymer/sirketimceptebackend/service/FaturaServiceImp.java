package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaSorguKriteri;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.repository.specs.FaturaSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class FaturaServiceImp implements FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public  Page<Fatura> findFaturaByCriteria(FaturaSorguKriteri faturaSorguKriteri, int page, int rows) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by("faturaTarihi").descending());
        Page<Fatura> faturaPage = faturaRepository.findAll(new FaturaSpesification(faturaSorguKriteri), pageable);
        return faturaPage;
    }


}
