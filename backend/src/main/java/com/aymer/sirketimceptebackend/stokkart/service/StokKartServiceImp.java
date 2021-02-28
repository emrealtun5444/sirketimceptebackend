package com.aymer.sirketimceptebackend.stokkart.service;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.common.repository.specs.BaseSpesification;
import com.aymer.sirketimceptebackend.common.repository.specs.SearchCriteria;
import com.aymer.sirketimceptebackend.common.repository.specs.SearchOperation;
import com.aymer.sirketimceptebackend.stokkart.dto.StokKartSorguKriteri;
import com.aymer.sirketimceptebackend.stokkart.listener.StokKartViewHolder;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.stokkart.repository.StokKartRepository;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import com.aymer.sirketimceptebackend.tahsilat.model.EParaBirimi;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Value("${link.entegrasyon.url}")
    private String linkEntegrasyonUrl;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StokKart syncStokKart(StokKartViewHolder stokKartDto) {
        StokKart stokKart = null;
        Optional<Sirket> sirket = sirketRepository.findById(stokKartDto.getSirketId());

        // control if there ise stok kart exists
        boolean haveStokKart = stokKartRepository.existsByStokKodu(stokKartDto.getStokKodu());
        if (haveStokKart) {
            stokKart = stokKartRepository.findByStokKodu(stokKartDto.getStokKodu());
            stokKart.updateStokAndPrice(stokKartDto.getAciklama(), stokKartDto.getMiktar(), stokKartDto.getBirimFiyati());
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
        return this.saveStokKart(stokKart);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInvalidateStokKart(List<StokKartViewHolder> stokKartDtoList) {
        // stok koları alınıyor.
        List<String> validStokKartList = stokKartDtoList.stream().map(StokKartViewHolder::getStokKodu).collect(Collectors.toList());
        // diğer sorklar için stok adedleri 0 tl olarak düzenleniyor.
        stokKartRepository.updateStokAdediByStokKodu(validStokKartList, EDurum.PASIF);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StokKart getStokKart(Sirket sirket, String stokKodu) {
        StokKart stokKart = stokKartRepository.findByStokKodu(stokKodu);
        if (stokKart == null) {
            RestTemplate restTemplate = new RestTemplate();
            StokKartViewHolder stokKartViewHolder = restTemplate.getForObject(linkEntegrasyonUrl + "stokKart/" + stokKodu, StokKartViewHolder.class);
            if (stokKartViewHolder == null) {
                stokKartViewHolder = StokKartViewHolder.builder().stokKodu(stokKodu).birimFiyati(BigDecimal.ONE).miktar(0L).aciklama("Dummy").build();
            }
            stokKartViewHolder.setSirketId(sirket.getId());
            return this.syncStokKart(stokKartViewHolder);
        }
        return stokKart;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StokKart saveStokKart(StokKart stokKart) {
        return stokKartRepository.save(stokKart);
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

        spesification.add(new SearchCriteria("durum", EDurum.AKTIF, SearchOperation.EQUAL));

        Pageable pageable = PageRequest.of(page, rows, Sort.by("urunAdi").ascending());
        Page<StokKart> stokKartPage = stokKartRepository.findAll(spesification, pageable);
        return stokKartPage;
    }


}
