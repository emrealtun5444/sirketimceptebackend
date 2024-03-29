package com.aymer.sirketimceptebackend.report.service;

import com.aymer.sirketimceptebackend.cariKart.model.CariKart;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.KnowsQueryCriteriaHolderClass;
import com.aymer.sirketimceptebackend.common.model.enums.EEvetHayir;
import com.aymer.sirketimceptebackend.report.dto.ReportGroup;
import com.aymer.sirketimceptebackend.report.dto.Stok;
import com.aymer.sirketimceptebackend.report.model.AsenkronRaporBilgi;
import com.aymer.sirketimceptebackend.report.model.ColumnDataType;
import com.aymer.sirketimceptebackend.report.model.ReportBaseEnum;
import com.aymer.sirketimceptebackend.report.repository.AsenkronRaporBilgiRepository;
import com.aymer.sirketimceptebackend.siparis.model.Siparis;
import com.aymer.sirketimceptebackend.utils.MoneyUtils;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractAsenkronVeriHazirlamaRaporServiceImp implements AbstractAsenkronVeriHazirlamaRaporService {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractAsenkronVeriHazirlamaRaporServiceImp.class);

    private AsenkronRaporGeneratorService asenkronRaporGeneratorService;
    private AsenkronRaporBilgiRepository asenkronRaporBilgiRepository;

    public AbstractAsenkronVeriHazirlamaRaporServiceImp(AsenkronRaporGeneratorService asenkronRaporGeneratorService, AsenkronRaporBilgiRepository asenkronRaporBilgiRepository) {
        this.asenkronRaporGeneratorService = asenkronRaporGeneratorService;
        this.asenkronRaporBilgiRepository = asenkronRaporBilgiRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void prepareReport(AsenkronRaporBilgi asenkronRaporBilgi) {
        // eğer başka bir thread tarafından başlatıldı ise durması sağlanıyor.
        Boolean canStart = asenkronRaporGeneratorService.canStart(asenkronRaporBilgi);
        if (!canStart) return;

        // başlatılır.
        asenkronRaporBilgi = asenkronRaporGeneratorService.start(asenkronRaporBilgi);

        // excel generate ediliyor.
        byte[] excelBytes = createExcelBytes(asenkronRaporBilgi);

        // süreç sonlşandırılıyor.
        asenkronRaporGeneratorService.finish(asenkronRaporBilgi, excelBytes);
    }


    public abstract List sorguSonucuGetir(AsenkronRaporBilgi asenkronRaporBilgi);

    public abstract ReportBaseEnum<String>[] getReportHeaders();

    public abstract ReportGroup getReportGroup(List result);

    private byte[] createExcelBytes(AsenkronRaporBilgi asenkronRaporBilgi) {
        // Sorgu Sonucu Çekiliyor.
        List resultList = sorguSonucuGetir(asenkronRaporBilgi);

        // header bilgileri alınıyopr.
        XSSFWorkbook workbook = new XSSFWorkbook();
        // RAPOR GRUBU ÇEKİLİYOR.
        ReportGroup reportGroup = getReportGroup(resultList);
        if (reportGroup.getStatus().equals(EEvetHayir.EVET_VAR)) {
            for (Map.Entry<String, List> entry : reportGroup.getGroup().entrySet()) {
                executeSheet(entry.getKey(), entry.getValue(), workbook);
            }
        } else {
            executeSheet(asenkronRaporBilgi.getRaporTuru().name(), resultList, workbook);
        }
        return convertWorkbookBytes(workbook);
    }

    private void executeSheet(String sheetName, List resultList, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        ReportBaseEnum<String>[] headers = getReportHeaders();
        int rowNum = createHeader(workbook, sheet, headers);
        //  rapor detayları yazılıyor.
        if (!CollectionUtils.isEmpty(resultList)) {
            for (Object rowData : resultList) {
                rowNum = createRows(workbook, sheet, headers, rowNum, rowData);
            }
        }
    }

    private byte[] convertWorkbookBytes(XSSFWorkbook workbook) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            bos.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return bos.toByteArray();
    }

    private int createRows(XSSFWorkbook workbook, XSSFSheet sheet, ReportBaseEnum<String>[] headers, int rowNum, Object rowData) {
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        for (ReportBaseEnum<String> baseEnum : headers) {
            Cell cell = row.createCell(colNum++);
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            Field field = null;
            try {
                field = rowData.getClass().getDeclaredField(baseEnum.getValue());
                field.setAccessible(true);
                Object value = field.get(rowData);
                if (value == null) continue;

                if (baseEnum.getColumnDataType().equals(ColumnDataType.STRING)) {
                    cell.setCellValue((String) value);
                } else if (baseEnum.getColumnDataType().equals(ColumnDataType.DATE)) {
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.MM.yyyy"));
                    cell.setCellValue((Date) value);
                } else if (baseEnum.getColumnDataType().equals(ColumnDataType.NUMBER)) {
                    cell.setCellValue((Long) value);
                } else if (baseEnum.getColumnDataType().equals(ColumnDataType.MONEY)) {
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#,##0.00"));
                    cell.setCellValue(MoneyUtils.getRoundedValue((BigDecimal) value));
                }
                cell.setCellStyle(cellStyle);
            } catch (NoSuchFieldException e) {
                logger.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return rowNum;
    }

    private int createHeader(XSSFWorkbook workbook, XSSFSheet sheet, ReportBaseEnum<String>[] headers) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);

        int rowNum = 0;
        // rapor başlıkları yazılıyor.
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        for (ReportBaseEnum<String> header : headers) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue((String) header.getLabel());
            cell.setCellStyle(headerStyle);
        }
        return rowNum;
    }

    protected SessionUtils createSessionInfo(AsenkronRaporBilgi asenkronRaporBilgi) {
        SessionUtils sessionInfo = null;
        try {
            InputStream rawStream = new ByteArrayInputStream(asenkronRaporBilgi.getSessionInfo());
            ObjectInputStream is = new ObjectInputStream(rawStream);
            sessionInfo = (SessionUtils) is.readObject();
            is.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sessionInfo;
    }

    protected KnowsQueryCriteriaHolderClass createSorguKriteri(AsenkronRaporBilgi asenkronRaporBilgi) {
        KnowsQueryCriteriaHolderClass knowsQueryCriteriaHolderClass = null;
        try {
            InputStream rawStream = new ByteArrayInputStream(asenkronRaporBilgi.getSorguKriteri());
            ObjectInputStream is = new ObjectInputStream(rawStream);
            knowsQueryCriteriaHolderClass = (KnowsQueryCriteriaHolderClass) is.readObject();
            is.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return knowsQueryCriteriaHolderClass;
    }

}
