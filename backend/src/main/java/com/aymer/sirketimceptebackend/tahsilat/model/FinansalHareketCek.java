package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.common.constants.IConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@DiscriminatorValue(IConstants.CEK)
public class FinansalHareketCek extends FinansalHareket {

    @NotNull
    @Column(name = "vade")
    private Date vade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "banka_id")
    private Banka banka;

    @NotNull
    @Column(name = "sube_adi")
    private String subeAdi;

    @Column(name = "borclu_adi")
    private String borcluAdi;

    @Column(name = "borclu_adresi")
    private String borcluAdresi;

    @NotNull
    @Column(name = "evrak_no")
    private String evrakNo;


}
