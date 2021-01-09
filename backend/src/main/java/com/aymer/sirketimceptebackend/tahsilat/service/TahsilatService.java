package com.aymer.sirketimceptebackend.tahsilat.service;


import com.aymer.sirketimceptebackend.tahsilat.dto.TahsilatSorguKriteri;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface TahsilatService {
    Page<FinansalHareket> findByCriteria(TahsilatSorguKriteri sorguKriteri, int page, int rows);
}
