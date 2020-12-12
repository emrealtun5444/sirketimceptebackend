package com.aymer.sirketimceptebackend.system.sirket.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Idendifier;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Sirket extends Auditable<String> implements Serializable, Idendifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "sirket_adi")
    private String sirketAdi;

    @Column(name = "sirket_logo")
    private String sirketLogo;

    @Column(name = "adress", length = 1024)
    private String adres;

    @NotNull
    @Column(name = "eposta")
    private String eposta;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;


    @Override
    public String getAciklama() {
        return sirketAdi;
    }
}
