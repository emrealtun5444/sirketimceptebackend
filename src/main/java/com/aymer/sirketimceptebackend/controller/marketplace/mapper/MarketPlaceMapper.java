package com.aymer.sirketimceptebackend.controller.marketplace.mapper;

import com.aymer.sirketimceptebackend.controller.marketplace.dto.MarketPlaceConfDto;
import com.aymer.sirketimceptebackend.model.MarketPlaceSettings;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class MarketPlaceMapper {

    public abstract MarketPlaceConfDto marketPlaceConfToDto(MarketPlaceSettings marketPlaceSettings);

    public abstract List<MarketPlaceConfDto> marketPlaceConfToDtoList(List<MarketPlaceSettings> marketPlaceSettingsList);

    public abstract void updateMarketPlaceConf(MarketPlaceConfDto marketPlaceConfDto, @MappingTarget MarketPlaceSettings marketPlaceSettings);


}
