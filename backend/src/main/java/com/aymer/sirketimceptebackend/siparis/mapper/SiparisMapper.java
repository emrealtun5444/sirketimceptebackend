package com.aymer.sirketimceptebackend.siparis.mapper;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.SiparisViewHolder;
import com.aymer.sirketimceptebackend.siparis.dto.SiparisDto;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
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
public abstract class SiparisMapper {

    @Mappings({
        @Mapping(target = "cariAdi", source = "cariKart.cariAdi"),
        @Mapping(target = "cariKodu", source = "cariKart.cariKodu"),
        @Mapping(target = "malKodu", source = "stokKart.stokKodu"),
        @Mapping(target = "urunAdi", source = "stokKart.urunAdi"),
        @Mapping(target = "faturaNo", source = "faturaNo"),
        @Mapping(target = "kalanMiktar", source = "kalanMiktar"),
        @Mapping(target = "stokMiktari", source = "stokKart.stokAdedi")
    })
    public abstract SiparisDto toDto(Siparis siparis);

    public abstract List<SiparisDto> toDtoList(List<Siparis> siparisList);

    public abstract void update(SiparisViewHolder siparisViewHolder, @MappingTarget Siparis siparis);
}
