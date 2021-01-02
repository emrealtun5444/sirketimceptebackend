package com.aymer.sirketimceptebackend.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PerformansOzetDto {

    private Donem donem;
    private CiroDto aylikCiro;
    private CiroDto yillikCiro;
    private HedefDto aylikHedef;
    private HedefDto yillikHedef;
    private TahsilatDto aylikTahsilat;
    private TahsilatDto yillikTahsilat;
    private SiparisDto aylikSiparis;
    private SiparisDto yillikSiparis;

}
