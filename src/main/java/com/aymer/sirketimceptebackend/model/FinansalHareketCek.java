package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.constants.IConstants;
import com.aymer.sirketimceptebackend.model.common.Banka;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "vade")
    private Date vade;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "banka_id")
    private Banka banka;

    @NotBlank
    @Column(name = "sube_adi")
    private String subeAdi;

    @Column(name = "borclu_adi")
    private String borcluAdi;

    @Column(name = "borclu_adresi")
    private String borcluAdresi;

    @NotBlank
    @Column(name = "evrak_no")
    private String evrakNo;


}
