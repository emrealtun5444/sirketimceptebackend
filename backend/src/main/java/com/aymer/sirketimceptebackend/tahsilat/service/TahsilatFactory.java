package com.aymer.sirketimceptebackend.tahsilat.service;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.CekTahsilatViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.NakitTahsilatViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.SenetTahsilatViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.TahsilatViewHolder;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.tahsilat.model.*;

public class TahsilatFactory {

    public static FinansalHareket create(TahsilatViewHolder tahsilatViewHolder, CariKart cariKart) {
        FinansalHareket finansalHareket = null;
        if (tahsilatViewHolder.getOdemeTipi().equals(EOdemeTipi.CEK)) {
            CekTahsilatViewHolder cekTahsilatViewHolder = (CekTahsilatViewHolder) tahsilatViewHolder;
            finansalHareket = FinansalHareketCek.builder()
                    .banka(cekTahsilatViewHolder.getBanka())
                    .subeAdi(cekTahsilatViewHolder.getSubeAdi())
                    .bankaHesapNo(cekTahsilatViewHolder.getBankaHesapNo())
                    .bankaCekNo(cekTahsilatViewHolder.getBankaCekNo())
                    .borcluAdi(cekTahsilatViewHolder.getBorcluAdi())
                    .borcluAdresi(cekTahsilatViewHolder.getBorcluAdresi())
                    .build();
        } else if (tahsilatViewHolder.getOdemeTipi().equals(EOdemeTipi.SENET)) {
            SenetTahsilatViewHolder senetTahsilatViewHolder = (SenetTahsilatViewHolder) tahsilatViewHolder;
            finansalHareket = FinansalHareketSenet.builder()
                    .borcluAdi(senetTahsilatViewHolder.getBorcluAdi())
                    .borcluAdresi(senetTahsilatViewHolder.getBorcluAdresi())
                    .build();
        } else if (tahsilatViewHolder.getOdemeTipi().equals(EOdemeTipi.NAKIT)) {
            NakitTahsilatViewHolder nakitTahsilatViewHolder = (NakitTahsilatViewHolder) tahsilatViewHolder;
            finansalHareket = FinansalHareketNakit.builder().build();
        }

        finansalHareket.init(cariKart);
        return finansalHareket;
    }

}
