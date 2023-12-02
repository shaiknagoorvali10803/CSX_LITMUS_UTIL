package com.csx.test.util.ExcelTests;

import com.csx.test.util.XLUtility_xls;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelReader_xls {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        System.out.println("+++++++++++ xls utility++++++++++++++++++");
        String path1 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\sampledata_ReadMap.xls";

        Map<String, Integer> columns = XLUtility_xls.getColumns(path1, 0);
        int totalRows=XLUtility_xls.getRowCount(path1,0);
        int colCount=XLUtility_xls.getCellCount(path1,0,1);
        System.out.println("totalRows ==>"+totalRows);
        System.out.println("totalColumns ==>"+colCount);
        System.out.println(columns);

        System.out.println("+++++++++++ xls utility reading Data Based on List++++++++++++++++++");

        String path2 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\sampledata.xls";
        int totalrows = XLUtility_xls.getRowCount(path2,0);
        int totalcols = XLUtility_xls.getCellCount(path2,0, 1);
        List<String> loginData = new ArrayList();
        for (int i = 1; i <= totalrows; i++) // 1
        {
            for (int j = 0; j < totalcols; j++) // 0
            {
                loginData.add(XLUtility_xls.getCellData(path2,0, i, j));
            }
        }
        System.out.println(loginData.toString());


        System.out.println("+++++++++++ xls utility reading Data Based on List++++++++++++++++++");
        Map<String, Integer> columns1 = XLUtility_xls.getColumns(path2, 0);
        System.out.println("++++++++++++data based on column name++++++++++++++++");
        for (int i = 1; i <= totalrows; i++) // 1
        {
            String cellData = XLUtility_xls.getCellData(path2,0, i, columns.get("username"));
            System.out.println(cellData);
        }
        System.out.println("++++++++++++total data ++++++++++++++++");
        for (int i = 1; i <= totalrows; i++) // 1
        {
            for (int j = 0; j <totalcols; j++) {
                String cellData = XLUtility_xls.getCellData(path2,0, i, j);
                if(!cellData.isEmpty())
                    System.out.println(cellData);
            }
        }
        String path3 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\writetest.xls";
        XLUtility_xls.setCellData(path3,0,1,1,"TESTWRITE");
        XLUtility_xls.fillGreenColor(path3,0,3,3);
        XLUtility_xls.fillRedColor(path3,0,2,2);

    }
}
