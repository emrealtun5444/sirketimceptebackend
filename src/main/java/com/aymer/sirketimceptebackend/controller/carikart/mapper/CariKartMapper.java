package com.aymer.sirketimceptebackend.controller.carikart.mapper;

import com.aymer.sirketimceptebackend.controller.carikart.dto.CariKartDto;
import com.aymer.sirketimceptebackend.listener.carikart.CariKartViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class CariKartMapper {

    @Mappings(
        @Mapping(target = "sorumluPersonel", source = "sorumluPersonel.name")
    )
    public abstract CariKartDto carikartToDto(CariKart cariKart);

    public abstract List<CariKartDto> carikartToDtoList(List<CariKart> cariKarts);

    @Mappings({
        @Mapping(target = "cariKodu", source = "hesapKodu"),
        @Mapping(target = "cariMail", source = "emailAdresi"),
        @Mapping(target = "cariTel", source = "telefon1"),
        @Mapping(target = "toplamAlacak", source = "toplamAlacak"),
        @Mapping(target = "toplamBorc", source = "toplamBorc"),
        @Mapping(target = "vergiDairesi", source = "vergiDairesi"),
        @Mapping(target = "vergiNumarasi", source = "vergiHesapNo"),
        @Mapping(target = "cariTipi", source = "cariTipi"),
        @Mapping(target = "cariAdres", expression = "java(cariKartDto.getAdres1() + \" \" + cariKartDto.getAdres2() + \" \" + cariKartDto.getAdres3())"),
        @Mapping(target = "cariAdi", expression = "java(cariKartDto.getUnvan1() + \" \" + cariKartDto.getUnvan2())")
    })
    public abstract void updateCariKart(CariKartViewHolder cariKartDto, @MappingTarget CariKart cariKart);


}
