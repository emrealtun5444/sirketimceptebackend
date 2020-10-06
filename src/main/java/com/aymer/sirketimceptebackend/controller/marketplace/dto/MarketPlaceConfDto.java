package com.aymer.sirketimceptebackend.controller.marketplace.dto;

import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.EMarketPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: ealtun
 * <p>
 * Date: 14.06.2020
 * Time: 09:27
 */
@Getter
@Setter
@NoArgsConstructor
public class MarketPlaceConfDto implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private EMarketPlace marketPlace;
    private String sellerId;
    private String apiKey;
    private String apiSecret;
    @NotNull
    private EDurum durum;
}
