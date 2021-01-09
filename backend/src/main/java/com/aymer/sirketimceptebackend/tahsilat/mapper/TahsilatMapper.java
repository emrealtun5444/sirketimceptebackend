package com.aymer.sirketimceptebackend.tahsilat.mapper;

import com.aymer.sirketimceptebackend.cariKart.listener.viewholder.TahsilatViewHolder;
import com.aymer.sirketimceptebackend.tahsilat.dto.TahsilatDto;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareket;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareketCek;
import com.aymer.sirketimceptebackend.tahsilat.model.FinansalHareketSenet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class TahsilatMapper {

    @Mappings({
            @Mapping(target = "cariAdi", source = "cariKart.cariAdi"),
            @Mapping(target = "cariKodu", source = "cariKart.cariKodu"),
            @Mapping(target = "banka", expression = "java(getBanka(finansalHareket))"),
            @Mapping(target = "subeAdi", expression = "java(getSubeAdi(finansalHareket))"),
            @Mapping(target = "bankaHesapNo", expression = "java(getBankaHesapNo(finansalHareket))"),
            @Mapping(target = "bankaCekNo", expression = "java(getBankaCekNo(finansalHareket))"),
            @Mapping(target = "borcluAdi", expression = "java(getBorcluAdi(finansalHareket))"),
            @Mapping(target = "borcluAdresi", expression = "java(getBorcluAdresi(finansalHareket))"),

    })
    public abstract TahsilatDto toDto(FinansalHareket finansalHareket);

    public abstract List<TahsilatDto> toDtoList(List<FinansalHareket> finansalHareketList);

    public abstract void update(TahsilatViewHolder tahsilatViewHolder, @MappingTarget FinansalHareket finansalHareket);

    public String getBanka(FinansalHareket finansalHareket) {
        if (finansalHareket instanceof FinansalHareketCek) {
            FinansalHareketCek finansalHareketCek = (FinansalHareketCek) finansalHareket;
            return finansalHareketCek.getBanka();
        }
        return null;
    }

    public String getSubeAdi(FinansalHareket finansalHareket) {
        if (finansalHareket instanceof FinansalHareketCek) {
            FinansalHareketCek finansalHareketCek = (FinansalHareketCek) finansalHareket;
            return finansalHareketCek.getSubeAdi();
        }
        return null;
    }

    public String getBankaHesapNo(FinansalHareket finansalHareket) {
        if (finansalHareket instanceof FinansalHareketCek) {
            FinansalHareketCek finansalHareketCek = (FinansalHareketCek) finansalHareket;
            return finansalHareketCek.getBankaHesapNo();
        }
        return null;
    }

    public String getBankaCekNo(FinansalHareket finansalHareket) {
        if (finansalHareket instanceof FinansalHareketCek) {
            FinansalHareketCek finansalHareketCek = (FinansalHareketCek) finansalHareket;
            return finansalHareketCek.getBankaCekNo();
        }
        return null;
    }

    public String getBorcluAdi(FinansalHareket finansalHareket) {
        if (finansalHareket instanceof FinansalHareketCek) {
            FinansalHareketCek finansalHareketCek = (FinansalHareketCek) finansalHareket;
            return finansalHareketCek.getBorcluAdi();
        } else if (finansalHareket instanceof FinansalHareketSenet) {
            FinansalHareketSenet finansalHareketSenet = (FinansalHareketSenet) finansalHareket;
            return finansalHareketSenet.getBorcluAdi();
        }
        return null;
    }

    public String getBorcluAdresi(FinansalHareket finansalHareket) {
        if (finansalHareket instanceof FinansalHareketCek) {
            FinansalHareketCek finansalHareketCek = (FinansalHareketCek) finansalHareket;
            return finansalHareketCek.getBorcluAdresi();
        } else if (finansalHareket instanceof FinansalHareketSenet) {
            FinansalHareketSenet finansalHareketSenet = (FinansalHareketSenet) finansalHareket;
            return finansalHareketSenet.getBorcluAdresi();
        }
        return null;
    }
}
