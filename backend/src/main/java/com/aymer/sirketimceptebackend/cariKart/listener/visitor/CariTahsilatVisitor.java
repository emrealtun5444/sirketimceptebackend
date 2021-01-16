package com.aymer.sirketimceptebackend.cariKart.listener.visitor;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.CariKartViewHolder;
import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.TahsilatViewHolder;
import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.cariKart.repository.CariKartRepository;
import com.aymer.sirketimceptebackend.tahsilat.mapper.TahsilatMapper;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import com.aymer.sirketimceptebackend.tahsilat.repository.TahsilatRepository;
import com.aymer.sirketimceptebackend.tahsilat.service.TahsilatFactory;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CariTahsilatVisitor implements CariKartVisitor {

    @Autowired
    private CariKartRepository cariKartRepository;

    @Autowired
    private TahsilatRepository tahsilatRepository;

    @Autowired
    private TahsilatMapper tahsilatMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void visit(CariKartViewHolder cariKartViewHolder) {
        // cariKart bulunur
        CariKart cariKart = cariKartRepository.findByCariKodu(cariKartViewHolder.getHesapKodu());
        List<FinansalHareket> oldFinansalHareketList = tahsilatRepository.findAllByCariKartAndIslemTarihiGreaterThanEqual(cariKart, DateUtils.firstDayOfYear());

        // siparis kayitlarini sileriz.
        tahsilatRepository.deleteAll(oldFinansalHareketList);
        // siparis üzerinde giziniriz
        List<FinansalHareket> finansalHareketList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cariKartViewHolder.getTahsilatList())) {
            cariKartViewHolder.getTahsilatList().forEach(tahsilatViewHolder -> {
                finansalHareketList.add(saveTahsilat(cariKart, tahsilatViewHolder));
            });
            tahsilatRepository.saveAll(finansalHareketList);
        }
    }

    private FinansalHareket saveTahsilat(CariKart cariKart, TahsilatViewHolder tahsilatViewHolder) {
        FinansalHareket finansalHareket = TahsilatFactory.create(tahsilatViewHolder, cariKart);
        tahsilatMapper.update(tahsilatViewHolder, finansalHareket);
        return finansalHareket;
    }

}
