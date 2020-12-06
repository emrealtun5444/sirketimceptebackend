package com.aymer.sirketimceptebackend.system.sirket.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.belge.model.Belge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "sirket_belge")
public class SirketBelge extends Auditable<String> implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "belge_id")
    private Belge belge;

}
