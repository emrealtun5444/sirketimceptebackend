package com.aymer.sirketimceptebackend.fatura.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.fatura.dto.SorumluPersonelCiro;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.report.dto.CiroDto;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
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

    Fatura getByFaturaNo(String faturaNo);

    List<Fatura> findAllByCariKartAndFaturaTarihiGreaterThanEqual(CariKart cariKart, Date faturaTarihi);

    @Query("SELECT sum(fd.miktar) from FaturaDetay fd join fd.fatura f where f.durum = :durum and f.faturaYonu = :faturaYonu and f.faturaTarihi >= :faturaTarihi")
    Long numberOfSales(@Param("faturaTarihi") Date faturaTarihi,
                       @Param("durum") EDurum durum,
                       @Param("faturaYonu") EOdemeYonu odemeYonu);

    @Query("SELECT sum(f.toplamTutar) from Fatura f where f.durum = :durum and f.faturaYonu = :faturaYonu and f.faturaTarihi >= :faturaTarihi " +
        "and exists(select 1 from FaturaDetay fd where fd.fatura = f)")
    BigDecimal amountOfSales(@Param("faturaTarihi") Date faturaTarihi,
                             @Param("durum") EDurum durum,
                             @Param("faturaYonu") EOdemeYonu odemeYonu);

    @Query("select new com.aymer.sirketimceptebackend.fatura.dto.SorumluPersonelCiro(p , sum(f.toplamTutar)) from Fatura f join f.cariKart c left join c.sorumluPersonel p where f.durum = :durum and f.faturaYonu = :faturaYonu and f.faturaTarihi >= :faturaTarihi " +
        "and exists(select 1 from FaturaDetay fd where fd.fatura = f) " +
        "group by p")
    List<SorumluPersonelCiro> personelCiroDagilim(@Param("faturaTarihi") Date faturaTarihi,
                                                  @Param("durum") EDurum durum,
                                                  @Param("faturaYonu") EOdemeYonu odemeYonu);

}
