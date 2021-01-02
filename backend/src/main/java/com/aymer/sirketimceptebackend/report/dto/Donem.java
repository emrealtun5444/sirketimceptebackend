package com.aymer.sirketimceptebackend.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Donem {

    TUMU(null),
    OCAK(1),
    SUBAT(2),
    MART(3),
    NISAN(4),
    MAYIS(5),
    HAZIRAN(6),
    TEMMUZ(7),
    AGUSTOS(8),
    EYLUL(9),
    EKIM(10),
    KASIM(11),
    ARALIK(12);

    private final Integer ay;
}
