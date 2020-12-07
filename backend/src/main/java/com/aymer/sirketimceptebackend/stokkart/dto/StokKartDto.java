package com.aymer.sirketimceptebackend.stokkart.dto;

import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import com.aymer.sirketimceptebackend.tahsilat.model.EParaBirimi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 *
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class StokKartDto implements Serializable {

    @NotNull
    private Long id;

    private String barkod;

    private String modelKodu;

    @NotNull
    private EParaBirimi paraBirimi;

    @NotNull
    private String urunAdi;

    @NotNull
    private BigDecimal urunFiyat;

    @NotNull
    private Long stokAdedi;

    @NotNull
    private String stokKodu;

    @NotNull
    private EKdvOrani kdvOrani;

    private String urunGorsel;

}
