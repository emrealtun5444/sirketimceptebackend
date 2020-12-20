package com.aymer.sirketimceptebackend.system.sirket.repository;

import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:08
 */
@Repository
public interface SirketRepository extends JpaRepository<Sirket, Long> {
    Long countById(Long id);
    List<Sirket> findByOperationIsNullAndDurum(EDurum durum);
}
