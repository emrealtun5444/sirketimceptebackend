package com.aymer.sirketimceptebackend.repository;

import com.aymer.sirketimceptebackend.model.common.Sirket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface SirketRepository extends JpaRepository<Sirket, Long> {

}
