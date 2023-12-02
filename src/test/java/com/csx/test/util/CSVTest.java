package com.csx.test.util;

import com.csx.test.util.CSVUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVTest {
    @Test
    public void readCSVData(){
        List<String> csvColumnData = CSVUtil.getCSVColumnData("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\ExcelData.csv", 3);
        System.out.println(csvColumnData);
        List<List<String>> csvColumnDataList = CSVUtil.getCSVData("D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\ExcelData.csv", "");
        System.out.println(csvColumnDataList);
    }
}
