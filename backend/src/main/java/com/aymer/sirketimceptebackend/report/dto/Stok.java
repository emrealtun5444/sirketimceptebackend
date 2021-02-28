package com.aymer.sirketimceptebackend.report.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Stok {

    private String marka;
    private String stokKodu;
    private String stokAdi;
    private Long adet;
    private BigDecimal tutar;

    public Stok(String marka, String stokKodu, String stokAdi, Long adet, BigDecimal tutar) {
        this.marka = marka;
        this.stokKodu = stokKodu;
        this.stokAdi = stokAdi;
        this.adet = adet;
        this.tutar = tutar;
    }
}
