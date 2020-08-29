package com.aymer.sirketimceptebackend.controller.fatura.mapper;

import com.aymer.sirketimceptebackend.listener.carikart.viewholder.FaturaDetayViewHolder;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.FaturaViewHolder;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.model.FaturaDetay;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Date;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class FaturaMapper {

    public abstract void updateFatura(FaturaViewHolder faturaViewHolder, @MappingTarget Fatura fatura);

    public abstract void updateFaturaDetay(FaturaDetayViewHolder faturaDetayViewHolder, @MappingTarget FaturaDetay faturaDetay);

}
