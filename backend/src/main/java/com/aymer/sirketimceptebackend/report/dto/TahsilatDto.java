package com.aymer.sirketimceptebackend.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TahsilatDto {

    private Donem donem;
    private BigDecimal tutar;

    public TahsilatDto(BigDecimal tutar) {
        this.tutar = tutar;
    }
}
