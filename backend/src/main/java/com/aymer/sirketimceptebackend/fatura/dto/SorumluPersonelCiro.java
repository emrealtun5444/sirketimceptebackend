package com.aymer.sirketimceptebackend.fatura.dto;

import com.aymer.sirketimceptebackend.cariKart.model.ECariTipi;
import com.aymer.sirketimceptebackend.system.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 1.09.2020
 * Time: 11:31
 */
@Getter
@AllArgsConstructor
public class SorumluPersonelCiro {

    private User user;
    private BigDecimal tutar;

}
