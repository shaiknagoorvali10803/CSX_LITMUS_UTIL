package com.csx.test.util.ExcelTests;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWrite {
    public static void main(String[] args) {
    File xlsxFile = new File("C:\\IntelliJProjects\\students.xlsx");

    //New students records to update in excel file
    Object[][] newStudents = {
            {"Rakesh sharma", "New Delhi", "rakesh.sharma@gmail.com", 22},
            {"Thomas Hardy", "London", "thomas.hardy@gmail.com", 25}
    };

        try {
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        for (Object[] student : newStudents) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            for (Object info : student) {
                Cell cell = row.createCell(columnCount++);
                if (info instanceof String) {
                    cell.setCellValue((String) info);
                } else if (info instanceof Integer) {
                    cell.setCellValue((Integer) info);
                }
            }
        }
        inputStream.close();
        FileOutputStream os = new FileOutputStream(xlsxFile);
        workbook.write(os);
        workbook.close();
        os.close();
        System.out.println("Excel file has been updated successfully.");

    } catch ( IOException e) {
        System.err.println("Exception while updating an existing excel file.");
        e.printStackTrace();
    }
}
}
