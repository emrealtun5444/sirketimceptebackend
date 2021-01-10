package com.aymer.sirketimceptebackend.tahsilat.dto;

import com.aymer.sirketimceptebackend.common.api.dto.LazyLoadEvent;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeTipi;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
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
public class TahsilatSorguKriteri implements Serializable {

    private Long staff;
    private String evrakNo;
    private String cariKodu;
    private String cariAdi;
    private EOdemeTipi odemeTipi;
    private EOdemeYonu odemeYonu;
    private Date baslangicTarihi;
    private Date bitisTarihi;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
