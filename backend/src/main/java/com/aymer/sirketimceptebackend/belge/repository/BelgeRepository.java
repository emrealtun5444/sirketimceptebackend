package com.aymer.sirketimceptebackend.belge.repository;

import com.aymer.sirketimceptebackend.belge.model.Belge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface BelgeRepository extends JpaRepository<Belge, Long> {


}
