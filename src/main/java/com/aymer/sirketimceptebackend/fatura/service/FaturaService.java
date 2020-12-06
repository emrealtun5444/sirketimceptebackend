package com.aymer.sirketimceptebackend.fatura.service;


import com.aymer.sirketimceptebackend.fatura.dto.FaturaSorguKriteri;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface FaturaService {

    Page<Fatura> findFaturaByCriteria(FaturaSorguKriteri faturaSorguKriteri, int page, int rows);
}
