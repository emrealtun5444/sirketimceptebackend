package com.aymer.sirketimceptebackend.report.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.report.dto.CiroDto;
import com.aymer.sirketimceptebackend.report.dto.HedefDto;
import com.aymer.sirketimceptebackend.report.dto.MarkaDagilimDto;
import com.aymer.sirketimceptebackend.report.dto.SiparisDto;
import com.aymer.sirketimceptebackend.siparis.model.SiparisYonu;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.tahsilat.model.EOdemeYonu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface ReportRepository extends JpaRepository<CariKart, Long>, JpaSpecificationExecutor<CariKart> {

    @Query("SELECT new com.aymer.sirketimceptebackend.report.dto.CiroDto(" +
        "COALESCE(sum(fd.toplamTutar), 0) " +
        ") " +
        "from Fatura f " +
        "join f.faturaDetays fd " +
        "where f.durum = :durum " +
        "and f.faturaYonu = :faturaYonu " +
        "and f.sirket = :sirket " +
        "and (:donem is null or MONTH(f.faturaTarihi) = :donem) " +
        "and YEAR(f.faturaTarihi) = :year ")
    CiroDto amountOfSalesForPeriod(@Param("donem") Integer donem,
                                   @Param("year") Integer year,
                                   @Param("durum") EDurum durum,
                                   @Param("faturaYonu") EOdemeYonu odemeYonu,
                                   @Param("sirket") Sirket sirket);

    @Query("SELECT new com.aymer.sirketimceptebackend.report.dto.SiparisDto(" +
        "COALESCE(count(s.miktar),0), " +
        "COALESCE(count(s.teslimMiktari),0), " +
        "COALESCE((count(s.miktar) - count(s.teslimMiktari)),0), " +
        "(case when COALESCE(count(s.miktar),0) > 0 then (COALESCE(count(s.teslimMiktari),0) * 100 / COALESCE(count(s.miktar),0)) else 0 end ), " +
        "COALESCE(sum(s.tutari),0)" +
        ") " +
        "from Siparis s " +
        "where s.durum = :durum " +
        "and s.siparisYonu = :siparisYonu " +
        "and s.sirket = :sirket " +
        "and (:donem is null or MONTH(s.islemTarihi) = :donem) " +
        "and YEAR(s.islemTarihi) = :year ")
    SiparisDto amountOfSiparisForPeriod(@Param("donem") Integer donem,
                                        @Param("year") Integer year,
                                        @Param("durum") EDurum durum,
                                        @Param("siparisYonu") SiparisYonu siparisYonu,
                                        @Param("sirket") Sirket sirket);

    @Query("select COALESCE(sum(COALESCE(c.yillikHedef,0)),0) " +
        "from CariKart c " +
        "where c.sirket = :sirket " +
        "and c.durum = :durum " +
        "and (:staff is null or c.sorumluPersonel = :staff) ")
    BigDecimal totalHedefTutar(@Param("durum") EDurum durum,
                               @Param("sirket") Sirket sirket,
                               @Param("staff") User staff);

    @Query("select new com.aymer.sirketimceptebackend.report.dto.HedefDto(MONTH(f.faturaTarihi) , sum(fd.toplamTutar)) " +
        "from Fatura f " +
        "join f.cariKart c " +
        "join f.faturaDetays fd " +
        "where f.durum = :durum " +
        "and f.faturaYonu = :faturaYonu " +
        "and f.sirket = :sirket " +
        "and (:staff is null or c.sorumluPersonel = :staff) " +
        "and YEAR(f.faturaTarihi) = :year " +
        "group by MONTH(f.faturaTarihi)")
    List<HedefDto> donemCiroDagilim(@Param("year") Integer year,
                                    @Param("durum") EDurum durum,
                                    @Param("faturaYonu") EOdemeYonu odemeYonu,
                                    @Param("sirket") Sirket sirket,
                                    @Param("staff") User staff);


    @Query("SELECT new com.aymer.sirketimceptebackend.report.dto.SiparisDto(" +
        "MONTH(s.islemTarihi), " +
        "COALESCE(count(s.miktar),0), " +
        "COALESCE(count(s.teslimMiktari),0), " +
        "COALESCE((count(s.miktar) - count(s.teslimMiktari)),0), " +
        "(case when COALESCE(count(s.miktar),0) > 0 then (COALESCE(count(s.teslimMiktari),0) * 100 / COALESCE(count(s.miktar),0)) else 0 end ), " +
        "COALESCE(sum(s.tutari),0)" +
        ") " +
        "from Siparis s " +
        "join s.cariKart c " +
        "where s.durum = :durum " +
        "and s.siparisYonu = :siparisYonu " +
        "and s.sirket = :sirket " +
        "and (:staff is null or c.sorumluPersonel = :staff) " +
        "and YEAR(s.islemTarihi) = :year " +
        "group by MONTH(s.islemTarihi)")
    List<SiparisDto> donemeGoreSiparisDagilimi(@Param("year") Integer year,
                                               @Param("durum") EDurum durum,
                                               @Param("siparisYonu") SiparisYonu siparisYonu,
                                               @Param("sirket") Sirket sirket,
                                               @Param("staff") User staff);

    @Query("select new com.aymer.sirketimceptebackend.report.dto.MarkaDagilimDto(COALESCE(m.aciklama,'Diger'), sum(fd.toplamTutar)) " +
        "from Fatura f " +
        "join f.cariKart c " +
        "join f.faturaDetays fd " +
        "join fd.stokKart sk " +
        "left join sk.marka m " +
        "where f.durum = :durum " +
        "and f.faturaYonu = :faturaYonu " +
        "and f.sirket = :sirket " +
        "and (:staff is null or c.sorumluPersonel = :staff) " +
        "and YEAR(f.faturaTarihi) = :year " +
        "group by COALESCE(m.aciklama,'Diger')")
    List<MarkaDagilimDto> donemeGoreMarkaDagilimi(@Param("year") Integer year,
                                                  @Param("durum") EDurum durum,
                                                  @Param("faturaYonu") EOdemeYonu odemeYonu,
                                                  @Param("sirket") Sirket sirket,
                                                  @Param("staff") User staff);
}