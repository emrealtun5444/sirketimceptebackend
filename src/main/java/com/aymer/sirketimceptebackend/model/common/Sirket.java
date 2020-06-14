package com.aymer.sirketimceptebackend.model.common;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(name = "sirket")
public class Sirket extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "sirket_adi")
    private String sirketAdi;

    @Column(name = "sirket_logo")
    private String sirketLogo;

    @Column(name = "adress", length = 1024)
    private String adres;

    @NotBlank
    @Column(name = "eposta")
    private String eposta;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;


}
