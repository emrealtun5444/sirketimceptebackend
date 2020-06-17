package com.aymer.sirketimceptebackend.controller.stokkart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StokKartSorguKriteri implements Serializable {

    private String stokKodu;

    private String urunAdi;

    private Long stokAdedi;

}
