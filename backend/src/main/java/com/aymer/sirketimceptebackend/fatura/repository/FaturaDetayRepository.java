package com.aymer.sirketimceptebackend.fatura.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.fatura.model.Fatura;
import com.aymer.sirketimceptebackend.fatura.model.FaturaDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
