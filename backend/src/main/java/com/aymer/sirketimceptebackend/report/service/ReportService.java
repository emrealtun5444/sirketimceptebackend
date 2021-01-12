package com.aymer.sirketimceptebackend.report.service;


import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.report.dto.*;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface ReportService {

    PerformansOzetDto preparePerformansOzet(Integer year);

    List<HedefDto> donemeGoreCiroDagilimi(User staff, Integer year, CariKart cariKart);

    List<TahsilatDto> donemeGoreTahsilatDagilimi(User staff, Integer year, CariKart cariKart);

    List<SiparisDto> donemeGoreSiparisDagilimi(User staff, Integer year, CariKart cariKart);

    List<MarkaDagilimDto> donemeGoreMarkaDagilimi(User staff, Integer year, CariKart cariKart);

    List<HedefCariDto> donemeGoreHedefCariDagilimi(User staff, Integer year, Sirket sirket);
}
