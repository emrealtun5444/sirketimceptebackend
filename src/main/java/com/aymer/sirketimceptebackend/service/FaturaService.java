package com.aymer.sirketimceptebackend.service;


import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaSorguKriteri;
import com.aymer.sirketimceptebackend.model.Fatura;
import org.springframework.data.domain.Page;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface FaturaService {

    Page<Fatura> findFaturaByCriteria(FaturaSorguKriteri faturaSorguKriteri, int page, int rows);
}
