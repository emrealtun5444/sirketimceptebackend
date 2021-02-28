package com.aymer.sirketimceptebackend.stokkart.repository;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.report.dto.Stok;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface StokKartRepository extends JpaRepository<StokKart, Long>, JpaSpecificationExecutor<StokKart> {

    Boolean existsByStokKodu(String stokKodu);

    StokKart findByStokKodu(String stokKodu);

    @Query("select new com.aymer.sirketimceptebackend.report.dto.Stok(" +
            "COALESCE(m.aciklama, 'Diger'), stk.stokKodu, stk.urunAdi, stk.stokAdedi, (COALESCE(stk.stokAdedi,0) * COALESCE(stk.urunFiyat,0))" +
            ") " +
            "from StokKart stk " +
            "left join stk.marka m " +
            "where stk.stokAdedi > :stokAdedi " +
            "and stk.durum = :durum " +
            "order by (COALESCE(stk.stokAdedi,0) * COALESCE(stk.urunFiyat,0)) desc")
    List<Stok> findAllByStokAdediGreaterThan(@Param("stokAdedi") Long stokAdedi, @Param("durum") EDurum durum);


    @Modifying
    @Query("update StokKart sk set sk.stokAdedi = 0, sk.durum = :durum where sk.stokKodu not in (:stokKodu)")
    void updateStokAdediByStokKodu(@Param("stokKodu") List<String> stokKodu, @Param("durum") EDurum durum);
}
