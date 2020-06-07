package com.aymer.sirketimceptebackend.model.common;

import com.aymer.sirketimceptebackend.model.abstructcommon.Auditable;
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
@Table(name = "ilce")
public class Ilce extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "il_id")
    private Il il;

    @NotBlank
    @Column(name = "aciklama")
    private String aciklama;

}
