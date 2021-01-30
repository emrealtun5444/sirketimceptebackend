package com.aymer.sirketimceptebackend.fatura.model;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.report.model.Maliyet;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fatura_detay")
public class FaturaDetay extends Auditable<String> implements Serializable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cari_kart_id")
    private CariKart cariKart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fatura_id")
    private Fatura fatura;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stok_kart_id")
    private StokKart stokKart;

    @NotNull
    @Column(name = "islem_tarihi", length = 20)
    private Date islemTarihi;

    @NotNull
    @Column(name = "miktar")
    private Integer miktar;

    @Column(name = "birim_fiyat")
    private BigDecimal birimFiyati;

    @Column(name = "tutar")
    private BigDecimal tutar;

    @Column(name = "iskonto")
    private BigDecimal iskonto;

    @Column(name = "kdv_tutari")
    private BigDecimal kdvTutari;

    @Column(name = "toplam_tutar")
    private BigDecimal toplamTutar;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "kdv_orani", length = 20)
    private EKdvOrani kdvOrani;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    public BigDecimal getIskontoOrani() {
        return getTutar().compareTo(BigDecimal.ZERO) > 0 ? (getIskonto().divide(getTutar(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))) : BigDecimal.valueOf(0);
    }

    public BigDecimal getMaliyetTutari(Maliyet maliyet) {
        if (maliyet != null) {
            return getTutar().subtract(getTutar().multiply(maliyet.getOran()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
        }
        return getSatisTutari();
    }

    public BigDecimal getSatisTutari() {
        return getTutar().subtract(getIskonto());
    }

}
