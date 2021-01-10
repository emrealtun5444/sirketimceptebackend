package com.aymer.sirketimceptebackend.cariKart.listener.viewholder;


import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeTipi;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
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
public class TahsilatViewHolder implements Serializable {
    private String hesapKodu;
    private Date islemTarihi;
    private String evrakNo;
    private String aciklama;
    private EOdemeYonu odemeYonu;
    private EOdemeTipi odemeTipi;
    private BigDecimal tutar;
    private Date vadeTarihi;
    private EKdvOrani kdvOrani;

    private String banka;
    private String subeAdi;
    private String bankaHesapNo;
    private String bankaCekNo;
    private String borcluAdi;
    private String borcluAdresi;


}
