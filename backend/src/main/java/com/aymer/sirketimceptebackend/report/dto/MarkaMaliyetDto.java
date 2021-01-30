package com.aymer.sirketimceptebackend.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkaMaliyetDto {

    private String markaAdi;
    private BigDecimal toplamCiro;
    private BigDecimal toplamKar;
    private BigDecimal karlilikOrani;

}
