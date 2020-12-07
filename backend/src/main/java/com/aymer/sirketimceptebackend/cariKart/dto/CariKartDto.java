package com.aymer.sirketimceptebackend.cariKart.dto;

import com.aymer.sirketimceptebackend.cariKart.model.ECariTipi;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * <p>
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class CariKartDto implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private ECariTipi cariTipi;

    @NotNull
    private String cariKodu;

    @NotNull
    private String cariAdi;

    private String cariAdres;

    private String cariTel;

    private String cariMail;

    private BigDecimal toplamBorc;
    private BigDecimal toplamAlacak;

    private BigDecimal bakiye;

    @NotNull
    private EDurum durum;

    private String sorumluPersonel;

}
