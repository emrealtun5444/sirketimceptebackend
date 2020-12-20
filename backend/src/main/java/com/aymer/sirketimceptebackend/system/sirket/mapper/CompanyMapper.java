package com.aymer.sirketimceptebackend.system.sirket.mapper;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.ReferenceMapper;
import com.aymer.sirketimceptebackend.system.sirket.dto.CompanyInput;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface CompanyMapper {
    CompanyInput toInput(Sirket company);
    @Mappings({
        @Mapping(target = "id", ignore = true)
    })
    void updateFromInput(CompanyInput companyInput, @MappingTarget Sirket company);
    List<CompanyInput> toInputs(List<Sirket> companies);
}
