package com.aymer.sirketimceptebackend.fatura.model;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "fatura")
@TypeDef(
    name = "jsonb-node",
    typeClass = JsonBinaryType.class,
    defaultForType = JsonNode.class
)
public class Fatura extends Auditable<String> implements Serializable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cari_kart_id")
    private CariKart cariKart;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "fatura_yonu", length = 20)
    private EOdemeYonu faturaYonu;

    @NotNull
    @Column(name = "fatura_tarihi", length = 20)
    private Date faturaTarihi;

    @NotNull
    @Column(name = "fatura_no", length = 128)
    private String faturaNo;

    @NotNull
    @Column(name = "mal_bedeli")
    private BigDecimal malBedeli;

    @NotNull
    @Column(name = "iskonto")
    private BigDecimal iskonto;

    @NotNull
    @Column(name = "net_toplam")
    private BigDecimal netToplam;

    @NotNull
    @Column(name = "kdv_tutari")
    private BigDecimal kdvTutari;

    @NotNull
    @Column(name = "toplam_tutar")
    private BigDecimal toplamTutar;

    @Type(type = "jsonb-node")
    @Column(columnDefinition = "jsonb", name = "fatura_kalem_info")
    @Basic(fetch = FetchType.LAZY)
    private JsonNode faturaKalemInfo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    @OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<FaturaDetay> faturaDetays = new HashSet<>();

}
