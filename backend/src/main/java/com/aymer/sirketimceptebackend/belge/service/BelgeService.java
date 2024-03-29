package com.aymer.sirketimceptebackend.belge.service;


import com.aymer.sirketimceptebackend.belge.model.Belge;
import com.aymer.sirketimceptebackend.belge.model.Documentable;
import com.aymer.sirketimceptebackend.belge.model.EBelgeTipi;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;

import javax.swing.text.Document;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface BelgeService {

    Belge saveBelge(Sirket sirket, String fileName, String minetype, EBelgeTipi belgeTipi, byte[] excelBytes);

    Belge findBelgeById(Long id);

    Documentable findBelgeById(String documentClassName, Long id) throws ClassNotFoundException;
}
