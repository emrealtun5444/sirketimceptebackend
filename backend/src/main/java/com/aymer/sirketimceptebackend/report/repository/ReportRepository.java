package com.aymer.sirketimceptebackend.report.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.report.dto.*;
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

    @Query("SELECT new com.aymer.sirketimceptebackend.report.dto.TahsilatDto(" +
            "COALESCE(sum(fh.tutar), 0) " +
            ") " +
            "from FinansalHareket fh " +
            "where fh.durum = :durum " +
            "and fh.odemeYonu = :odemeYonu " +
            "and fh.sirket = :sirket " +
            "and (:donem is null or MONTH(fh.islemTarihi) = :donem) " +
            "and YEAR(fh.islemTarihi) = :year ")
    TahsilatDto amountOfTahsilatForPeriod(@Param("donem") Integer donem,
                                   @Param("year") Integer year,
                                   @Param("durum") EDurum durum,
                                   @Param("odemeYonu") EOdemeYonu odemeYonu,
                                   @Param("sirket") Sirket sirket);

    @Query("SELECT new com.aymer.sirketimceptebackend.report.dto.SiparisDto(" +
        "COALESCE(sum(s.miktar),0), " +
        "COALESCE(sum(s.teslimMiktari),0), " +
        "COALESCE((sum(s.miktar) - sum(s.teslimMiktari)),0), " +
        "(case when COALESCE(sum(s.miktar),0) > 0 then (COALESCE(sum(s.teslimMiktari),0) * 100 / COALESCE(sum(s.miktar),0)) else 0 end ), " +
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

    @Query("select new com.aymer.sirketimceptebackend.report.dto.TahsilatDto( " +
            "MONTH(fh.islemTarihi), " +
            "sum(fh.tutar), " +
            "sum(case when fh.odemeTipi = 'NAKIT' then fh.tutar else 0 end ), " +
            "sum(case when fh.odemeTipi = 'CEK' then fh.tutar else 0 end ), " +
            "sum(case when fh.odemeTipi = 'SENET' then fh.tutar else 0 end ) " +
            ") " +
            "from FinansalHareket fh " +
            "join fh.cariKart c " +
            "where fh.durum = :durum " +
            "and fh.odemeYonu = :odemeYonu " +
            "and fh.sirket = :sirket " +
            "and (:staff is null or c.sorumluPersonel = :staff) " +
            "and YEAR(fh.islemTarihi) = :year " +
            "group by MONTH(fh.islemTarihi)")
    List<TahsilatDto> donemeGoreTahsilatDagilimi(@Param("year") Integer year,
                                    @Param("durum") EDurum durum,
                                    @Param("odemeYonu") EOdemeYonu odemeYonu,
                                    @Param("sirket") Sirket sirket,
                                    @Param("staff") User staff);

    @Query("SELECT new com.aymer.sirketimceptebackend.report.dto.SiparisDto(" +
        "MONTH(s.islemTarihi), " +
        "COALESCE(sum(s.miktar),0), " +
        "COALESCE(sum(s.teslimMiktari),0), " +
        "COALESCE((sum(s.miktar) - sum(s.teslimMiktari)),0), " +
        "(case when COALESCE(sum(s.miktar),0) > 0 then (COALESCE(sum(s.teslimMiktari),0) * 100 / COALESCE(sum(s.miktar),0)) else 0 end ), " +
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

    @Query("select new com.aymer.sirketimceptebackend.report.dto.HedefCariDto(" +
        "c.cariKodu, c.cariAdi, c.toplamBorc, c.toplamAlacak, (c.toplamAlacak- c.toplamBorc), COALESCE(c.yillikHedef,0), COALESCE(sum(fd.toplamTutar),0)) " +
        "from CariKart c " +
        "left join Fatura f on f.cariKart = c and f.durum = :durum and f.faturaYonu = :faturaYonu and YEAR(f.faturaTarihi) = :year " +
        "left join FaturaDetay fd on fd.fatura = f " +
        "where c.sirket = :sirket " +
        "and (:staff is null or c.sorumluPersonel = :staff) " +
        "group by c.cariKodu, c.cariAdi, c.toplamBorc, c.toplamAlacak, (c.toplamAlacak- c.toplamBorc), COALESCE(c.yillikHedef,0)")
    List<HedefCariDto> donemeGoreHedefCariDagilimi(@Param("year") Integer year,
                                                   @Param("durum") EDurum durum,
                                                   @Param("faturaYonu") EOdemeYonu odemeYonu,
                                                   @Param("sirket") Sirket sirket,
                                                   @Param("staff") User staff);
}
