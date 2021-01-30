package com.aymer.sirketimceptebackend.stokkart.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Idendifier;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Table(name = "kategori")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Kategori extends Auditable<String> implements Serializable , Idendifier {

    @Column(name = "kisa_aciklama")
    private String kisaAciklama;

    @NotNull
    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;
}
