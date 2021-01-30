package com.aymer.sirketimceptebackend.fatura.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
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
            "where f.durum = :durum " +
            "and f.faturaYonu = :faturaYonu " +
            "and (:cariKart is null or c = :cariKart) " +
            "and (:donem is null or MONTH(f.faturaTarihi) = :donem) " +
            "and YEAR(f.faturaTarihi) = :year ")
    List<FaturaDetay> faturaKalems(@Param("cariKart") CariKart cariKart,
                                   @Param("durum") EDurum durum,
                                   @Param("faturaYonu") EOdemeYonu odemeYonu,
                                   @Param("donem") Integer donem,
                                   @Param("year") Integer year);

}
