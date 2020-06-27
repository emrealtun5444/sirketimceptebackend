package com.aymer.sirketimceptebackend.service;


import com.aymer.sirketimceptebackend.controller.carikart.dto.CariKartSorguKriteri;
import com.aymer.sirketimceptebackend.controller.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.listener.carikart.CariKartViewHolder;
import com.aymer.sirketimceptebackend.listener.stokkart.StokKartViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.StokKart;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface CariKartService {

    void syncCariKart(CariKartViewHolder cariKartViewHolder);

    Page<CariKart> findCariKartByCriteria(CariKartSorguKriteri cariKartSorguKriteri, int page, int rows);
}
