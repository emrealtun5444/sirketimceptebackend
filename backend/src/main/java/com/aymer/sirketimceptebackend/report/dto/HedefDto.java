package com.aymer.sirketimceptebackend.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class HedefDto {

    private Donem donem;
    private BigDecimal hedefTutari;
    private BigDecimal gerceklesenHedefTutari;
    private BigDecimal gerceklesmeOrani;

}
