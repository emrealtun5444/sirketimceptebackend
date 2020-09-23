package com.aymer.sirketimceptebackend.repository;

import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
import com.aymer.sirketimceptebackend.repository.specs.predicate.CaritipiCiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long>, JpaSpecificationExecutor<Fatura> {

    List<Fatura> findAllByCariKartAndFaturaTarihiGreaterThanEqual(CariKart cariKart, Date faturaTarihi);

    @Query("SELECT sum(fd.miktar) from FaturaDetay fd join fd.fatura f where f.durum = :durum and f.faturaYonu = :faturaYonu and f.faturaTarihi >= :faturaTarihi")
    Long numberOfSales(@Param("faturaTarihi") Date faturaTarihi,
                              @Param("durum") EDurum durum ,
                              @Param("faturaYonu") EOdemeYonu odemeYonu);

    @Query("SELECT sum(f.toplamTutar) from Fatura f where f.durum = :durum and f.faturaYonu = :faturaYonu and f.faturaTarihi >= :faturaTarihi")
    BigDecimal amountOfSales(@Param("faturaTarihi") Date faturaTarihi,
                             @Param("durum") EDurum durum ,
                             @Param("faturaYonu") EOdemeYonu odemeYonu);

    @Query("select new com.aymer.sirketimceptebackend.repository.specs.predicate.CaritipiCiro(c.cariTipi , sum(f.toplamTutar)) from Fatura f join f.cariKart c where f.durum = :durum and f.faturaYonu = :faturaYonu and f.faturaTarihi >= :faturaTarihi group by c.cariTipi")
    List<CaritipiCiro> faturaKirilim(@Param("faturaTarihi") Date faturaTarihi,
                                     @Param("durum") EDurum durum ,
                                     @Param("faturaYonu") EOdemeYonu odemeYonu);

}
