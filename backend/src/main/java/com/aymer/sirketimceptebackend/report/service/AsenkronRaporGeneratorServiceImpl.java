package com.aymer.sirketimceptebackend.report.service;

import com.aymer.sirketimceptebackend.belge.model.Belge;
import com.aymer.sirketimceptebackend.belge.model.EBelgeTipi;
import com.aymer.sirketimceptebackend.belge.service.BelgeService;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.KnowsQueryCriteriaHolderClass;
import com.aymer.sirketimceptebackend.report.dto.AsenkronRaporBilgiSorguSonucu;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.RaporOlusmaDurumu;
import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
User    : ealtun
Date    : 15.01.2021

*/
@Service
public class AsenkronRaporGeneratorServiceImpl implements AsenkronRaporGeneratorService {

    @Autowired
    private AsenkronRaporBilgiRepository asenkronRaporBilgiRepository;

    @Autowired
    private BelgeService belgeService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean canStart(AsenkronRaporBilgi raporBilgi) {
        return asenkronRaporBilgiRepository.countAllByIdAndRaporOlusmaDurumu(raporBilgi.getId(), RaporOlusmaDurumu.ISLENMEYI_BEKLIYOR) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AsenkronRaporBilgi start(AsenkronRaporBilgi raporBilgi) {
        raporBilgi.startProcess();
        return asenkronRaporBilgiRepository.save(raporBilgi);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AsenkronRaporBilgi finish(AsenkronRaporBilgi raporBilgi, byte[] excelBytes) {
        String reportName = raporBilgi.getRaporAdi();
        String minetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        Belge belge = belgeService.saveBelge(raporBilgi.getSirket(), reportName, minetype, EBelgeTipi.RAPOR, excelBytes);
        raporBilgi.finishProcess(belge);
        return asenkronRaporBilgiRepository.save(raporBilgi);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void fail(Long raporBilgiId, String exp) {
        asenkronRaporBilgiRepository.updateWhenStatusChanged(raporBilgiId, exp.length() >= 4000 ? exp.substring(0, 4000) : exp, RaporOlusmaDurumu.BASARISIZ);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AsenkronRaporBilgi create(String raporAdi, RaporTuru raporTuru, KnowsQueryCriteriaHolderClass sorguKriteriClass, SessionUtils sessionInfo) {
        AsenkronRaporBilgi asenkronRaporBilgi = new AsenkronRaporBilgi();
        asenkronRaporBilgi.initialize(raporAdi, sessionInfo, raporTuru);
        asenkronRaporBilgi.setSerializedFields(sorguKriteriClass, sessionInfo);
        return asenkronRaporBilgiRepository.save(asenkronRaporBilgi);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<AsenkronRaporBilgiSorguSonucu> getAsenkronRaporBilgiList(SessionUtils sessionInfo, RaporTuru raporTuru) {
        if (raporTuru == RaporTuru.BOS) {
            return asenkronRaporBilgiRepository.getAsenkronRaporBilgiByKullaniciOrderByIdDesc(sessionInfo.getUserDetails().getId());
        } else {
            return asenkronRaporBilgiRepository.getAsenkronRaporBilgiByKullaniciAndRaporTuruOrderByIdDesc(sessionInfo.getUserDetails().getId(), raporTuru);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Long findBelgeById(Long id) {
        return asenkronRaporBilgiRepository.findBelgeById(id);
    }


}
