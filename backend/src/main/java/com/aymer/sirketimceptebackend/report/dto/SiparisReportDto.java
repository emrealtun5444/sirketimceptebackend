package com.aymer.sirketimceptebackend.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiparisReportDto {
    private String cariAdi;
    private String siparisNo;
    private Date siparisTarihi;
    private String stokKodu;
    private String urunAdi;
    private Long miktar;
    private Long teslimMiktari;
    private Long kalanMiktar;
    private Long stokMiktar;
    private String siparisDurumu;
}
