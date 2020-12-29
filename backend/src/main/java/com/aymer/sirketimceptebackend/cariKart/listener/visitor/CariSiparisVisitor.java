package com.aymer.sirketimceptebackend.cariKart.listener.visitor;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.SiparisViewHolder;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.repository.FaturaRepository;
import com.aymer.sirketimceptebackend.siparis.mapper.SiparisMapper;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.siparis.repository.SiparisRepository;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.stokkart.repository.StokKartRepository;
import com.aymer.sirketimceptebackend.stokkart.service.StokKartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CariSiparisVisitor implements CariKartVisitor {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private SiparisRepository siparisRepository;

    @Autowired
    private StokKartRepository stokKartRepository;

    @Autowired
    private SiparisMapper siparisMapper;

    @Autowired
    private StokKartService stokKartService;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void visit(CariKartViewHolder cariKartViewHolder) {
        // cariKart bulunur
        CariKart cariKart = cariKartRepository.findByCariKodu(cariKartViewHolder.getHesapKodu());
        final List<Siparis> oldSiparisList = siparisRepository.findAllByCariKart(cariKart);
        // siparis kayitlarini sileriz.
        siparisRepository.deleteAll(oldSiparisList);
        // siparis üzerinde giziniriz
        List<Siparis> siparisList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cariKartViewHolder.getSiparisList())) {
            cariKartViewHolder.getSiparisList().forEach(siparisViewHolder -> {
                siparisList.add(saveSiparis(cariKart, siparisViewHolder));
            });
            siparisRepository.saveAll(siparisList);
        }
    }

    private Siparis saveSiparis(CariKart cariKart, SiparisViewHolder siparisViewHolder) {
        StokKart stokKart = stokKartService.getStokKart(cariKart.getSirket(), siparisViewHolder.getMalKodu());
        Siparis siparis = Siparis.builder()
            .durum(EDurum.AKTIF)
            .cariKart(cariKart)
            .sirket(cariKart.getSirket())
            .stokKart(stokKart)
            .build();
        siparisMapper.update(siparisViewHolder, siparis);
        return siparis;
    }

}
