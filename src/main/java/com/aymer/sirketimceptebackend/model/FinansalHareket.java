package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Banka;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EBelgeTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EOdemeTipi;
import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EOdemeYonu odemeYonu;

    @Enumerated(EnumType.STRING)
    @Column(name = "odeme_tipi", length = 20)
    private EOdemeTipi odemeTipi;

    @Column(name = "tutar")
    private BigDecimal tutar;

    @Column(name = "aciklama")
    private String aciklama;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;
}
