package com.aymer.sirketimceptebackend.report.dto;

import com.aymer.sirketimceptebackend.common.model.enums.EEvetHayir;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReportGroup {
    private EEvetHayir status;
    private Map<String, List> group;

    public ReportGroup(EEvetHayir status) {
        this.status = status;
    }
}
