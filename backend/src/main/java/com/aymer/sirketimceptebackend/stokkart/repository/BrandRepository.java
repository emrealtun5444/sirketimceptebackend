package com.aymer.sirketimceptebackend.stokkart.repository;

import com.aymer.sirketimceptebackend.stokkart.model.Marka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface BrandRepository extends JpaRepository<Marka, Long> {

}
