package com.aymer.sirketimceptebackend.siparis.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface SiparisRepository extends JpaRepository<Siparis, Long>, JpaSpecificationExecutor<Siparis> {

    List<Siparis> findAllByCariKart(CariKart cariKart);

    List<Siparis> findAllBySiparisNo(String siparisNo);

}
