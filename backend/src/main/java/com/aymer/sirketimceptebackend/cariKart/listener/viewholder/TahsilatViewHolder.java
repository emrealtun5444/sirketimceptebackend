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
    String hesapKodu;
    Date islemTarihi;
    String evrakNo;
    String aciklama;
    EOdemeYonu odemeYonu;
    EOdemeTipi odemeTipi;
    BigDecimal tutar;
    Date vadeTarihi;
    EKdvOrani kdvOrani;
}
