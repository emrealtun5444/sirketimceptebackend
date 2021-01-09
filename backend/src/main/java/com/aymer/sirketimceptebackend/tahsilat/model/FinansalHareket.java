package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "odeme_tipi", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name = "finansal_hareket")
public class FinansalHareket extends Auditable<String> implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

    @NotNull
    @Column(name = "evrak_no")
    private String evrakNo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "odeme_yonu", length = 20)
    private EOdemeYonu odemeYonu;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "odeme_tipi", length = 20, updatable = false, insertable = false)
    private EOdemeTipi odemeTipi;

    @NotNull
    @Column(name = "tutar")
    private BigDecimal tutar;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "kdv_orani", length = 20)
    private EKdvOrani kdvOrani;

    @Column(name = "aciklama")
    private String aciklama;

    @NotNull
    @Column(name = "islem_tarihi", length = 20)
    private Date islemTarihi;

    @NotNull
    @Column(name = "vade")
    private Date vadeTarihi;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    public void init(CariKart cariKart) {
        this.cariKart = cariKart;
        this.sirket = cariKart.getSirket();
        this.durum = EDurum.AKTIF;
    }
}
