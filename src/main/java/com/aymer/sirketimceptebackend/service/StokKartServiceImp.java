package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.model.StokKarti;
import com.aymer.sirketimceptebackend.model.common.Sirket;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EKdvOrani;
import com.aymer.sirketimceptebackend.model.enums.EParaBirimi;
import com.aymer.sirketimceptebackend.model.viewholder.StokKartDto;
import com.aymer.sirketimceptebackend.repository.SirketRepository;
import com.aymer.sirketimceptebackend.repository.StokKartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class StokKartServiceImp implements StokKartService {

    @Autowired
    private StokKartRepository stokKartRepository;

    @Autowired
    private SirketRepository sirketRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void syncStokKart(StokKartDto stokKartDto) {
        StokKarti stokKarti = null;
        Optional<Sirket> sirket = sirketRepository.findById(stokKartDto.getSirketId());

        // control if there ise stok kart exists
        boolean haveStokKart = stokKartRepository.existsByStokKodu(stokKartDto.getStokKodu());
        if (haveStokKart) {
            stokKarti = stokKartRepository.findByStokKodu(stokKartDto.getStokKodu());
            stokKarti.updateStokAndPrice(stokKartDto.getMiktar(),stokKartDto.getBirimFiyati());
        } else {
            stokKarti = StokKarti.builder()
                    .stokKodu(stokKartDto.getStokKodu())
                    .urunAdi(stokKartDto.getAciklama())
                    .urunFiyat(stokKartDto.getBirimFiyati())
                    .stokAdedi(stokKartDto.getMiktar())
                    .durum(EDurum.AKTIF)
                    .kdvOrani(EKdvOrani.KDV_ORANI_18)
                    .paraBirimi(EParaBirimi.TRY)
                    .sirket(sirket.get())
                    .build();
        }
        stokKartRepository.save(stokKarti);
    }
}
