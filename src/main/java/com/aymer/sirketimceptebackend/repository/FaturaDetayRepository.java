package com.aymer.sirketimceptebackend.repository;

import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.model.FaturaDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

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

}
