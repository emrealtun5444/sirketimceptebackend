package com.aymer.sirketimceptebackend.siparis.service;


import com.aymer.sirketimceptebackend.siparis.dto.SiparisSorguKriteri;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface SiparisService {
    Page<Siparis> findByCriteria(SiparisSorguKriteri sorguKriteri, int page, int rows);
}
