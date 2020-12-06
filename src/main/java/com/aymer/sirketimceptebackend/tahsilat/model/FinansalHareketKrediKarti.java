package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.common.constants.IConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * User: ealtun
 * Date: 7.06.2020
 * Time: 16:15
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(IConstants.KREDI_KARTI)
public class FinansalHareketKrediKarti extends FinansalHareket {

    @ManyToOne
    @JoinColumn(name = "banka_id")
    private Banka banka;

}
