package com.automation.testing.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelReader {

    private ExcelReader() {}

    public static List<Map<String, String>> readSheet(String filePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in " + filePath);
            }

            Row headerRow = sheet.getRow(sheet.getFirstRowNum());
            if (headerRow == null) {
                return data;
            }

            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    Cell headerCell = headerRow.getCell(j);
                    if (headerCell == null) continue;

                    String header = headerCell.getStringCellValue();
                    Cell cell = row.getCell(j);
                    rowData.put(header, getCellValue(cell));
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                }
                yield String.valueOf((long) cell.getNumericCellValue());
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> {
                try {
                    yield String.valueOf((long) cell.getNumericCellValue());
                } catch (Exception e) {
                    yield cell.getStringCellValue();
                }
            }
            default -> "";
        };
    }
}
