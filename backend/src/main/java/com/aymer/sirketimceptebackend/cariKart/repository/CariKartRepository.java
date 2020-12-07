package com.aymer.sirketimceptebackend.cariKart.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface CariKartRepository extends JpaRepository<CariKart, Long>, JpaSpecificationExecutor<CariKart> {

    Boolean existsByCariKodu(String cariKodu);

    CariKart findByCariKodu(String cariKodu);

}
