package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Ilce;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.ECariTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(name = "cari_kart")
public class CariKart extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "cari_tipi", length = 20)
    private ECariTipi cariTipi;

    @NotBlank
    @Column(name = "cari_kodu")
    private String cariKodu;

    @NotBlank
    @Column(name = "cari_adi", length = 512)
    private String cariAdi;

    @Column(name = "cari_adres", length = 2048)
    private String cariAdres;

    @Column(name = "cari_tel")
    private String cariTel;

    @Column(name = "cari_mail")
    private String cariMail;

    @Column(name = "toplam_borc")
    private BigDecimal toplamBorc;

    @Column(name = "toplam_alacak")
    private BigDecimal toplamAlacak;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sorumlu_personel_id")
    private User sorumluPersonel;

    @Column(name = "yillik_hedef")
    private BigDecimal yillikHedef;

    @ManyToOne
    @JoinColumn(name = "ilce_id")
    private Ilce ilce;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
