package com.aymer.sirketimceptebackend.siparis.dto;


import com.aymer.sirketimceptebackend.siparis.model.SiparisDurumu;
import com.aymer.sirketimceptebackend.siparis.model.SiparisYonu;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class SiparisDto implements Serializable {
    private Long id;
    private String malKodu;
    private String urunAdi;
    private Date islemTarihi;
    private SiparisYonu siparisYonu;
    private String cariKodu;
    private String cariAdi;
    private String siparisNo;
    private Long miktar;
    private Long kalanMiktar;
    private Long stokMiktari;
    private BigDecimal birimFiyati;
    private BigDecimal tutari;
    private BigDecimal iskonto;
    private BigDecimal kdvTutari;
    private String faturaNo;
    private Long teslimMiktari;
    private SiparisDurumu siparisDurumu;
    private Date teslimTarihi;
}
