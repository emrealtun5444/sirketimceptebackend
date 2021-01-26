package com.aymer.sirketimceptebackend.report.dto;

import com.aymer.sirketimceptebackend.report.model.RaporOlusmaDurumu;
import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.system.user.model.User;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.Date;

/**
 * User: matasci
 * Date: 18.01.2021
 * Time: 16:10
 */
@Getter
@Setter
public class AsenkronRaporBilgiSorguSonucu implements Serializable {

    private Long id;
    private RaporTuru raporTuru;
    private RaporOlusmaDurumu raporOlusmaDurumu;
    private String islemCevap;
    private String documentName;
    private Date raporOlusmaTarihi;
    private String kullanici;
    private String vekil;

    public AsenkronRaporBilgiSorguSonucu(Long id, RaporTuru raporTuru, RaporOlusmaDurumu raporOlusmaDurumu, String islemCevap, String documentName, Date raporOlusmaTarihi, User kullanici) {
        this.id = id;
        this.raporTuru = raporTuru;
        this.raporOlusmaDurumu = raporOlusmaDurumu;
        this.islemCevap = islemCevap;
        this.documentName = documentName;
        this.raporOlusmaTarihi = raporOlusmaTarihi;
        this.kullanici = kullanici.getAciklama();
    }
}
