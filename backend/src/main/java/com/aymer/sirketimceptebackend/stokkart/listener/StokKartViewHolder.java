package com.aymer.sirketimceptebackend.stokkart.listener;

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
public class StokKartViewHolder implements Serializable {

    private Long id;

    private String stokKodu;

    private String aciklama;

    private BigDecimal birimFiyati;

    private Long miktar;

    private Long sirketId;

    @Override
    public String toString() {
        return "StokKartDto{" +
                "id=" + id +
                ", stokKodu='" + stokKodu + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", birimFiyati=" + birimFiyati +
                ", miktar=" + miktar +
                '}';
    }
}
