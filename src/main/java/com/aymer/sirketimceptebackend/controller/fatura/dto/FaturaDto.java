package com.aymer.sirketimceptebackend.controller.fatura.dto;

import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
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
public class FaturaDto implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String cariKodu;

    @NotNull
    private String cariAdi;

    @NotNull
    private EOdemeYonu faturaYonu;

    @NotNull
    private Date faturaTarihi;

    @NotNull
    private String faturaNo;


    private BigDecimal malBedeli;
    private BigDecimal iskonto;
    private BigDecimal netToplam;
    private BigDecimal kdvTutari;
    private BigDecimal toplamTutar;

    @NotNull
    private EDurum durum;

}
