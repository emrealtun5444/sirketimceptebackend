package com.aymer.sirketimceptebackend.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DonemCiroDto {
    private int ay;
    private BigDecimal tutar;
}
