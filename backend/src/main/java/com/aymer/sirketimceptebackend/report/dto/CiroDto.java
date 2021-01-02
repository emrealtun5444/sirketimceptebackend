package com.aymer.sirketimceptebackend.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CiroDto {

    private Donem donem;
    private BigDecimal tutar;

    public CiroDto(BigDecimal tutar) {
        this.donem = Donem.TUMU;
        this.tutar = tutar;
    }
}
