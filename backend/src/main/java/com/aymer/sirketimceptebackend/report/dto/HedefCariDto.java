package com.aymer.sirketimceptebackend.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HedefCariDto {

    private String cariKodu;
    private String cariAdi;
    private BigDecimal toplamBorc;
    private BigDecimal toplamAlacak;
    private BigDecimal bakiye;

    private BigDecimal toplamCiro;
    private BigDecimal yillikHedef;
    private BigDecimal gerceklesmeYuzdesi;

    public HedefCariDto(String cariKodu, String cariAdi, BigDecimal toplamBorc, BigDecimal toplamAlacak, BigDecimal bakiye, BigDecimal yillikHedef, BigDecimal toplamCiro) {
        this.cariKodu = cariKodu;
        this.cariAdi = cariAdi;
        this.toplamBorc = toplamBorc;
        this.toplamAlacak = toplamAlacak;
        this.bakiye = bakiye;
        this.toplamCiro = toplamCiro;
        this.yillikHedef = yillikHedef;
    }
}
