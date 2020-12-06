package com.aymer.sirketimceptebackend.stokkart.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.stokkart.dto.StokKartDto;
import com.aymer.sirketimceptebackend.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.stokkart.mapper.StokKartMapper;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.stokkart.repository.StokKartRepository;
import com.aymer.sirketimceptebackend.stokkart.service.StokKartService;
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
@RequestMapping(path = BaseController.BASE_PATH + "/stokKart")
public class StokKartController {

    private StokKartRepository stokKartRepository;
    private StokKartService stokKartService;
    private StokKartMapper stokKartMapper;

    @Autowired
    public StokKartController(StokKartService stokKartService, StokKartRepository stokKartRepository, StokKartMapper stokKartMapper) {
        this.stokKartRepository = stokKartRepository;
        this.stokKartMapper = stokKartMapper;
        this.stokKartService = stokKartService;
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    AppResponse<Map> sorgula(@Valid @RequestBody StokKartSorguKriteri stokKartSorguKriteri) {
        int pageNum = stokKartSorguKriteri.getLazyLoadEvent().getFirst() / stokKartSorguKriteri.getLazyLoadEvent().getRows();
        int rows = stokKartSorguKriteri.getLazyLoadEvent().getRows();

        Page<StokKart> stokKarts = stokKartService.findStokKartByCriteria(stokKartSorguKriteri, pageNum, rows);
        Map<String, Object> pageObject = new HashMap<String, Object>();
        pageObject.put("resultList", stokKartMapper.stokkartToDtoList(stokKarts.getContent()));
        pageObject.put("totalRecords", stokKarts.getTotalElements());
        return new AppResponse<Map>(pageObject);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> stokKartById(@Valid @PathVariable(name = "id") Long stokKartId) {
        Optional<StokKart> stokKart = stokKartRepository.findById(stokKartId);
        StokKartDto stokKartDto = stokKartMapper.stokkartToDto(stokKart.get());
        return ResponseEntity.ok(new AppResponse(stokKartDto));
    }

    @GetMapping("/stokKarts")
    public ResponseEntity<?> allStokKarts() {
        List<StokKart> stokKarts = stokKartRepository.findAll();
        return ResponseEntity.ok(new AppResponse(stokKartMapper.stokkartToDtoList(stokKarts)));
    }

}
