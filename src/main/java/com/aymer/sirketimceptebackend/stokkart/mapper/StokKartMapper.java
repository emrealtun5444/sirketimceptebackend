package com.aymer.sirketimceptebackend.stokkart.mapper;

import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.stokkart.dto.StokKartDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class StokKartMapper {

    public abstract StokKartDto stokkartToDto(StokKart stokKart);

    public abstract List<StokKartDto> stokkartToDtoList(List<StokKart> stokKarts);

}
