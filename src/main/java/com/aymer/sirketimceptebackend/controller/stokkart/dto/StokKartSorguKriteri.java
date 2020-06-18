package com.aymer.sirketimceptebackend.controller.stokkart.dto;

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
public class StokKartSorguKriteri implements Serializable {

    private String stokKodu;

    private String urunAdi;

    private Long stokAdedi;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
