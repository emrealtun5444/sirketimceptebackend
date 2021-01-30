package com.aymer.sirketimceptebackend.report.model;

import com.aymer.sirketimceptebackend.belge.model.Belge;
import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.KnowsQueryCriteriaHolderClass;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.utils.DateUtils;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/*
User    : ealtun
Date    : 15.01.2021
*/

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONE)
public class AsenkronRaporBilgi extends Auditable<String> implements Serializable {

    @Column
    private String raporAdi;

    @Enumerated(EnumType.STRING)
    @Column
    private RaporTuru raporTuru;

    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.SELECT)
    private User kullanici;

    @Lob
    @Column
    @Basic(fetch = FetchType.LAZY)
    private byte[] sorguKriteri;

    @Lob
    @Column
    @Basic(fetch = FetchType.LAZY)
    private byte[] sessionInfo;

    @Enumerated(EnumType.STRING)
    @Column
    private RaporOlusmaDurumu raporOlusmaDurumu;

    @Column
    private String islemCevap;

    @ManyToOne
    @JoinColumn
    private Sirket sirket;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date raporOlusmaZamani;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Belge belge;


    public void initialize(String raporAdi, SessionUtils sessionInfo, RaporTuru raporTuru) {
        this.raporOlusmaDurumu = RaporOlusmaDurumu.ISLENMEYI_BEKLIYOR;
        this.raporTuru = raporTuru;
        this.raporAdi = raporAdi;
        this.sirket = sessionInfo.getSelectedCompany();
        this.setKullanici(new User(sessionInfo.getUserDetails().getId()));
    }

    public void startProcess() {
        this.raporOlusmaDurumu = RaporOlusmaDurumu.ISLENIYOR;
    }

    public void finishProcess(Belge belge) {
        this.raporOlusmaDurumu = RaporOlusmaDurumu.BASARILI;
        this.raporOlusmaZamani = DateUtils.getNow();
        this.belge = belge;
    }

    public void prepareFaildState(String exp) {
        this.islemCevap = exp.length() >= 4000 ? exp.substring(0, 4000) : exp;
        this.raporOlusmaDurumu = RaporOlusmaDurumu.BASARISIZ;
    }

    public void setSerializedFields(KnowsQueryCriteriaHolderClass sorguKriteriClass, SessionUtils sessionInfo) {
        this.sessionInfo = getSerializedSesionInfo(sessionInfo);
        this.sorguKriteri = getSerializedSorguKriteri(sorguKriteriClass);
    }

    private byte[] getSerializedSesionInfo(SessionUtils sessionInfo) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(sessionInfo);
        } catch (Exception ex) {
            throw new ServiceException(ex.getLocalizedMessage());
        }
        return os.toByteArray();
    }

    private byte[] getSerializedSorguKriteri(KnowsQueryCriteriaHolderClass sorguKriteriClass) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(sorguKriteriClass);
        } catch (Exception ex) {
            throw new ServiceException(ex.getLocalizedMessage());
        }
        return os.toByteArray();
    }

}
