package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@Entity
@Table(name = "fatura")
@TypeDef(
    name = "jsonb-node",
    typeClass = JsonBinaryType.class,
    defaultForType = JsonNode.class
)
public class Fatura extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
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
    private JsonNode faturaKalemInfo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
