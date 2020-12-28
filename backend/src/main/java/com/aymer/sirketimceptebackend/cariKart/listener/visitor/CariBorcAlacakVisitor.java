package com.aymer.sirketimceptebackend.cariKart.listener.visitor;

import com.aymer.sirketimceptebackend.cariKart.mapper.CariKartMapper;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import com.aymer.sirketimceptebackend.system.user.repositoru.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
        String ozelCode = cariKartViewHolder.getOzelKod() != null ? cariKartViewHolder.getOzelKod().toUpperCase(LocaleContextHolder.getLocale()) : null;
        Optional<User> sorumluPersonel = userRepository.findByCode(ozelCode);
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
