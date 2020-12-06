package com.aymer.sirketimceptebackend.system.contactinformation.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.common.model.enums.EIletisimBilgiTipi;
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
@Table(name = "iletisim_bilgi")
public class IletisimBilgi extends Auditable<String> implements Serializable {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "bilgi_tipi", length = 20)
    private EIletisimBilgiTipi bilgiTipi;

    @NotNull
    @Column(name = "kisa_aciklama")
    String kisaAciklama;

    @NotNull
    @Column(name = "aciklama")
    String aciklama;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
