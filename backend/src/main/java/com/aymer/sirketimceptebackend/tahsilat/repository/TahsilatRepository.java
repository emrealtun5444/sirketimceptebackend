package com.aymer.sirketimceptebackend.tahsilat.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface TahsilatRepository extends JpaRepository<FinansalHareket, Long>, JpaSpecificationExecutor<FinansalHareket> {

    List<FinansalHareket> findAllByCariKartAndIslemTarihiGreaterThanEqual(CariKart cariKart, Date islemTarihi);

    List<FinansalHareket> findAllByCariKart(CariKart cariKart);

    List<FinansalHareket> findAllByEvrakNo(String evrakNo);

}
