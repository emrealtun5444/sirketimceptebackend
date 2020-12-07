package com.aymer.sirketimceptebackend.stokkart.service;


import com.aymer.sirketimceptebackend.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.stokkart.listener.StokKartViewHolder;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface StokKartService {

    void syncStokKart(StokKartViewHolder stokKartDto);

    public Page<StokKart> findStokKartByCriteria(StokKartSorguKriteri stokKartSorguKriteri, int page, int rows);
}
