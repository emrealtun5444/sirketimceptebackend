package com.aymer.sirketimceptebackend.belge.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
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
public class Belge extends Auditable<String> implements Documentable, Serializable {

    @NotNull
    @Lob
    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    private byte[] documentContent;

    @NotNull
    @Column(name = "file_name", length = 512)
    private String fileName;

    @NotNull
    @Column(name = "kisa_aciklama", length = 255)
    private String kisaAciklama;

    @Column(name = "aciklama", length = 2048)
    private String aciklama;

    @NotNull
    @Column(name = "size")
    private Long DocumentSize;

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
        this.setDocumentContent(excelBytes);
        this.setDocumentSize((long) excelBytes.length);
        this.setFileName(!fileName.contains(".xlsx") ? fileName.concat(".xlsx") : fileName);
        this.setKisaAciklama(this.getFileName());
        this.setMinetype(minetype);
        this.setDurum(EDurum.AKTIF);
        this.setSirket(sirket);
    }

}
