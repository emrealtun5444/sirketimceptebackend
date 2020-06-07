package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.constants.IConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * User: ealtun
 * Date: 7.06.2020
 * Time: 16:15
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(IConstants.NAKIT)
public class FinansalHareketNakit extends FinansalHareket {
}
