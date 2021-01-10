package com.aymer.sirketimceptebackend.cariKart.dto;

import com.aymer.sirketimceptebackend.common.api.dto.LazyLoadEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CariKartSorguKriteri implements Serializable {

    private Long staff;
    private String cariKodu;
    private String cariAdi;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
