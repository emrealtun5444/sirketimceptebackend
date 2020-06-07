package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EBelgeTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
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
@Entity
@Table(name = "fatura")
public class Fatura extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cari_kart_id")
    private CariKart cariKart;

    @Column(name = "fatura_tarihi", length = 20)
    private Date faturaTarihi;

    @Column(name = "fatura_no", length = 128)
    private String faturaNo;

    @Column(name = "toplam_tutar")
    private BigDecimal toplamTutar;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
