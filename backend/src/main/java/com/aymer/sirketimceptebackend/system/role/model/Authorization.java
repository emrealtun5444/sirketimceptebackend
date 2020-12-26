package com.aymer.sirketimceptebackend.system.role.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Authorization implements Serializable {
    //System
    SYSTEM_MENU("menu.system.yonetimi", null),
    COMPANY_MENU("menu.sirket.ayarlari", SYSTEM_MENU),
    USER_MENU("menu.kullanici.ayarlari" , SYSTEM_MENU),
    ROLE_MENU("menu.role.ayarlari", SYSTEM_MENU),

    // Stok
    STOK_MENU("menu.stok.yonetimi", null),

    // Cari
    CARI_MENU("menu.cari.yonetimi", null),

    // Fatura
    FATURA_MENU("menu.fatura.yonetimi", null),

    // Siparis
    SIPARIS_MENU("menu.siparis.yonetimi", null),

    // Marketplace
    PAZARYERI_MENU("menu.pazaryeri.yonetimi", null),
    ENTEGRASYON_AYAR("menu.pazaryeri.conf", PAZARYERI_MENU);


    private String label;
    private Authorization parent;

}
