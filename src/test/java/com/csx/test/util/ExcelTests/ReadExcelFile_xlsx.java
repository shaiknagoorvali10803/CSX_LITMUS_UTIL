package com.csx.test.util.ExcelTests;

import com.csx.test.util.XLUtility_xlsx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ReadExcelFile_xlsx {
		public static void main(String... strings) throws IOException {
		System.out.println("+++++++++++ xls utility++++++++++++++++++");
		String path1 = "D:\\AUTOMATION_TESTING_LOGICS\\SampleExcel.xlsX";
		// Read Data
		String sheetName=null;
		sheetName ="login";
		int totalrows = XLUtility_xlsx.getRowCount(path1,0);
		int totalcols = XLUtility_xlsx.getCellCount(path1, 0,0);
		List<String> loginData = new ArrayList<String>();
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				loginData.add(XLUtility_xlsx.getCellData(path1, 0,i, j));
			}
		}
		System.out.println(loginData.toString());
		
		//Read Data From Excel Using the ColumnName
		Map<String, Integer> columns = XLUtility_xlsx.getColumns(path1, 0);
		System.out.println(columns.toString());
		
		System.out.println("++++++++++++data based on column name++++++++++++++++");
		List<String> columnData = new ArrayList<String>();
		for (int i = 1; i <= totalrows; i++) // 1
		{
			String cellData = XLUtility_xlsx.getCellData(path1, 0,i, columns.get("username"));
			if(!cellData.isEmpty()) {
			columnData.add(cellData);}
			System.out.println(cellData);
		}
		System.out.println("Size of the list is: "+ columnData.size());	
		
		
		System.out.println("++++++++++++total data ++++++++++++++++");	
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j <totalcols; j++) {
				String cellData = XLUtility_xlsx.getCellData(path1, 0,i, j);
				if(!cellData.isEmpty())
				System.out.println(cellData);		
			}
			
		}

	
	}

}
