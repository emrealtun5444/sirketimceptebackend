package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.Belge;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
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
@Table(name = "sirket_belge")
public class SirketBelge extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    @ManyToOne
    @JoinColumn(name = "belge_id")
    private Belge belge;

}
