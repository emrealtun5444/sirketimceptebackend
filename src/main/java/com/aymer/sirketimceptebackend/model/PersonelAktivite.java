package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

    @Column(name = "konu", length = 512)
    private String konu;

    @Column(name = "aciklama", length = 4000)
    private String aciklama;

    @Column(name = "aktivite_zamani",length = 20)
    private Date aktiviteZamani;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
