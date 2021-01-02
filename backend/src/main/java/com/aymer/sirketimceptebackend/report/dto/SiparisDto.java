package com.aymer.sirketimceptebackend.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SiparisDto {
    private Integer donem;
    private Long toplamAdet;
    private Long teslimAdet;
    private Long kalanAdet;
    private Long teslimOran;
    private BigDecimal toplamTutar;

    public SiparisDto(Long toplamAdet, Long teslimAdet, Long kalanAdet, Long teslimOran, BigDecimal toplamTutar) {
        this.toplamAdet = toplamAdet;
        this.teslimAdet = teslimAdet;
        this.kalanAdet = kalanAdet;
        this.teslimOran = teslimOran;
        this.toplamTutar = toplamTutar;
    }
}
