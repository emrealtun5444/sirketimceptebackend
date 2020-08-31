package com.aymer.sirketimceptebackend.controller.fatura.mapper;

import com.aymer.sirketimceptebackend.controller.carikart.dto.CariKartDto;
import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaDetayDto;
import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaDto;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.FaturaDetayViewHolder;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.FaturaViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.model.FaturaDetay;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class FaturaMapper {

    @Mappings({
        @Mapping(target = "cariAdi", source = "cariKart.cariAdi"),
        @Mapping(target = "cariKodu", source = "cariKart.cariKodu"),
    })
    public abstract FaturaDto faturaToDto(Fatura fatura);
    public abstract List<FaturaDto> faturaToDtoList(List<Fatura> faturaList);

    @Mappings({
            @Mapping(target = "stokKodu", source = "stokKart.stokKodu"),
            @Mapping(target = "urunAdi", source = "stokKart.urunAdi"),
    })
    public abstract FaturaDetayDto faturaDetayToDto(FaturaDetay faturaDetay);
    public abstract List<FaturaDetayDto> faturaDetayToDtoList(List<FaturaDetay> faturaDetayList);


    public abstract void updateFatura(FaturaViewHolder faturaViewHolder, @MappingTarget Fatura fatura);
    public abstract void updateFaturaDetay(FaturaDetayViewHolder faturaDetayViewHolder, @MappingTarget FaturaDetay faturaDetay);

}
