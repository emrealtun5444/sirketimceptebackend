package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.common.constants.IConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(IConstants.SENET)
public class FinansalHareketSenet extends FinansalHareket {

    @NotNull
    @Column(name = "vade")
    private Date vade;

    @Column(name = "borclu_adi")
    private String borcluAdi;

    @Column(name = "borclu_adresi")
    private String borcluAdresi;

    @NotNull
    @Column(name = "evrak_no")
    private String evrakNo;

}
