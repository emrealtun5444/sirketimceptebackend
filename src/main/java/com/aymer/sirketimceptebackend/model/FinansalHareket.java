package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
import com.aymer.sirketimceptebackend.model.enums.EOdemeTipi;
import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

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
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;
}
