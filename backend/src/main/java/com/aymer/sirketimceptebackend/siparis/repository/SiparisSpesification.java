package com.aymer.sirketimceptebackend.siparis.repository;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.siparis.dto.SiparisSorguKriteri;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.stokkart.model.StokKart;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class SiparisSpesification implements Specification<Siparis> {

    private final SessionUtils sessionUtils;
    private final SiparisSorguKriteri sorguKriteri;

    @Override
    public Predicate toPredicate(Root<Siparis> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();

        Join<Siparis, CariKart> cariJoin = root.join("cariKart", JoinType.INNER);
        Join<Siparis, StokKart> stokJoin = root.join("stokKart", JoinType.INNER);

        if (sorguKriteri.getSiparisNo() != null) {
            predicates.add(cb.like(cb.upper(root.get("siparisNo")), "%" + sorguKriteri.getSiparisNo().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
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

        if (sorguKriteri.getSiparisDurumu() != null) {
            predicates.add(cb.equal(root.get("siparisDurumu"), sorguKriteri.getSiparisDurumu()));
        }

        if (sorguKriteri.getSiparisYonu() != null) {
            predicates.add(cb.equal(root.get("siparisYonu"), sorguKriteri.getSiparisYonu()));
        }

        if (sorguKriteri.getStokKodu() != null) {
            predicates.add(cb.like(cb.upper(stokJoin.get("stokKodu")), "%" + sorguKriteri.getStokKodu().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        if (sorguKriteri.getUrunAdi() != null) {
            predicates.add(cb.like(cb.upper(stokJoin.get("urunAdi")), "%" + sorguKriteri.getUrunAdi().toUpperCase(LocaleContextHolder.getLocale()) + "%"));
        }

        predicates.add(cb.equal(root.get("sirket"), sessionUtils.getSelectedCompany()));

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
