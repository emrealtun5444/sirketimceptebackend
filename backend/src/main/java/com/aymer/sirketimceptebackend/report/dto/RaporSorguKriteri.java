package com.aymer.sirketimceptebackend.report.dto;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.KnowsQueryCriteriaHolderClass;
import com.aymer.sirketimceptebackend.report.model.RaporTuru;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RaporSorguKriteri implements KnowsQueryCriteriaHolderClass, Serializable {


    private RaporTuru raporTuru;
    private Date baslangicTarihi;
    private Date bitisTarihi;


}
