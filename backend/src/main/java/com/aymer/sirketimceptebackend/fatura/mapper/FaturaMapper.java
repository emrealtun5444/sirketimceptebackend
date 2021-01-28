package com.aymer.sirketimceptebackend.fatura.mapper;

import com.aymer.sirketimceptebackend.fatura.dto.FaturaDetayDto;
import com.aymer.sirketimceptebackend.fatura.dto.FaturaDto;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.FaturaDetayViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.FaturaViewHolder;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            @Mapping(target = "iskontoOrani", source = "iskontoOrani")
    })
    public abstract FaturaDetayDto faturaDetayToDto(FaturaDetay faturaDetay);
    public abstract List<FaturaDetayDto> faturaDetayToDtoList(List<FaturaDetay> faturaDetayList);


    public abstract void updateFatura(FaturaViewHolder faturaViewHolder, @MappingTarget Fatura fatura);
    public abstract void updateFaturaDetay(FaturaDetayViewHolder faturaDetayViewHolder, @MappingTarget FaturaDetay faturaDetay);
}

