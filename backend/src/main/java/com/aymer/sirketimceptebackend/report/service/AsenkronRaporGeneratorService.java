package com.aymer.sirketimceptebackend.report.service;


import com.aymer.sirketimceptebackend.belge.model.Belge;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.KnowsQueryCriteriaHolderClass;
import com.aymer.sirketimceptebackend.report.dto.AsenkronRaporBilgiSorguSonucu;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.utils.SessionUtils;

import java.util.List;

/**
 * User: matasci
 * Date: 15.01.2021
 * Time: 08:36
 */
public interface AsenkronRaporGeneratorService {
    Boolean canStart(AsenkronRaporBilgi raporBilgi);

    AsenkronRaporBilgi start(AsenkronRaporBilgi raporBilgi);

    AsenkronRaporBilgi finish(AsenkronRaporBilgi raporBilgi, byte[] excelBytes);

    void fail(Long raporBilgiId, String exp);

    AsenkronRaporBilgi create(String raporAdi, RaporTuru raporTuru, KnowsQueryCriteriaHolderClass sorguKriteri, SessionUtils sessionInfo);

    List<AsenkronRaporBilgiSorguSonucu> getAsenkronRaporBilgiList(SessionUtils sessionInfo, RaporTuru raporTuru);

    Long findBelgeById(Long id);
}
