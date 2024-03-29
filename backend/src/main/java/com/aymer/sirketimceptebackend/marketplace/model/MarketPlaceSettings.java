package com.aymer.sirketimceptebackend.marketplace.model;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.Auditable;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.marketplace.model.EMarketPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:04
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "market_place_settings")
public class MarketPlaceSettings extends Auditable<String> implements Serializable {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EMarketPlace marketPlace;

    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_secret")
    private String apiSecret;

    @Column(name = "image_path")
    private String imagePath;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EDurum durum;

}
