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
@Getter
@Setter
public class CekTahsilatViewHolder extends TahsilatViewHolder implements Serializable {

    private String banka;
    private String subeAdi;
    private String bankaHesapNo;
    private String bankaCekNo;
    private String borcluAdi;
    private String borcluAdresi;

}
