package com.aymer.sirketimceptebackend.tahsilat.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.tahsilat.dto.TahsilatSorguKriteri;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class TahsilatSpesification implements Specification<FinansalHareket> {

    private final SessionUtils sessionUtils;
    private final TahsilatSorguKriteri sorguKriteri;

    @Override
    public Predicate toPredicate(Root<FinansalHareket> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();

        Join<FinansalHareket, CariKart> cariJoin = root.join("cariKart", JoinType.INNER);

        if (sorguKriteri.getEvrakNo() != null) {
            predicates.add(cb.like(cb.upper(root.get("evrakNo")), "%" + sorguKriteri.getEvrakNo().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        if (sorguKriteri.getCariKodu() != null) {
            predicates.add(cb.like(cb.upper(cariJoin.get("cariKodu")), "%" + sorguKriteri.getCariKodu().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        if (sorguKriteri.getCariAdi() != null) {
            predicates.add(cb.like(cb.upper(cariJoin.get("cariAdi")), "%" + sorguKriteri.getCariAdi().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        if (sorguKriteri.getStaff() != null) {
            predicates.add(cb.equal(cariJoin.get("sorumluPersonel"), sorguKriteri.getStaff()));
        }

        if (sorguKriteri.getBaslangicTarihi() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("islemTarihi"), sorguKriteri.getBaslangicTarihi()));
        }

        if (sorguKriteri.getBitisTarihi() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("islemTarihi"), sorguKriteri.getBitisTarihi()));
        }

        if (sorguKriteri.getOdemeTipi() != null) {
            predicates.add(cb.equal(root.get("odemeTipi"), sorguKriteri.getOdemeTipi()));
        }

        if (sorguKriteri.getOdemeYonu() != null) {
            predicates.add(cb.equal(root.get("odemeYonu"), sorguKriteri.getOdemeYonu()));
        }

        predicates.add(cb.equal(root.get("sirket"), sessionUtils.getSelectedCompany()));

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
