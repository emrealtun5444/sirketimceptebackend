package com.aymer.sirketimceptebackend.listener.carikart.viewholder;

import com.aymer.sirketimceptebackend.listener.carikart.visitor.CariKartVisitor;
import com.aymer.sirketimceptebackend.listener.carikart.visitor.ItemElement;
import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class CariKartViewHolder implements Serializable, ItemElement {

    private Long id;
    private String hesapKodu;
    private ECariTipi cariTipi;
    private String unvan1;
    private String unvan2;
    private String adres1;
    private String adres2;
    private String adres3;
    private String vergiDairesi;
    private String vergiHesapNo;
    private String telefon1;
    private String emailAdresi;
    private String ozelKod;
    private BigDecimal toplamBorc;
    private BigDecimal toplamAlacak;
    private Long sirketId;

    // fatura detayları
    private List<FaturaViewHolder> faturaList;

    @Override
    public void accept(CariKartVisitor visitor) {
        visitor.visit(this);
    }

}
