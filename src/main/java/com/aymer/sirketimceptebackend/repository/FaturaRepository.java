package com.aymer.sirketimceptebackend.repository;

import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.Fatura;
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
public interface FaturaRepository extends JpaRepository<Fatura, Long>, JpaSpecificationExecutor<Fatura> {

    List<Fatura> findAllByCariKartAndFaturaTarihiGreaterThanEqual(CariKart cariKart, Date faturaTarihi);

}
