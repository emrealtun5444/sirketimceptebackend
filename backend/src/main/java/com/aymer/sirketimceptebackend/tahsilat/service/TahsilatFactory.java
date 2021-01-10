package com.aymer.sirketimceptebackend.tahsilat.service;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.TahsilatViewHolder;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.tahsilat.model.*;

public class TahsilatFactory {

    public static FinansalHareket create(TahsilatViewHolder tahsilatViewHolder, CariKart cariKart) {
        FinansalHareket finansalHareket = null;
        if (tahsilatViewHolder.getOdemeTipi().equals(EOdemeTipi.CEK)) {

            finansalHareket = FinansalHareketCek.builder()
                    .banka(tahsilatViewHolder.getBanka())
                    .subeAdi(tahsilatViewHolder.getSubeAdi())
                    .bankaHesapNo(tahsilatViewHolder.getBankaHesapNo())
                    .bankaCekNo(tahsilatViewHolder.getBankaCekNo())
                    .borcluAdi(tahsilatViewHolder.getBorcluAdi())
                    .borcluAdresi(tahsilatViewHolder.getBorcluAdresi())
                    .build();
        } else if (tahsilatViewHolder.getOdemeTipi().equals(EOdemeTipi.SENET)) {
            finansalHareket = FinansalHareketSenet.builder()
                    .borcluAdi(tahsilatViewHolder.getBorcluAdi())
                    .borcluAdresi(tahsilatViewHolder.getBorcluAdresi())
                    .build();
        } else if (tahsilatViewHolder.getOdemeTipi().equals(EOdemeTipi.NAKIT)) {
            finansalHareket = FinansalHareketNakit.builder().build();
        }

        finansalHareket.init(cariKart);
        return finansalHareket;
    }

}
