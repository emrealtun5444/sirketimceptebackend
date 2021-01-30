package com.aymer.sirketimceptebackend.siparis.model;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "siparis")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONE)
public class Siparis extends Auditable<String> implements Serializable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private CariKart cariKart;

    @NotNull
    private Date islemTarihi;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private SiparisYonu siparisYonu;

    @NotNull
    @Column(length = 128)
    private String siparisNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private StokKart stokKart;

    @NotNull
    private Long miktar;
    private BigDecimal birimFiyati;
    private BigDecimal tutari;
    private BigDecimal iskonto;
    private BigDecimal kdvTutari;
    private String faturaNo;
    private Long teslimMiktari;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private SiparisDurumu siparisDurumu;
    private Date teslimTarihi;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Sirket sirket;

    public Long getKalanMiktar() {
        return this.getMiktar() - this.getTeslimMiktari();
    }



}
