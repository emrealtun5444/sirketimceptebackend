package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EBelgeTipi;
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
@Table(name = "belge")
public class Belge extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Lob
    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @NotBlank
    @Column(name = "file_name", length = 512)
    private String fileName;

    @NotBlank
    @Column(name = "kisa_aciklama", length = 255)
    private String kisaAciklama;

    @Column(name = "aciklama", length = 2048)
    private String aciklama;

    @NotBlank
    @Column(name = "size")
    private Long size;

    @NotBlank
    @Column(name = "minetype")
    private String minetype;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "belge_tipi", length = 20)
    private EBelgeTipi belgeTipi;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
