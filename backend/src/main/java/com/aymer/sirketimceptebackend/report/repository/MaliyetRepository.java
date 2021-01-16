package com.aymer.sirketimceptebackend.report.repository;

import com.aymer.sirketimceptebackend.report.model.Maliyet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface MaliyetRepository extends JpaRepository<Maliyet, Long> {


}
