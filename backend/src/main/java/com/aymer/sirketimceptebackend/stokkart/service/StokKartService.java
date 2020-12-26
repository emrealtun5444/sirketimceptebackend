package com.aymer.sirketimceptebackend.stokkart.service;


import com.aymer.sirketimceptebackend.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.stokkart.listener.StokKartViewHolder;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface StokKartService {

    StokKart syncStokKart(StokKartViewHolder stokKartDto);

    StokKart getStokKart(Sirket sirket, String stokKodu);

    StokKart saveStokKart(StokKart stokKart);

    public Page<StokKart> findStokKartByCriteria(StokKartSorguKriteri stokKartSorguKriteri, int page, int rows);
}
