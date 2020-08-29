package com.aymer.sirketimceptebackend.listener.carikart.viewholder;


import com.aymer.sirketimceptebackend.model.enums.EOdemeYonu;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * User: ealtun
 * Date: 14.06.2020
 * Time: 09:27
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaturaViewHolder implements Serializable {

    private Date faturaTarihi;
    private String faturaNo;
    private BigDecimal malBedeli;
    private BigDecimal iskonto;
    private BigDecimal netToplam;
    private BigDecimal kdvTutari;
    private BigDecimal toplamTutar;
    private EOdemeYonu faturaYonu;

    private List<FaturaKalemViewHolder> faturaKalemList;
    private List<FaturaDetayViewHolder> faturaDetayList;

}
