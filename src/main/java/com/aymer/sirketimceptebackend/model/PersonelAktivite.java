package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.EAktiviteTipi;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
@Table(name = "personel_aktivite")
public class PersonelAktivite extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "aktivite_tipi", length = 20)
    private EAktiviteTipi aktiviteTipi;

    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

    @NotBlank
    @Column(name = "konu", length = 512)
    private String konu;

    @NotBlank
    @Column(name = "aciklama", length = 4000)
    private String aciklama;

    @NotBlank
    @Column(name = "aktivite_zamani", length = 20)
    private Date aktiviteZamani;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
