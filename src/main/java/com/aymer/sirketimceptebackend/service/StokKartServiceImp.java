package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.controller.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.listener.stokkart.StokKartViewHolder;
import com.aymer.sirketimceptebackend.model.StokKart;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
import com.aymer.sirketimceptebackend.model.enums.EParaBirimi;
import com.aymer.sirketimceptebackend.repository.SirketRepository;
import com.aymer.sirketimceptebackend.repository.StokKartRepository;
import com.aymer.sirketimceptebackend.repository.specs.BaseSpesification;
import com.aymer.sirketimceptebackend.repository.specs.SearchCriteria;
import com.aymer.sirketimceptebackend.repository.specs.SearchOperation;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class StokKartServiceImp implements StokKartService {

    @Autowired
    private StokKartRepository stokKartRepository;

    @Autowired
    private SirketRepository sirketRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void syncStokKart(StokKartViewHolder stokKartDto) {
        StokKart stokKart = null;
        Optional<Sirket> sirket = sirketRepository.findById(stokKartDto.getSirketId());

        // control if there ise stok kart exists
        boolean haveStokKart = stokKartRepository.existsByStokKodu(stokKartDto.getStokKodu());
        if (haveStokKart) {
            stokKart = stokKartRepository.findByStokKodu(stokKartDto.getStokKodu());
            stokKart.updateStokAndPrice(stokKartDto.getMiktar(),stokKartDto.getBirimFiyati());
        } else {
            stokKart = StokKart.builder()
                    .stokKodu(stokKartDto.getStokKodu())
                    .urunAdi(stokKartDto.getAciklama())
                    .urunFiyat(stokKartDto.getBirimFiyati())
                    .stokAdedi(stokKartDto.getMiktar())
                    .durum(EDurum.AKTIF)
                    .kdvOrani(EKdvOrani.KDV_ORANI_18)
                    .paraBirimi(EParaBirimi.TRY)
                    .sirket(sirket.get())
                    .build();
        }
        stokKartRepository.save(stokKart);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<StokKart> findStokKartByCriteria(StokKartSorguKriteri stokKartSorguKriteri, int page, int rows) {

        BaseSpesification spesification = new BaseSpesification<StokKart>();

        if (StringUtils.isNotEmpty(stokKartSorguKriteri.getStokKodu())) {
            spesification.add(new SearchCriteria("stokKodu", stokKartSorguKriteri.getStokKodu(), SearchOperation.MATCH));
        }

        if (StringUtils.isNotEmpty(stokKartSorguKriteri.getUrunAdi())) {
            spesification.add(new SearchCriteria("urunAdi", stokKartSorguKriteri.getUrunAdi(), SearchOperation.MATCH));
        }

        if (stokKartSorguKriteri.getStokAdedi() != null) {
            spesification.add(new SearchCriteria("stokAdedi", stokKartSorguKriteri.getStokAdedi(), SearchOperation.EQUAL));
        }

        Pageable pageable = PageRequest.of(page, rows, Sort.by("urunAdi").ascending());
        Page<StokKart> stokKartPage = stokKartRepository.findAll(spesification, pageable);
        return stokKartPage;
    }


}
