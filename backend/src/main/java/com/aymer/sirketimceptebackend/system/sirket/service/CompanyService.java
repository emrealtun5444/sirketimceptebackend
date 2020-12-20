package com.aymer.sirketimceptebackend.system.sirket.service;


import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.system.sirket.dto.CompanyInput;

import java.util.List;

public interface CompanyService {
    List<CompanyInput> listCompanies();

    CompanyInput addCompany(CompanyInput input);

    CompanyInput updateCompany(Long id, CompanyInput input);

    void deleteCompany(Long id);

    CompanyInput getCompany(Long id);

    List<SelectItem> listOperations();

}
