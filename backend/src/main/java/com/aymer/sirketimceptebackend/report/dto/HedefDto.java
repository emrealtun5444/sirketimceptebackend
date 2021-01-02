package com.aymer.sirketimceptebackend.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HedefDto {

    private Integer donem;
    private BigDecimal hedefTutari;
    private BigDecimal gerceklesenHedefTutari;
    private BigDecimal gerceklesmeOrani;

    public HedefDto(Integer donem,  BigDecimal gerceklesenHedefTutari) {
        this.donem = donem;
        this.hedefTutari = hedefTutari;
        this.gerceklesenHedefTutari = gerceklesenHedefTutari;
    }
}
