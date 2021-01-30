package com.aymer.sirketimceptebackend.cariKart.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.belge.model.Belge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONE)
public class CariBelge extends Auditable<String> implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cari_id")
    private CariKart cariKart;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "belge_id")
    private Belge belge;

}
