package com.aymer.sirketimceptebackend.tahsilat.service;

import com.aymer.sirketimceptebackend.tahsilat.dto.TahsilatSorguKriteri;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import com.aymer.sirketimceptebackend.tahsilat.repository.TahsilatRepository;
import com.aymer.sirketimceptebackend.tahsilat.repository.TahsilatSpesification;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
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
public class TahsilatServiceImp implements TahsilatService {

    @Autowired
    private TahsilatRepository tahsilatRepository;

    @Autowired
    private SessionUtils sessionUtils;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<FinansalHareket> findByCriteria(TahsilatSorguKriteri sorguKriteri, int page, int rows) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by("evrakNo").descending());
        Page<FinansalHareket> finansalHareketPage = tahsilatRepository.findAll(new TahsilatSpesification(sessionUtils, sorguKriteri), pageable);
        return finansalHareketPage;
    }


}
