package com.roma.helper.excel;

import com.roma.helper.Helper;

import java.util.List;

/**
 * Created by roma on 04/05/2017.
 */
public class ExcelHelper implements Helper {

    private static ExcelReader reader;
    private static ExcelWriter writer;

    private ExcelHelper() {}

    public static ExcelReader getReader() {
        if (reader == null) {
            synchronized (ExcelHelper.class) {
                if (reader == null) {
                    reader = new ExcelReader();
                }
            }
        }
        return reader;
    }

    public static ExcelWriter getWriter() {
        if (writer == null) {
            synchronized (ExcelHelper.class) {
                if (writer == null) {
                    writer = new ExcelWriter();
                }
            }
        }
        return writer;
    }

    public static void main(String[] args) {
        List<List<String>> result = ExcelHelper.getReader().read("/Users/roma/Desktop/原料&产品.xlsx");
        result.forEach(element -> {
            System.out.println(element);
        });
        ExcelHelper.getWriter().write("/Users/roma/Desktop/原料&产品.xlsx");
    }
}
