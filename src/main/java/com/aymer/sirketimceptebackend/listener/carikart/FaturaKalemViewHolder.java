package com.aymer.sirketimceptebackend.listener.carikart;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaturaKalemViewHolder implements Serializable {

    private Integer satirNo;
    private String satirTipi;
    private String satirAciklama;
    private BigDecimal tutar;
    private BigDecimal kdvOrani;

}
