package com.aymer.sirketimceptebackend.repository.specs;

import com.aymer.sirketimceptebackend.controller.fatura.dto.FaturaSorguKriteri;
import com.aymer.sirketimceptebackend.model.CariKart;
import com.aymer.sirketimceptebackend.model.Fatura;
import com.aymer.sirketimceptebackend.repository.specs.common.SearchCriteria;
import com.aymer.sirketimceptebackend.repository.specs.common.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class FaturaSpesification implements Specification<Fatura> {

    private FaturaSorguKriteri faturaSorguKriteri;

    @Override
    public Predicate toPredicate(Root<Fatura> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();

        Join<Fatura, CariKart> cariJoin = root.join("cariKart",JoinType.INNER);

        if (faturaSorguKriteri.getCariKodu() != null) {
            predicates.add(cb.like(cb.upper(cariJoin.get("cariKodu")), "%" + faturaSorguKriteri.getCariKodu().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        if (faturaSorguKriteri.getCariAdi() != null) {
            predicates.add(cb.like(cb.upper(cariJoin.get("cariAdi")), "%" + faturaSorguKriteri.getCariAdi().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        if (faturaSorguKriteri.getFaturaBaslangicTarihi() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("faturaTarihi"),  faturaSorguKriteri.getFaturaBaslangicTarihi()));
        }

        if (faturaSorguKriteri.getFaturaBitisTarihi() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("faturaTarihi"),  faturaSorguKriteri.getFaturaBitisTarihi()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
