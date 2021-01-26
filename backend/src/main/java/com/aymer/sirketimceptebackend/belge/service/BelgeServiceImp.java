package com.aymer.sirketimceptebackend.belge.service;

import com.aymer.sirketimceptebackend.belge.model.Belge;
import com.aymer.sirketimceptebackend.belge.model.EBelgeTipi;
import com.aymer.sirketimceptebackend.belge.repository.BelgeRepository;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class BelgeServiceImp implements BelgeService {

    @Autowired
    private BelgeRepository belgeRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Belge saveBelge(Sirket sirket, String fileName, String minetype, EBelgeTipi belgeTipi, byte[] excelBytes) {
        Belge belge = Belge.builder().build();
        belge.initialize(sirket, fileName, minetype, belgeTipi, excelBytes);
        return belgeRepository.save(belge);
    }


}
