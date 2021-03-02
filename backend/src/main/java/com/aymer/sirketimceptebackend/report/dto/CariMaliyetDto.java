package com.aymer.sirketimceptebackend.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CariMaliyetDto {

    private String cariTipi;
    private String sorumluPersonel;
    private String cariKodu;
    private String cariAdi;
    private BigDecimal toplamCiro;
    private BigDecimal toplamTahsilat;
    private BigDecimal bekleyenSiparis;
    private BigDecimal hedef;
    private BigDecimal toplamBorc;
    private BigDecimal toplamAlacak;
    private BigDecimal bakiye;
    private BigDecimal toplamKar;
    private BigDecimal karlilikOrani;

    public String sorumluPersonelGroup() {
        return sorumluPersonel != null ? sorumluPersonel : "Diger";
    }

}
