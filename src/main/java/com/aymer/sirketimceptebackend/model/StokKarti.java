package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Kategori;
import com.aymer.sirketimceptebackend.model.common.Marka;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
import com.aymer.sirketimceptebackend.model.enums.EParaBirimi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stok_karti")
public class StokKarti extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    @Column(name = "barkod", length = 50)
    private String barkod;

    @Column(name = "model_kodu")
    private String modelKodu;

    @ManyToOne
    @JoinColumn(name = "marka_id")
    private Marka marka;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EParaBirimi paraBirimi;

    @Column(name = "urun_adi", length = 256)
    private String urunAdi;

    @Column(name = "urun_aciklama", length = 4000)
    private String urunAciklama;

    @Column(name = "urun_fiyat")
    private BigDecimal urunFiyat;

    @Column(name = "stok_adedi")
    private Long stokAdedi;

    @Column(name = "stok_kodu")
    private String stokKodu;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EKdvOrani kdvOrani;

    @Column(name = "urun_gorsel", length = 4000)
    private String urunGorsel;

    @Column(name = "varyant")
    private String varyant;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
