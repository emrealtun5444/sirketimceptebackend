package com.aymer.sirketimceptebackend.siparis.api;

import com.aymer.sirketimceptebackend.cariKart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.siparis.dto.SiparisSorguKriteri;
import com.aymer.sirketimceptebackend.siparis.mapper.SiparisMapper;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisRepository;
import com.aymer.sirketimceptebackend.siparis.service.SiparisService;
import com.aymer.sirketimceptebackend.utils.BigDecimalTr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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
@RequestMapping(path = BaseController.BASE_PATH + "/siparis")
public class SiparisController {

    private final CariKartRepository cariKartRepository;
    private final CariKartMapper cariKartMapper;
    private final SiparisRepository siparisRepository;
    private final SiparisService siparisService;
    private final SiparisMapper siparisMapper;
    private final FaturaRepository faturaRepository;

    @Autowired
    public SiparisController(CariKartRepository cariKartRepository, CariKartMapper cariKartMapper, SiparisRepository siparisRepository, SiparisService siparisService, SiparisMapper siparisMapper, FaturaRepository faturaRepository) {
        this.cariKartRepository = cariKartRepository;
        this.cariKartMapper = cariKartMapper;
        this.siparisRepository = siparisRepository;
        this.siparisService = siparisService;
        this.siparisMapper = siparisMapper;
        this.faturaRepository = faturaRepository;
    }

    @PostMapping("/sorgula")
    @PreAuthorize("hasAuthority('SIPARIS_MENU')")
    AppResponse<Map> sorgula(@Valid @RequestBody SiparisSorguKriteri sorguKriteri) {
        int pageNum = sorguKriteri.getLazyLoadEvent().getFirst() / sorguKriteri.getLazyLoadEvent().getRows();
        int rows = sorguKriteri.getLazyLoadEvent().getRows();

        Page<Siparis> siparisPage = siparisService.findByCriteria(sorguKriteri, pageNum, rows);
        Map<String, Object> pageObject = new HashMap<String, Object>();
        pageObject.put("resultList", siparisMapper.toDtoList(siparisPage.getContent()));
        pageObject.put("totalRecords", siparisPage.getTotalElements());
        return new AppResponse<Map>(pageObject);
    }

    @GetMapping("/{id}/fatura")
    @PreAuthorize("hasAuthority('FATURA_MENU')")
    AppResponse<Long> faturaById(@Valid @PathVariable(name = "id") Long id) {
        Siparis siparis = siparisRepository.findById(id).get();
        if (siparis.getFaturaNo() == null) {
            throw new ServiceException("error.siparis.fatura.not.found");
        }
        Fatura fatura = faturaRepository.getByFaturaNo(siparis.getFaturaNo());
        if (fatura == null) {
            throw new ServiceException("error.siparis.fatura.not.found");
        }
        return new AppResponse<>(fatura.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SIPARIS_MENU')")
    AppResponse<Map> siparisByNo(@Valid @PathVariable(name = "id") Long id) {
        Map<String, Object> pageObject = new HashMap<String, Object>();
        // siparis list çekilir
        Siparis siparis = siparisRepository.findById(id).get();
        List<Siparis> siparisList = siparisRepository.findAllBySiparisNo(siparis.getSiparisNo());
        pageObject.put("siparisList", siparisMapper.toDtoList(siparisList));
        pageObject.put("siparis", siparisMapper.toDto(siparis));

        // carikart çekiliyor.
        Optional<CariKart> cariKart = cariKartRepository.findById(siparisList.get(0).getCariKart().getId());
        if (cariKart.isPresent()) {
            pageObject.put("cariKart", cariKartMapper.carikartToDto(cariKart.get()));
        }
        return new AppResponse<Map>(pageObject);
    }

}
