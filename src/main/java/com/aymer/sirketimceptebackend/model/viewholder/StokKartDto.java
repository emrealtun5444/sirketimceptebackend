package com.aymer.sirketimceptebackend.model.viewholder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class StokKartDto implements Serializable {

    private Long id;

    private String stokKodu;

    private String aciklama;

    private BigDecimal birimFiyati;

    private Long miktar;

    private Long sirketId;
}
