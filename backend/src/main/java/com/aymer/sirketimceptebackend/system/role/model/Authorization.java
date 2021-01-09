package com.aymer.sirketimceptebackend.system.role.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Authorization implements Serializable {
    ROOT_AUTHORIZATION("root.authorization",null),

    //System
    SYSTEM_MENU("menu.system.yonetimi", ROOT_AUTHORIZATION),
    COMPANY_MENU("menu.company.ayarlari", SYSTEM_MENU),
    USER_MENU("menu.kullanici.ayarlari" , SYSTEM_MENU),
    ROLE_MENU("menu.role.ayarlari", SYSTEM_MENU),

    // Stok
    STOK_MENU("menu.stok.yonetimi", ROOT_AUTHORIZATION),

    // Cari
    CARI_MENU("menu.cari.yonetimi", ROOT_AUTHORIZATION),

    // Fatura
    FATURA_MENU("menu.fatura.yonetimi", ROOT_AUTHORIZATION),

    // Siparis
    SIPARIS_MENU("menu.siparis.yonetimi", ROOT_AUTHORIZATION),

    // Tahsilat
    TAHSILAT_MENU("menu.tahsilat.yonetimi", ROOT_AUTHORIZATION),

    // Marketplace
    PAZARYERI_MENU("menu.pazaryeri.yonetimi", ROOT_AUTHORIZATION),
    ENTEGRASYON_AYAR("menu.pazaryeri.conf", PAZARYERI_MENU),

    // Raporlar
    REPORT_MENU("menu.rapor.yonetimi", ROOT_AUTHORIZATION),
    PERFORMANS_REPORT_MENU("menu.rapor.performans", REPORT_MENU);

    private String label;
    private Authorization parent;

}
