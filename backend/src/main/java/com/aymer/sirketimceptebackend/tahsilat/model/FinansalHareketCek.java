package com.aymer.sirketimceptebackend.tahsilat.model;

import com.aymer.sirketimceptebackend.common.constants.IConstants;
import lombok.*;

import javax.persistence.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(IConstants.CEK)
public class FinansalHareketCek extends FinansalHareket {

    @NotNull
    @Column(name = "banka")
    private String banka;

    @Column(name = "sube_adi")
    private String subeAdi;

    @Column(name = "banka_hesap_no")
    private String bankaHesapNo;

    @Column(name = "banka_cek_no")
    private String bankaCekNo;

    @Column(name = "borclu_adi")
    private String borcluAdi;

    @Column(name = "borclu_adresi")
    private String borcluAdresi;

}
