package com.aymer.sirketimceptebackend.tahsilat.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.tahsilat.dto.TahsilatSorguKriteri;
import com.aymer.sirketimceptebackend.tahsilat.mapper.TahsilatMapper;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import com.aymer.sirketimceptebackend.tahsilat.service.TahsilatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/tahsilat")
public class TahsilatController {

    private final TahsilatService tahsilatService;
    private final TahsilatMapper tahsilatMapper;

    @Autowired
    public TahsilatController(TahsilatService tahsilatService, TahsilatMapper tahsilatMapper) {
        this.tahsilatService = tahsilatService;
        this.tahsilatMapper = tahsilatMapper;
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasAuthority('SIPARIS_MENU')")
    AppResponse<Map> sorgula(@Valid @RequestBody TahsilatSorguKriteri sorguKriteri) {
        int pageNum = sorguKriteri.getLazyLoadEvent().getFirst() / sorguKriteri.getLazyLoadEvent().getRows();
        int rows = sorguKriteri.getLazyLoadEvent().getRows();

        Page<FinansalHareket> finansalHareketPage = tahsilatService.findByCriteria(sorguKriteri, pageNum, rows);
        Map<String, Object> pageObject = new HashMap<String, Object>();
        pageObject.put("resultList", tahsilatMapper.toDtoList(finansalHareketPage.getContent()));
        pageObject.put("totalRecords", finansalHareketPage.getTotalElements());
        return new AppResponse<Map>(pageObject);
    }

}
