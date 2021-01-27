package com.aymer.sirketimceptebackend.report.repository;

import com.aymer.sirketimceptebackend.report.dto.AsenkronRaporBilgiSorguSonucu;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.RaporOlusmaDurumu;
import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * User: ealtun
 * Date: 15.01.2021
 */
@Repository
public interface AsenkronRaporBilgiRepository extends JpaRepository<AsenkronRaporBilgi, Long> {

    @Query("select v from AsenkronRaporBilgi v where v.raporOlusmaDurumu = :olusmaDurumu and v.sirket = :sirket")
    Page<AsenkronRaporBilgi> getIslenecekAsenkronRaporBilgi(@Param("olusmaDurumu") RaporOlusmaDurumu olusmaDurumu, @Param("sirket") Sirket sirket, Pageable pageable);

    @Query("select count(v) from AsenkronRaporBilgi v where v.id = :id and v.raporOlusmaDurumu = :olusmaDurumu")
    Long countAllByIdAndRaporOlusmaDurumu(@Param("id") Long id, @Param("olusmaDurumu") RaporOlusmaDurumu olusmaDurumu);

    @Query("select new com.aymer.sirketimceptebackend.report.dto.AsenkronRaporBilgiSorguSonucu (v.id, v.raporTuru, v.raporOlusmaDurumu, v.islemCevap, b.fileName, v.raporOlusmaZamani, k) from AsenkronRaporBilgi v left join v.kullanici k left join v.belge b where v.kullanici.id = :kullanici order by v.id desc")
    List<AsenkronRaporBilgiSorguSonucu> getAsenkronRaporBilgiByKullaniciOrderByIdDesc(@Param("kullanici") Long kullanici);

    @Query("select new com.aymer.sirketimceptebackend.report.dto.AsenkronRaporBilgiSorguSonucu (v.id, v.raporTuru, v.raporOlusmaDurumu, v.islemCevap, b.fileName, v.raporOlusmaZamani, k) from AsenkronRaporBilgi v left join v.kullanici k left join v.belge b  where v.kullanici.id = :kullanici and v.raporTuru = :raporTuru order by v.id desc")
    List<AsenkronRaporBilgiSorguSonucu> getAsenkronRaporBilgiByKullaniciAndRaporTuruOrderByIdDesc(@Param("kullanici") Long kullanici, @Param("raporTuru") RaporTuru raporTuru);


    @Query("select v.id from AsenkronRaporBilgi v where v.createdDate <= :raporTarihi and v.sirket = :sirket")
    List<Long> getSilinecekRaporListesi(@Param("raporTarihi") Date raporTarihi, @Param("sirket") Sirket sirket);

    // Deletes the Reports Older Than One Month
    // Deletes the Reports Older Than One Month
    @Modifying
    @Query("update AsenkronRaporBilgi asb set asb.islemCevap= :islemCevap, asb.raporOlusmaDurumu= :raporOlusmaDurumu where asb.id= :id")
    void updateWhenStatusChanged(@Param("id") Long id, @Param("islemCevap") String islemCevap, @Param("raporOlusmaDurumu") RaporOlusmaDurumu raporOlusmaDurumu);

}
