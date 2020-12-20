package com.aymer.sirketimceptebackend.system.sirket.service;


import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItemMapper;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.system.sirket.dto.CompanyInput;
import com.aymer.sirketimceptebackend.system.sirket.mapper.CompanyMapper;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCompanyService implements CompanyService {
    private final SirketRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public DefaultCompanyService(SirketRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CompanyInput> listCompanies() {
        return companyMapper.toInputs(companyRepository.findAll());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CompanyInput addCompany(CompanyInput input) {
        Sirket company = new Sirket();
        companyMapper.updateFromInput(input, company);
        return companyMapper.toInput(companyRepository.save(company));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CompanyInput updateCompany(Long id, CompanyInput input) {
        Sirket company = companyRepository.findById(id).get();
        companyMapper.updateFromInput(input, company);
        return companyMapper.toInput(companyRepository.save(company));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCompany(Long id) {
        //TODO mark as disabled if used?
        companyRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CompanyInput getCompany(Long id) {
        return companyMapper.toInput(companyRepository.findById(id)
            .orElseThrow(() -> new ServiceException("No company entity found with id: " + id)));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SelectItem> listOperations() {
        return SelectItemMapper.toComboItems(companyRepository.findByOperationIsNullAndDurum(EDurum.AKTIF));
    }
}
