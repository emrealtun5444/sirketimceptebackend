package com.aymer.sirketimceptebackend.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TahsilatDto {

    private Integer donem;
    private BigDecimal tutar;

    public TahsilatDto(BigDecimal tutar) {
        this.tutar = tutar;
    }
}
