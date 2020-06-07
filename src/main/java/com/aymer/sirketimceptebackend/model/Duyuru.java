package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "duyuru")
public class Duyuru extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "konu", length = 512)
    private String konu;

    @Column(name = "aciklama", length = 2048)
    private String aciklama;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
