package com.aymer.sirketimceptebackend.controller.fatura.dto;

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
public class FaturaSorguKriteri implements Serializable {

    private String cariKodu;
    private String cariAdi;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
