package com.aymer.sirketimceptebackend.siparis.service;

import com.aymer.sirketimceptebackend.siparis.dto.SiparisSorguKriteri;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisRepository;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisSpesification;
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
public class SiparisServiceImp implements SiparisService {

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private SessionUtils sessionUtils;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<Siparis> findByCriteria(SiparisSorguKriteri sorguKriteri, int page, int rows) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by("siparisNo").descending());
        Page<Siparis> siparisPage = siparisRepository.findAll(new SiparisSpesification(sessionUtils, sorguKriteri), pageable);
        return siparisPage;
    }


}
