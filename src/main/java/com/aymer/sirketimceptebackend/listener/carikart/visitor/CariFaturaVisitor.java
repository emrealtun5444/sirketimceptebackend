package com.aymer.sirketimceptebackend.listener.carikart.visitor;

import com.aymer.sirketimceptebackend.controller.fatura.mapper.FaturaMapper;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.FaturaDetayViewHolder;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.FaturaViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.model.FaturaDetay;
import com.aymer.sirketimceptebackend.model.StokKart;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
import com.aymer.sirketimceptebackend.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.repository.FaturaDetayRepository;
import com.aymer.sirketimceptebackend.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.repository.StokKartRepository;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import com.aymer.sirketimceptebackend.utils.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import freemarker.template.utility.DateUtil;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
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
        Set<FaturaDetay> faturaDetaySet = createFaturaDetays(faturaViewHolder.getFaturaDetayList(), fatura);
        fatura.setFaturaDetays(faturaDetaySet);
        faturaRepository.save(fatura);
    }

    private Fatura createFatura(CariKart cariKart, FaturaViewHolder faturaViewHolder) {
        JsonNode jsonNodes = JsonUtil.getJsonNode(faturaViewHolder.getFaturaKalemList());
        Fatura fatura = Fatura.builder().durum(EDurum.AKTIF).cariKart(cariKart).sirket(cariKart.getSirket()).faturaKalemInfo(jsonNodes).build();
        faturaMapper.updateFatura(faturaViewHolder,fatura);
        return fatura;
    }

    private Set<FaturaDetay> createFaturaDetays(List<FaturaDetayViewHolder> faturaDetayViewHolders, Fatura fatura) {
        Set<FaturaDetay> faturaDetaySet = new HashSet<>();
        if (!CollectionUtils.isEmpty(faturaDetayViewHolders)) {
            faturaDetayViewHolders.forEach(faturaDetayViewHolder -> {
                StokKart stokKart = stokKartRepository.findByStokKodu(faturaDetayViewHolder.getStokKodu());
                EKdvOrani kdvOrani = getKdvOrani(faturaDetayViewHolder);
                FaturaDetay faturaDetay = FaturaDetay.builder().cariKart(fatura.getCariKart()).fatura(fatura).durum(EDurum.AKTIF).stokKart(stokKart).kdvOrani(kdvOrani).build();
                faturaMapper.updateFaturaDetay(faturaDetayViewHolder,faturaDetay);
                faturaDetaySet.add(faturaDetay);
            });
        }
        return faturaDetaySet;
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
