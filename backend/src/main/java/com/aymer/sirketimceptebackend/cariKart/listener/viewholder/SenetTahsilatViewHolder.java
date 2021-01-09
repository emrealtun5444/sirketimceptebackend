package com.aymer.sirketimceptebackend.cariKart.listener.viewholder;


import lombok.*;

import java.io.Serializable;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
public class SenetTahsilatViewHolder extends TahsilatViewHolder implements Serializable {

    private String borcluAdi;
    private String borcluAdresi;

}
