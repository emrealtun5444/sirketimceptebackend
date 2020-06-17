package com.aymer.sirketimceptebackend.service;


import com.aymer.sirketimceptebackend.controller.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.listener.skorkart.StokKartViewHolder;
import com.aymer.sirketimceptebackend.model.StokKart;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface StokKartService {

     void syncStokKart(StokKartViewHolder stokKartDto);

     Page<StokKart> findStokKartByCriteria(StokKartSorguKriteri stokKartSorguKriteri, int page);

}
