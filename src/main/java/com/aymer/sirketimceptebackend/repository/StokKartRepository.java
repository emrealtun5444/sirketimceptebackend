package com.aymer.sirketimceptebackend.repository;

import com.aymer.sirketimceptebackend.model.StokKart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface StokKartRepository extends JpaRepository<StokKart, Long>, JpaSpecificationExecutor<StokKart> {

    Boolean existsByStokKodu(String stokKodu);

    StokKart findByStokKodu(String stokKodu);

}
