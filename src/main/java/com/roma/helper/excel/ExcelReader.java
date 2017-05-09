package com.roma.helper.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roma on 04/05/2017.
 */
public class ExcelReader {

    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public List<List<String>> read(String path, int sheetIndex) {
        List<List<String>> data = new ArrayList<>();
        Workbook workbook = getWorkBook(path);
        if (workbook == null) return null;
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int r = 0; r < rowCount; r++) {
            Row row = sheet.getRow(r);
            int cellCount = row.getPhysicalNumberOfCells();
            List<String> rowData = new ArrayList<>();
            for (int c = 0; c < cellCount; c++) {
                Cell cell = row.getCell(c);
                String cellValue = formatCellValue(cell);
                rowData.add(cellValue);
            }
            data.add(rowData);
        }
        return data;
    }

    private Workbook getWorkBook(String path) {
        try {
            //同时支持Excel 2003、2007
            File excelFile = new File(path);
            FileInputStream is = new FileInputStream(excelFile);
            return WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String formatCellValue(Cell cell) {
        String cellValue = getValueByType(cell, cell.getCellType());
        cellValue = cellValue.trim()
                .replaceAll("([\\s\\u00A0]+)?,([\\s\\u00A0]+)?", "|")
                .replaceAll("([\\s\\u00A0]+)?，([\\s\\u00A0]+)?", "|")
                .replaceAll("、", "|");
        return cellValue;
    }

    private String getValueByType(Cell cell, int cellType) {
        String cellValue;
        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //文本
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC: //数字、日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = fmt.format(cell.getDateCellValue()); //日期型
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue()); //数字
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: //布尔型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: //空白
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_ERROR: //错误
                cellValue = "error";
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = "error";
                break;
            default:
                cellValue = "error";
        }
        return cellValue;
    }

    public List<List<String>> read(String path) {
        System.out.println("Read from path: " + path);
        return read(path, 0);
    }
}
