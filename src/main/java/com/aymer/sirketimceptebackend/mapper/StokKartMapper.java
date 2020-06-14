package com.aymer.sirketimceptebackend.mapper;


import com.aymer.sirketimceptebackend.model.StokKarti;
import com.aymer.sirketimceptebackend.model.viewholder.StokKartDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * User: ealtun
 * Date: 16.03.2020
 * Time: 16:22
 */
@Mapper
public interface StokKartMapper {
    StokKartMapper INSTANCE = Mappers.getMapper(StokKartMapper.class);

    StokKarti mapToStokKart(StokKartDto stokKartDto);

}
