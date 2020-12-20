package com.aymer.sirketimceptebackend.system.sirket.api;


import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.system.sirket.dto.CompanyInput;
import com.aymer.sirketimceptebackend.system.sirket.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/companies")
@Slf4j
public class CompanyController {
    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('COMPANY_MENU')")
    AppResponse<List<CompanyInput>> list() {
        return new AppResponse<>(service.listCompanies());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('COMPANY_MENU')")
    AppResponse<CompanyInput> add(@RequestBody CompanyInput input) {
        return new AppResponse<>(service.addCompany(input));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('COMPANY_MENU')")
    AppResponse<CompanyInput> update(@PathVariable Long id, @RequestBody CompanyInput input) {
        return new AppResponse<>(service.updateCompany(id, input));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('COMPANY_MENU')")
    AppResponse<CompanyInput> get(@PathVariable Long id) {
        return new AppResponse<>(service.getCompany(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('COMPANY_MENU')")
    AppResponse delete(@PathVariable Long id) {
        service.deleteCompany(id);
        return new AppResponse();
    }

    @GetMapping("/operations")
    @PreAuthorize("hasAuthority('COMPANY_MENU')")
    AppResponse<List<SelectItem>> getOperations() {
        return new AppResponse<>(service.listOperations());
    }

}
