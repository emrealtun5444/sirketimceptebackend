package com.aymer.sirketimceptebackend.fatura.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import com.aymer.sirketimceptebackend.stokkart.model.Marka;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface FaturaDetayRepository extends JpaRepository<FaturaDetay, Long> {

    Optional<List<FaturaDetay>> findAllByFatura(Fatura fatura);

    List<FaturaDetay> findAllByCariKart(CariKart cariKart);

    @Query("SELECT fd " +
            "from FaturaDetay fd " +
            "join fd.fatura f " +
            "join fd.cariKart c " +
            "join fd.stokKart s " +
            "left join s.marka m " +
            "where f.durum = :durum " +
            "and f.faturaYonu = :faturaYonu " +
            "and (:cariKart is null or c = :cariKart) " +
            "and (:donem is null or MONTH(f.faturaTarihi) = :donem) " +
            "and (:marka is null or m = :marka) " +
            "and YEAR(f.faturaTarihi) = :year ")
    List<FaturaDetay> faturaKalems(@Param("marka") Marka marka,
                                   @Param("cariKart") CariKart cariKart,
                                   @Param("durum") EDurum durum,
                                   @Param("faturaYonu") EOdemeYonu odemeYonu,
                                   @Param("donem") Integer donem,
                                   @Param("year") Integer year);

}
