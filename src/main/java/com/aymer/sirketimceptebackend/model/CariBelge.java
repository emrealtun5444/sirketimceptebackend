package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
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
@Table(name = "cari_belge")
public class CariBelge extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

    @ManyToOne
    @JoinColumn(name = "belge_id")
    private Belge belge;

}
