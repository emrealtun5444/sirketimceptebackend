package com.aymer.sirketimceptebackend.belge.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "belge")
public class Belge extends Auditable<String> implements Serializable {

    @NotNull
    @Lob
    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @NotNull
    @Column(name = "fileName", length = 512)
    private String fileName;

    @NotNull
    @Column(name = "kisa_aciklama", length = 255)
    private String kisaAciklama;

    @Column(name = "aciklama", length = 2048)
    private String aciklama;

    @NotNull
    @Column(name = "size")
    private Long size;

    @NotNull
    @Column(name = "minetype")
    private String minetype;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "belge_tipi", length = 20)
    private EBelgeTipi belgeTipi;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

    public void initialize(Sirket sirket, String fileName, String minetype, EBelgeTipi belgeTipi, byte[] excelBytes) {
        this.setBelgeTipi(belgeTipi);
        this.setContent(excelBytes);
        this.setSize((long) excelBytes.length);
        this.setFileName(fileName);
        this.setMinetype(minetype);
        this.setDurum(EDurum.AKTIF);
        this.setSirket(sirket);
    }

}
