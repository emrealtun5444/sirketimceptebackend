package com.aymer.sirketimceptebackend.report.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.stokkart.model.Marka;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Maliyet extends Auditable<String> implements Serializable {

    @ManyToOne
    @JoinColumn
    @NotNull
    private Marka marka;

    @NotNull
    @Column
    private Date baslangicTarihi;

    @NotNull
    @Column
    private Date bitisTarihi;

    @NotNull
    @Column
    private BigDecimal oran;

}
