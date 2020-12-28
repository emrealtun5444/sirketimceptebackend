package com.aymer.sirketimceptebackend.dashboard.dto;

import com.aymer.sirketimceptebackend.system.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SorumluPersonelCiroDto {
    private String nameSurname;
    private BigDecimal tutar;
}
