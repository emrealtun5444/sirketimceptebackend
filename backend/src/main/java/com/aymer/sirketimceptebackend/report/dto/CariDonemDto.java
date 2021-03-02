package com.aymer.sirketimceptebackend.report.dto;

import com.aymer.sirketimceptebackend.cariKart.model.ECariTipi;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CariDonemDto {

    private String cariTipi;
    private String sorumluPersonel;
    private String cariKodu;
    private String cariAdi;
    private BigDecimal donem1;
    private BigDecimal donem2;
    private BigDecimal donem3;
    private BigDecimal donem4;
    private BigDecimal donem5;
    private BigDecimal donem6;
    private BigDecimal donem7;
    private BigDecimal donem8;
    private BigDecimal donem9;
    private BigDecimal donem10;
    private BigDecimal donem11;
    private BigDecimal donem12;
    private BigDecimal toplam;

    public CariDonemDto(ECariTipi cariTipi, String sorumluPersonel, String cariKodu, String cariAdi, BigDecimal donem1, BigDecimal donem2, BigDecimal donem3, BigDecimal donem4, BigDecimal donem5, BigDecimal donem6, BigDecimal donem7, BigDecimal donem8, BigDecimal donem9, BigDecimal donem10, BigDecimal donem11, BigDecimal donem12, BigDecimal toplam) {
        this.cariTipi = cariTipi.name();
        this.sorumluPersonel = sorumluPersonel;
        this.cariKodu = cariKodu;
        this.cariAdi = cariAdi;
        this.donem1 = donem1;
        this.donem2 = donem2;
        this.donem3 = donem3;
        this.donem4 = donem4;
        this.donem5 = donem5;
        this.donem6 = donem6;
        this.donem7 = donem7;
        this.donem8 = donem8;
        this.donem9 = donem9;
        this.donem10 = donem10;
        this.donem11 = donem11;
        this.donem12 = donem12;
        this.toplam = toplam;
    }

    public String sorumluPersonelGroup() {
        return sorumluPersonel != null ? sorumluPersonel : "Diger";
    }
}
