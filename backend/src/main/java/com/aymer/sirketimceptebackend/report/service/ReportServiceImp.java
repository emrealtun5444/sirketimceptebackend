package com.aymer.sirketimceptebackend.report.service;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.report.dto.*;
import com.aymer.sirketimceptebackend.report.repository.ReportRepository;
import com.aymer.sirketimceptebackend.siparis.model.SiparisYonu;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private SessionUtils sessionUtils;

    @Transactional(propagation = Propagation.SUPPORTS)
    public PerformansOzetDto preparePerformansOzet(Integer year) {

        Integer donem = DateUtils.getDonem(DateUtils.getToday());

        // ciro dagilimi aliniyor
        CiroDto aylikCiro = reportRepository.amountOfSalesForPeriod(donem, year, EDurum.AKTIF, EOdemeYonu.BORC, sessionUtils.getSelectedCompany());
        CiroDto yillikCiro = reportRepository.amountOfSalesForPeriod(null, year, EDurum.AKTIF, EOdemeYonu.BORC, sessionUtils.getSelectedCompany());

        // tahsilat aliniyor
        TahsilatDto aylikTahsilat = new TahsilatDto(BigDecimal.ZERO);
        TahsilatDto yillikTahsilat = new TahsilatDto(BigDecimal.ZERO);

        // siparis aliniyor
        SiparisDto aylikSiparis = reportRepository.amountOfSiparisForPeriod(donem, year, EDurum.AKTIF, SiparisYonu.ALINAN_SIPARIS, sessionUtils.getSelectedCompany());
        SiparisDto yillikSiparis = reportRepository.amountOfSiparisForPeriod(null, year, EDurum.AKTIF, SiparisYonu.ALINAN_SIPARIS, sessionUtils.getSelectedCompany());

        // hedef aliniyor
        BigDecimal totalHedef = reportRepository.totalHedefTutar(EDurum.AKTIF, sessionUtils.getSelectedCompany(), null);
        BigDecimal aylikHedefTutari = totalHedef.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        HedefDto aylikHedef = HedefDto.builder()
            .hedefTutari(aylikHedefTutari)
            .gerceklesenHedefTutari(aylikCiro.getTutar())
            .gerceklesmeOrani(aylikCiro.getTutar().multiply(BigDecimal.valueOf(100)).divide(aylikHedefTutari, 2, RoundingMode.HALF_UP))
            .build();

        BigDecimal currentDateDif = BigDecimal.valueOf(LocalDate.now().lengthOfYear());
        BigDecimal yillikHedefTutari = totalHedef.multiply(currentDateDif).divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);
        HedefDto yillikHedef = HedefDto.builder()
            .hedefTutari(yillikHedefTutari)
            .gerceklesenHedefTutari(yillikCiro.getTutar())
            .gerceklesmeOrani(yillikCiro.getTutar().multiply(BigDecimal.valueOf(100)).divide(yillikHedefTutari, 2, RoundingMode.HALF_UP))
            .build();

        return PerformansOzetDto.builder()
            .aylikCiro(aylikCiro)
            .yillikCiro(yillikCiro)
            .aylikTahsilat(aylikTahsilat)
            .yillikTahsilat(yillikTahsilat)
            .aylikSiparis(aylikSiparis)
            .yillikSiparis(yillikSiparis)
            .aylikHedef(aylikHedef)
            .yillikHedef(yillikHedef)
            .build();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<HedefDto> donemeGoreCiroDagilimi(User staff, Integer year) {
        BigDecimal totalHedef = reportRepository.totalHedefTutar(EDurum.AKTIF, sessionUtils.getSelectedCompany(), staff);
        BigDecimal aylikHedefTutari = totalHedef.divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        List<HedefDto> hedefDtos = reportRepository.donemCiroDagilim(year, EDurum.AKTIF, EOdemeYonu.BORC, sessionUtils.getSelectedCompany(), staff);
        hedefDtos.stream().forEach(hedefDto -> hedefDto.setHedefTutari(aylikHedefTutari));
        return hedefDtos;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TahsilatDto> donemeGoreTahsilatDagilimi(User staff, Integer year) {
        return Arrays.asList(new TahsilatDto(0, BigDecimal.ZERO));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SiparisDto> donemeGoreSiparisDagilimi(User staff, Integer year) {
        return reportRepository.donemeGoreSiparisDagilimi(year, EDurum.AKTIF, SiparisYonu.ALINAN_SIPARIS, sessionUtils.getSelectedCompany(), staff);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MarkaDagilimDto> donemeGoreMarkaDagilimi(User staff, Integer year) {
        return reportRepository.donemeGoreMarkaDagilimi(year, EDurum.AKTIF, EOdemeYonu.BORC, sessionUtils.getSelectedCompany(), staff);
    }


}
