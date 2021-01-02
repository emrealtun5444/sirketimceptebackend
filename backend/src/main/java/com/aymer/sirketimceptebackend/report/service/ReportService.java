package com.aymer.sirketimceptebackend.report.service;


import com.aymer.sirketimceptebackend.report.dto.PerformansOzetDto;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface ReportService {

    PerformansOzetDto preparePerformansOzet(Integer year);

}
