package com.aymer.sirketimceptebackend.fatura.api;

import com.aymer.sirketimceptebackend.cariKart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.fatura.dto.FaturaKalemDto;
import com.aymer.sirketimceptebackend.fatura.dto.FaturaSorguKriteri;
import com.aymer.sirketimceptebackend.fatura.mapper.FaturaMapper;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaDetayRepository;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.fatura.service.FaturaService;
import com.aymer.sirketimceptebackend.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/fatura")
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
        Map<String, Object> pageObject = new HashMap<String, Object>();

        // fatura çekilir
        Optional<Fatura> fatura = faturaRepository.findById(faturaId);
        if (fatura.isPresent()) {
            pageObject.put("fatura", faturaMapper.faturaToDto(fatura.get()));
        }

        // fatura detay çekilir
        Optional<List<FaturaDetay>> faturaDetays = faturaDetayRepository.findAllByFatura(fatura.get());
        if (faturaDetays.isPresent()) {
            pageObject.put("faturaDetayList", faturaMapper.faturaDetayToDtoList(faturaDetays.get()));
        }

        // fatura kalem listesi çekiliyor.
        List<FaturaKalemDto> faturaKalemDtos = JsonUtil.getObjectList(fatura.get().getFaturaKalemInfo(), FaturaKalemDto.class);
        if (!CollectionUtils.isEmpty(faturaKalemDtos)) {
            faturaKalemDtos.sort(Comparator.comparingInt(FaturaKalemDto::getSatirNo));
            pageObject.put("faturaKalemList", faturaKalemDtos);
        }

        // carikart çekiliyor.
        Optional<CariKart> cariKart = cariKartRepository.findById(fatura.get().getCariKart().getId());
       if (cariKart.isPresent()) {
           pageObject.put("cariKart", cariKartMapper.carikartToDto(cariKart.get()));
       }
        return new AppResponse<Map>(pageObject);
    }

}
