package com.aymer.sirketimceptebackend.controller.fatura.dto;

import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
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
public class FaturaKalemDto implements Serializable {

    @NotNull
    private Integer satirNo;

    @NotNull
    private String satirTipi;

    private String satirAciklama;

    private BigDecimal tutar;
    private BigDecimal kdvOrani;

}
