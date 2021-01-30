package com.aymer.sirketimceptebackend.cariKart.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.system.contactinformation.model.Ilce;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cari_kart")
public class CariKart extends Auditable<String> implements Serializable {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cari_tipi", length = 20)
    private ECariTipi cariTipi;

    @NotNull
    @Column(name = "cari_kodu")
    private String cariKodu;

    @NotNull
    @Column(name = "cari_adi", length = 512)
    private String cariAdi;

    @Column(name = "cari_adres", length = 2048)
    private String cariAdres;

    @Column(name = "cari_tel")
    private String cariTel;

    @Column(name = "cari_mail")
    private String cariMail;

    @Column(name = "toplam_borc")
    private BigDecimal toplamBorc;

    @Column(name = "toplam_alacak")
    private BigDecimal toplamAlacak;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sorumlu_personel_id")
    private User sorumluPersonel;

    @Column(name = "yillik_hedef")
    private BigDecimal yillikHedef;

    @ManyToOne
    @JoinColumn(name = "ilce_id")
    private Ilce ilce;

    @Column(name = "vergi_dairesi", length = 512)
    private String vergiDairesi;

    @Column(name = "vergi_numarasi", length = 128)
    private String vergiNumarasi;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    public BigDecimal getBakiye() {
        return toplamAlacak.subtract(toplamBorc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CariKart cariKart = (CariKart) o;

        return cariKodu.equals(cariKart.cariKodu);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cariKodu.hashCode();
        return result;
    }
}
