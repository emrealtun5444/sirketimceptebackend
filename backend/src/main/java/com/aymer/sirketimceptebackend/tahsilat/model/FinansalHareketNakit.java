package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.common.constants.IConstants;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ealtun
 * Date: 7.06.2020
 * Time: 16:15
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(IConstants.NAKIT)
public class FinansalHareketNakit extends FinansalHareket {
}
