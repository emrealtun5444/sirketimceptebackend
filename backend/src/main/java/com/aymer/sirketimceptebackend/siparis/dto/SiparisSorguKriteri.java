package com.aymer.sirketimceptebackend.siparis.dto;

import com.aymer.sirketimceptebackend.common.api.dto.LazyLoadEvent;
import com.aymer.sirketimceptebackend.siparis.model.SiparisDurumu;
import com.aymer.sirketimceptebackend.siparis.model.SiparisYonu;
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
public class SiparisSorguKriteri implements Serializable {

    private String siparisNo;
    private String cariKodu;
    private String cariAdi;
    private SiparisDurumu siparisDurumu;
    private SiparisYonu siparisYonu;
    private Date baslangicTarihi;
    private Date bitisTarihi;
    private Long staff;

    @Valid
    @NotNull
    private LazyLoadEvent lazyLoadEvent;

}
