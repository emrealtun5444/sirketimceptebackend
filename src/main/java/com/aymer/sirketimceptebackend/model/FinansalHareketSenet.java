package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.constants.IConstants;
import com.aymer.sirketimceptebackend.model.common.Banka;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "vade")
    private Date vade;

    @Column(name = "borclu_adi")
    private String borcluAdi;

    @Column(name = "borclu_adresi")
    private String borcluAdresi;

    @Column(name = "evrak_no")
    private String evrakNo;

}
