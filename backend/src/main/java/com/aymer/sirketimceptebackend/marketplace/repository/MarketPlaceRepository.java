package com.aymer.sirketimceptebackend.marketplace.repository;

import com.aymer.sirketimceptebackend.marketplace.model.MarketPlaceSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface MarketPlaceRepository extends JpaRepository<MarketPlaceSettings, Long>, JpaSpecificationExecutor<MarketPlaceSettings> {


}
