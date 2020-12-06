package com.aymer.sirketimceptebackend.cariKart.api;

import com.aymer.sirketimceptebackend.cariKart.dto.CariKartDto;
import com.aymer.sirketimceptebackend.cariKart.dto.CariKartSorguKriteri;
import com.aymer.sirketimceptebackend.cariKart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.cariKart.service.CariKartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/cariKart")
@Slf4j
public class CariKartController {

    private CariKartRepository cariKartRepository;
    private CariKartService cariKartService;
    private CariKartMapper cariKartMapper;

    @Autowired
    public CariKartController(CariKartService  cariKartService, CariKartRepository cariKartRepository, CariKartMapper cariKartMapper) {
        this.cariKartService = cariKartService;
        this.cariKartRepository = cariKartRepository;
        this.cariKartMapper = cariKartMapper;
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    AppResponse<Map> sorgula(@Valid @RequestBody CariKartSorguKriteri cariKartSorguKriteri) {
        int pageNum = cariKartSorguKriteri.getLazyLoadEvent().getFirst() / cariKartSorguKriteri.getLazyLoadEvent().getRows();
        int rows = cariKartSorguKriteri.getLazyLoadEvent().getRows();

        Page<CariKart> cariKarts = cariKartService.findCariKartByCriteria(cariKartSorguKriteri, pageNum, rows);
        Map<String, Object> pageObject = new HashMap<String, Object>();
        pageObject.put("resultList", cariKartMapper.carikartToDtoList(cariKarts.getContent()));
        pageObject.put("totalRecords", cariKarts.getTotalElements());
        return new AppResponse<Map>(pageObject);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> stokKartById(@Valid @PathVariable(name = "id") Long cariKartId) {
        Optional<CariKart> cariKart = cariKartRepository.findById(cariKartId);
        CariKartDto cariKartDto = cariKartMapper.carikartToDto(cariKart.get());
        return ResponseEntity.ok(new AppResponse(cariKartDto));
    }

    @GetMapping("/cariKarts")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> allCariKarts() {
        List<CariKart> cariKarts = cariKartRepository.findAll();
        return ResponseEntity.ok(new AppResponse(cariKartMapper.carikartToDtoList(cariKarts)));
    }

}
