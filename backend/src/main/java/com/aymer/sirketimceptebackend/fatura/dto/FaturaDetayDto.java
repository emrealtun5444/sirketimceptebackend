package com.aymer.sirketimceptebackend.fatura.dto;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: ealtun
 * <p>
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class FaturaDetayDto implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String stokKodu;

    @NotNull
    private String urunAdi;

    @NotNull
    private Date islemTarihi;

    @NotNull
    private Integer miktar;

    @NotNull
    private BigDecimal birimFiyati;

    @NotNull
    private BigDecimal tutar;

    @NotNull
    private BigDecimal iskonto;

    @NotNull
    private BigDecimal kdvTutari;

    @NotNull
    private BigDecimal toplamTutar;

    @NotNull
    private EKdvOrani kdvOrani;

    @NotNull
    private EDurum durum;

}
