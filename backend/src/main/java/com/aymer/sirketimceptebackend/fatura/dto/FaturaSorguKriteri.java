package com.aymer.sirketimceptebackend.fatura.dto;

import com.aymer.sirketimceptebackend.common.api.dto.LazyLoadEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FaturaSorguKriteri implements Serializable {

    private Long staff;
    private String faturaNo;
    private String cariKodu;
    private String cariAdi;
    private Date faturaBaslangicTarihi;
    private Date faturaBitisTarihi;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
