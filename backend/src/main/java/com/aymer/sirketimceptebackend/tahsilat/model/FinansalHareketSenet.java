package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.common.constants.IConstants;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * User: ealtun
 * Date: 7.06.2020
 * Time: 16:15
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(IConstants.SENET)
public class FinansalHareketSenet extends FinansalHareket {

    @Column(name = "borclu_adi")
    private String borcluAdi;

    @Column(name = "borclu_adresi")
    private String borcluAdresi;

}
