package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
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
@Entity
@Table(name = "fatura_detay")
public class FaturaDetay extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fatura_id")
    private Fatura fatura;

    @NotNull
    @ManyToOne
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


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "kdv_orani", length = 20)
    private EKdvOrani kdvOrani;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

}
