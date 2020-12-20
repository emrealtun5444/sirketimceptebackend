package com.aymer.sirketimceptebackend.cariKart.listener.visitor;

import com.aymer.sirketimceptebackend.fatura.mapper.FaturaMapper;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.FaturaDetayViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.FaturaViewHolder;
import com.aymer.sirketimceptebackend.stokkart.listener.StokKartViewHolder;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.stokkart.service.StokKartService;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.tahsilat.model.EKdvOrani;
import com.aymer.sirketimceptebackend.tahsilat.model.EParaBirimi;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaDetayRepository;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.stokkart.repository.StokKartRepository;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import com.aymer.sirketimceptebackend.utils.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CariFaturaVisitor implements CariKartVisitor {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private FaturaDetayRepository faturaDetayRepository;

    @Autowired
    private StokKartRepository stokKartRepository;

    @Autowired
    private FaturaMapper faturaMapper;

    @Value("${link.entegrasyon.url}")
    private String linkEntegrasyonUrl;

    @Autowired
    private StokKartService stokKartService;



    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void visit(CariKartViewHolder cariKartViewHolder) {
        if (CollectionUtils.isEmpty(cariKartViewHolder.getFaturaList())) return;

        // cariKart bulunur
        CariKart cariKart = cariKartRepository.findByCariKodu(cariKartViewHolder.getHesapKodu());
        List<Fatura> faturaList = faturaRepository.findAllByCariKartAndFaturaTarihiGreaterThanEqual(cariKart, DateUtils.firstDayOfYear());
        Map<String, Fatura> existingFaturaMap = faturaList.stream().collect(Collectors.toMap(Fatura::getFaturaNo , fatura -> fatura));

        // faturalar üzerinde giziniriz
        cariKartViewHolder.getFaturaList().forEach(faturaViewHolder -> {
            if (!existingFaturaMap.containsKey(faturaViewHolder.getFaturaNo())) {
                saveFatura(cariKart, faturaViewHolder);
            } else {
                Fatura fatura = existingFaturaMap.get(faturaViewHolder.getFaturaNo());
                // faturada değişiklik varmı diye bakarız.
                if (!faturaAyniMi(faturaViewHolder,fatura)) {
                    // eski fatura silinir.
                    faturaRepository.delete(fatura);
                    // fatura tekrar kaydedilir.
                    saveFatura(cariKart, faturaViewHolder);
                }
            }
        });
    }

    private boolean faturaAyniMi(FaturaViewHolder faturaViewHolder , Fatura fatura) {
        return faturaViewHolder.getFaturaTarihi().equals(fatura.getFaturaTarihi()) &&
            faturaViewHolder.getIskonto().equals(fatura.getIskonto()) &&
            faturaViewHolder.getKdvTutari().equals(fatura.getKdvTutari()) &&
            faturaViewHolder.getMalBedeli().equals(fatura.getMalBedeli()) &&
            faturaViewHolder.getNetToplam().equals(fatura.getNetToplam()) &&
            faturaViewHolder.getToplamTutar().equals(fatura.getToplamTutar()) &&
            faturaViewHolder.getFaturaYonu().equals(fatura.getFaturaYonu());
    }

    private void saveFatura(CariKart cariKart, FaturaViewHolder faturaViewHolder) {
        Fatura fatura = createFatura(cariKart, faturaViewHolder);
        List<FaturaDetay> faturaDetaySet = createFaturaDetays(faturaViewHolder.getFaturaDetayList(), fatura);
        fatura.setFaturaDetays(faturaDetaySet);
        faturaRepository.save(fatura);
    }

    private Fatura createFatura(CariKart cariKart, FaturaViewHolder faturaViewHolder) {
        JsonNode jsonNodes = JsonUtil.getJsonNode(faturaViewHolder.getFaturaKalemList());
        Fatura fatura = Fatura.builder().durum(EDurum.AKTIF).cariKart(cariKart).sirket(cariKart.getSirket()).faturaKalemInfo(jsonNodes).build();
        faturaMapper.updateFatura(faturaViewHolder,fatura);
        return fatura;
    }

    private List<FaturaDetay> createFaturaDetays(List<FaturaDetayViewHolder> faturaDetayViewHolders, Fatura fatura) {
        List<FaturaDetay> faturaDetaySet = new ArrayList<>();
        if (!CollectionUtils.isEmpty(faturaDetayViewHolders)) {
            faturaDetayViewHolders.forEach(faturaDetayViewHolder -> {
                StokKart stokKart = getStokKart(fatura.getSirket(), faturaDetayViewHolder.getStokKodu());
                EKdvOrani kdvOrani = getKdvOrani(faturaDetayViewHolder);
                FaturaDetay faturaDetay = FaturaDetay.builder().cariKart(fatura.getCariKart()).fatura(fatura).durum(EDurum.AKTIF).stokKart(stokKart).kdvOrani(kdvOrani).build();
                faturaMapper.updateFaturaDetay(faturaDetayViewHolder,faturaDetay);
                faturaDetaySet.add(faturaDetay);
            });
        }
        return faturaDetaySet;
    }

    private StokKart getStokKart(Sirket company, String stokKodu) {
        StokKart stokKart = stokKartRepository.findByStokKodu(stokKodu);
        if (stokKart == null) {
            RestTemplate restTemplate = new RestTemplate();
            StokKartViewHolder stokKartViewHolder = restTemplate.getForObject(linkEntegrasyonUrl+ "stokKart/" + stokKodu, StokKartViewHolder.class);
            if (stokKartViewHolder != null) {
                stokKart = StokKart.builder()
                    .stokKodu(stokKartViewHolder.getStokKodu())
                    .urunAdi(stokKartViewHolder.getAciklama())
                    .urunFiyat(stokKartViewHolder.getBirimFiyati())
                    .stokAdedi(stokKartViewHolder.getMiktar())
                    .durum(EDurum.AKTIF)
                    .kdvOrani(EKdvOrani.KDV_ORANI_18)
                    .paraBirimi(EParaBirimi.TRY)
                    .sirket(company)
                    .build();

                stokKart = stokKartService.saveStokKart(stokKart);
            }
        }
        return stokKart;
    }

    private EKdvOrani getKdvOrani(FaturaDetayViewHolder faturaDetayViewHolder) {
        EKdvOrani kdvOrani = EKdvOrani.KDV_ORANI_18;
        try {
            BigDecimal kdvHaricTutar = faturaDetayViewHolder.getTutar().subtract(faturaDetayViewHolder.getIskonto());
            BigDecimal kdvTutari = faturaDetayViewHolder.getKdvTutari();
            if (!kdvTutari.divide(kdvHaricTutar).multiply(BigDecimal.valueOf(100)).equals(BigDecimal.valueOf(18))) {
                kdvOrani = EKdvOrani.KDV_ORANI_8;
            }
        } catch (Exception e) {
        }
        return kdvOrani;
    }


}
