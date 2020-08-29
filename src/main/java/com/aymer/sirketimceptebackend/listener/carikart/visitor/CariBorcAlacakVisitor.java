package com.aymer.sirketimceptebackend.listener.carikart.visitor;

import com.aymer.sirketimceptebackend.controller.carikart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.listener.carikart.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.repository.SirketRepository;
import com.aymer.sirketimceptebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CariBorcAlacakVisitor implements CariKartVisitor {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private SirketRepository sirketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CariKartMapper cariKartMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void visit(CariKartViewHolder cariKartViewHolder) {

        Optional<Sirket> sirket = sirketRepository.findById(cariKartViewHolder.getSirketId());

        CariKart cariKart = null;
        boolean cariKartExists = cariKartRepository.existsByCariKodu(cariKartViewHolder.getHesapKodu());
        if (cariKartExists) {
            cariKart = cariKartRepository.findByCariKodu(cariKartViewHolder.getHesapKodu());
        } else {
            cariKart = CariKart.builder().durum(EDurum.AKTIF).sirket(sirket.get()).build();
        }
        // sorumlu personel bulunur.
        Optional<User> sorumluPersonel = userRepository.findByUsername(cariKartViewHolder.getOzelKod());
        if (sorumluPersonel.isPresent()) {
            cariKart.setSorumluPersonel(sorumluPersonel.get());
        } else {
            cariKart.setSorumluPersonel(null);
        }

        cariKartMapper.updateCariKart(cariKartViewHolder, cariKart);

        // değişiklikler kaydediliyor
        cariKartRepository.save(cariKart);


    }


}
