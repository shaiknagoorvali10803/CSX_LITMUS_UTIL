package com.csx.test.util.ExcelTests;

import com.csx.test.util.XLUtility_xlsx;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelReader_xlsx {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        System.out.println("+++++++++++ xls utility++++++++++++++++++");
        String path1 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\sampledata_ReadMap.xlsx";

        Map<String, Integer> columns = XLUtility_xlsx.getColumns(path1, 0);
        int totalRows=XLUtility_xlsx.getRowCount(path1,0);
        int colCount=XLUtility_xlsx.getCellCount(path1,0,1);
        System.out.println("totalRows ==>"+totalRows);
        System.out.println("totalColumns ==>"+colCount);
        System.out.println(columns);

        System.out.println("+++++++++++ xls utility reading Data Based on List++++++++++++++++++");

        String path2 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\sampledata.xlsx";
        int totalrows = XLUtility_xlsx.getRowCount(path2,0);
        int totalcols = XLUtility_xlsx.getCellCount(path2,0, 1);
        List<String> loginData = new ArrayList();
        for (int i = 1; i <= totalrows; i++) // 1
        {
            for (int j = 0; j < totalcols; j++) // 0
            {
                loginData.add(XLUtility_xlsx.getCellData(path2,0, i, j));
            }
        }
        System.out.println(loginData.toString());


        System.out.println("+++++++++++ xls utility reading Data Based on List++++++++++++++++++");
        Map<String, Integer> columns1 = XLUtility_xlsx.getColumns(path2, 0);
        System.out.println("++++++++++++data based on column name++++++++++++++++");
        for (int i = 1; i <= totalrows; i++) // 1
        {
            String cellData = XLUtility_xlsx.getCellData(path2,0, i, columns.get("username"));
            System.out.println(cellData);
        }
        System.out.println("++++++++++++total data ++++++++++++++++");
        for (int i = 1; i <= totalrows; i++) // 1
        {
            for (int j = 0; j <totalcols; j++) {
                String cellData = XLUtility_xlsx.getCellData(path2,0, i, j);
                if(!cellData.isEmpty())
                    System.out.println(cellData);
            }
        }
        String path3 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\writetest.xlsx";
        XLUtility_xlsx.setCellData(path3,0,1,1,"TESTWRITE");
        XLUtility_xlsx.fillGreenColor(path3,0,3,3);
        XLUtility_xlsx.fillRedColor(path3,0,2,2);

    }
}
