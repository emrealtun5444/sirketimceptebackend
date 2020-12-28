package com.aymer.sirketimceptebackend.dashboard.mapper;

import com.aymer.sirketimceptebackend.dashboard.dto.SorumluPersonelCiroDto;
import com.aymer.sirketimceptebackend.fatura.dto.SorumluPersonelCiro;
import com.aymer.sirketimceptebackend.siparis.dto.SiparisDto;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class DashboardMapper {

    @Mappings({
            @Mapping(target = "nameSurname", source = "user.aciklama")
    })
    public abstract SorumluPersonelCiroDto toDto(SorumluPersonelCiro sorumluPersonelCiro);

    public abstract List<SorumluPersonelCiroDto> toDtoList(List<SorumluPersonelCiro> sorumluPersonelCiroList);
}
