package com.csx.test.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class XLUtility_xls {
    public static final Logger LOGGER = LoggerFactory.getLogger(XLUtility_xls.class);
    public static final String ERROR_MSG = "Some error has occurred while performing operation::{}";
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static HSSFWorkbook workbook;
    public static HSSFSheet sheet;
    public static HSSFRow row;
    public static HSSFCell cell;
    public static CellStyle style;
    public static Map<String, Integer> columns = new HashMap<>();

    public XLUtility_xls() {
    }

    public static int getRowCount(String filePath,int sheetIndex) throws IOException {
        fi = new FileInputStream(filePath);
        workbook = new HSSFWorkbook(fi);
        sheet = workbook.getSheetAt(sheetIndex);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }


    public static int getCellCount(String filePath,int sheetIndex, int rownum) throws IOException {
        fi = new FileInputStream(filePath);
        workbook = new HSSFWorkbook(fi);
        sheet = workbook.getSheetAt(sheetIndex);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public static Map<String, Integer> getColumns(String ExcelPath, int sheetIndex) throws IOException {
        File f = new File(ExcelPath);
        if (!f.exists()) {
            f.createNewFile();
            System.out.println("File doesn't exist, so created!");
        }
        fi = new FileInputStream(ExcelPath);
        workbook = new HSSFWorkbook(fi);
        sheet = workbook.getSheetAt(sheetIndex);
        // adding all the column header names to the map 'columns'
        sheet.getRow(0).forEach(cell -> {
            columns.put(cell.getStringCellValue(), cell.getColumnIndex());
        });
        return columns;
    }

    public static String getCellData(String filePath,int sheetIndex, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(filePath);
        workbook = new HSSFWorkbook(fi);
        sheet = workbook.getSheetAt(sheetIndex);
        row = sheet.getRow(rownum);
        try {
			cell=row.getCell(colnum);}
			catch (Exception e) {
				cell =null;
			}

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String
            // regardless of the cell type.
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public static void setCellData(String filePath,int sheetIndex, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(filePath);
        if (!xlfile.exists()) // If file not exists then create new file
        {
            workbook = new HSSFWorkbook();
            fo = new FileOutputStream(filePath);
            workbook.write(fo);
        }
        fi = new FileInputStream(filePath);
        workbook = new HSSFWorkbook(fi);
        if (workbook.getSheetAt(sheetIndex)==null) {
            workbook.createSheet();
        }
        sheet = workbook.getSheetAt(sheetIndex);
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(filePath);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }


    public static void fillGreenColor(String filePath,int sheetIndex, int rownum, int colnum) throws IOException {
        FileInputStream fi = new FileInputStream(filePath);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheetAt(sheetIndex);
        row = sheet.createRow(rownum);
        cell = row.createCell(colnum);
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        FileOutputStream fo = new FileOutputStream(filePath);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }


    public static void fillRedColor(String filePath,int sheetIndex, int rownum, int colnum) throws IOException {
        FileInputStream fi = new FileInputStream(filePath);
        workbook=new HSSFWorkbook(fi);
        sheet=workbook.getSheetAt(sheetIndex);
        row = sheet.createRow(rownum);
        cell = row.createCell(colnum);
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        FileOutputStream fo = new FileOutputStream(filePath);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }


    public static List<Map<String, String>> getData(String excelFilePath, int sheetIndex) throws InvalidFormatException, IOException {
        Sheet sheet = WorkbookFactory.create(new File(excelFilePath)).getSheetAt(sheetIndex);
        Row row;
        int totalRow = sheet.getPhysicalNumberOfRows();
        List<Map<String, String>> excelRows = new ArrayList<>();
        int totalColumn = sheet.getRow(0).getLastCellNum();
        int setCurrentRow = 1;
        for (int currentRow = setCurrentRow; currentRow < totalRow; currentRow++) {
            row = sheet.getRow(sheet.getFirstRowNum() + currentRow);
            LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
            for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
                columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
            }
            excelRows.add(columnMapdata);
        }
        return excelRows;
    }

    public static LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
        LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
        Cell cell;
        if (row == null) {
            if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
                String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();
                columnMapdata.put(columnHeaderName, "");
            }
        } else {
            cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() == CellType.STRING) {
                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, cell.getStringCellValue());
                }
            } else if (cell.getCellType() == CellType.NUMERIC) {
                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
                }
            } else if (cell.getCellType() == CellType.BLANK) {
                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, "");
                }
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
                }
            } else if (cell.getCellType() == CellType.ERROR) {
                if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                        .getCellType() != CellType.BLANK) {
                    String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
                    columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
                }
            }
        }
        return columnMapdata;
    }
}
