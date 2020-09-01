package com.aymer.sirketimceptebackend.repository.specs.predicate;

import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
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
