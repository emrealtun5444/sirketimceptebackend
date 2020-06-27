package com.aymer.sirketimceptebackend.controller.carikart.dto;

import com.aymer.sirketimceptebackend.controller.common.dto.LazyLoadEvent;
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

    private String cariKodu;
    private String cariAdi;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
