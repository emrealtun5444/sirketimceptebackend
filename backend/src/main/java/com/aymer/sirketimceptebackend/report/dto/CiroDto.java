package com.aymer.sirketimceptebackend.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CiroDto {

    private Integer donem;
    private BigDecimal tutar;

    public CiroDto(BigDecimal tutar) {
        this.tutar = tutar;
    }
}
