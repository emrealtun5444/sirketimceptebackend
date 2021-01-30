package com.aymer.sirketimceptebackend.stokkart.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.stokkart.model.Kategori;
import com.aymer.sirketimceptebackend.stokkart.model.Marka;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import com.aymer.sirketimceptebackend.tahsilat.model.EParaBirimi;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stok_kart")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StokKart extends Auditable<String> implements Serializable {

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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "para_birimi", length = 20)
    private EParaBirimi paraBirimi;

    @NotNull
    @Column(name = "urun_adi", length = 255)
    private String urunAdi;

    @Column(name = "urun_aciklama", length = 4000)
    private String urunAciklama;

    @Column(name = "urun_fiyat")
    private BigDecimal urunFiyat;

    @Column(name = "stok_adedi")
    private Long stokAdedi;

    @NotNull
    @Column(name = "stok_kodu")
    private String stokKodu;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "kdv_orani", length = 20)
    private EKdvOrani kdvOrani;

    @Column(name = "urun_gorsel", length = 4000)
    private String urunGorsel;

    @Column(name = "varyant")
    private String varyant;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    public void updateStokAndPrice(String aciklama, Long stokAdedi, BigDecimal urunFiyati) {
        this.setStokAdedi(stokAdedi);
        this.setUrunFiyat(urunFiyati);
        this.setUrunAdi(aciklama);
    }

}
