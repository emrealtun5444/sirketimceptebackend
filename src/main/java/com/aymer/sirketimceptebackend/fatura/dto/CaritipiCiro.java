package com.aymer.sirketimceptebackend.fatura.dto;

import com.aymer.sirketimceptebackend.cariKart.model.ECariTipi;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 1.09.2020
 * Time: 11:31
 */
@Getter
public class CaritipiCiro {

    private ECariTipi cariTipi;
    private BigDecimal tutar;

    public CaritipiCiro(ECariTipi cariTipi, BigDecimal tutar) {
        this.cariTipi = cariTipi;
        this.tutar = tutar;
    }
}
