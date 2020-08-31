package com.aymer.sirketimceptebackend.controller.fatura;

import com.aymer.sirketimceptebackend.controller.carikart.dto.CariKartDto;
import com.aymer.sirketimceptebackend.controller.carikart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaDetayDto;
import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaDto;
import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaKalemDto;
import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaSorguKriteri;
import com.aymer.sirketimceptebackend.controller.fatura.mapper.FaturaMapper;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.model.FaturaDetay;
import com.aymer.sirketimceptebackend.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.repository.FaturaDetayRepository;
import com.aymer.sirketimceptebackend.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.service.FaturaService;
import com.aymer.sirketimceptebackend.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/fatura")
public class FaturaController {

    private FaturaRepository faturaRepository;
    private FaturaDetayRepository faturaDetayRepository;
    private CariKartRepository cariKartRepository;
    private CariKartMapper cariKartMapper;
    private FaturaService faturaService;
    private FaturaMapper faturaMapper;

    @Autowired
    public FaturaController(FaturaRepository faturaRepository, FaturaDetayRepository faturaDetayRepository, CariKartRepository cariKartRepository, CariKartMapper cariKartMapper, FaturaService faturaService, FaturaMapper faturaMapper) {
        this.faturaRepository = faturaRepository;
        this.faturaDetayRepository = faturaDetayRepository;
        this.cariKartRepository = cariKartRepository;
        this.cariKartMapper = cariKartMapper;
        this.faturaService = faturaService;
        this.faturaMapper = faturaMapper;
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    AppResponse<Map> sorgula(@Valid @RequestBody FaturaSorguKriteri faturaSorguKriteri) {
        int pageNum = faturaSorguKriteri.getLazyLoadEvent().getFirst() / faturaSorguKriteri.getLazyLoadEvent().getRows();
        int rows = faturaSorguKriteri.getLazyLoadEvent().getRows();

        Page<Fatura> faturaPage = faturaService.findFaturaByCriteria(faturaSorguKriteri, pageNum, rows);
        Map<String, Object> pageObject = new HashMap<String, Object>();
        pageObject.put("resultList", faturaMapper.faturaToDtoList(faturaPage.getContent()));
        pageObject.put("totalRecords", faturaPage.getTotalElements());
        return new AppResponse<Map>(pageObject);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    AppResponse<Map> faturaById(@Valid @PathVariable(name = "id") Long faturaId) {

        // fatura çekilir
        Optional<Fatura> fatura = faturaRepository.findById(faturaId);
        FaturaDto faturaDto = faturaMapper.faturaToDto(fatura.get());

        // fatura detay çekilir
        Optional<List<FaturaDetay>> faturaDetays = faturaDetayRepository.findAllByFatura(fatura.get());
        List<FaturaDetayDto> faturaDetayDtos = faturaMapper.faturaDetayToDtoList(faturaDetays.get());

        // fatura kalem listesi çekiliyor.
        List<FaturaKalemDto> faturaKalemDtos = JsonUtil.getObjectList(fatura.get().getFaturaKalemInfo(), FaturaKalemDto.class);

        // carikart çekiliyor.
        Optional<CariKart> cariKart = cariKartRepository.findById(fatura.get().getCariKart().getId());
        CariKartDto cariKartDto = cariKartMapper.carikartToDto(cariKart.get());


        Map<String, Object> pageObject = new HashMap<String, Object>();
        pageObject.put("cariKart", cariKartDto);
        pageObject.put("fatura", faturaDto);
        pageObject.put("faturaDetayList", faturaDetayDtos);
        pageObject.put("faturaKalemList", faturaKalemDtos);

        return new AppResponse<Map>(pageObject);
    }

}
