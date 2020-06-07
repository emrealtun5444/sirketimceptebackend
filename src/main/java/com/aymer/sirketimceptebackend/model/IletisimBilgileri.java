package com.aymer.sirketimceptebackend.model;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@Table(name = "iletisim_bilgileri")
public class IletisimBilgileri extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bilgi_tipi")
    String bilgiTipi;

    @Column(name = "bilgi_tipi_ack")
    String bilgiTipiAck;

    @Column(name = "aciklama")
    String iletisimBilgisi;

    @ManyToOne
    @JoinColumn(name = "sirket_id")
    private Sirket sirket;

}
