package com.csx.test.util.ExcelTests;

import com.csx.test.util.XLUtility_xls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ReadExcelFile_xls {
		public static void main(String... strings) throws IOException {
			System.out.println("+++++++++++ xls utility++++++++++++++++++");
			String path1 = "D:\\FRAMEWORK_DEVELOPMENT\\CSX_FRAMEWORK _TESTED\\UTILITY\\Testing\\sampledata.xls";
			// Read Data
			String sheetName=null;
			sheetName ="login";
			int totalrows = XLUtility_xls.getRowCount(path1,0);
			int totalcols = XLUtility_xls.getCellCount(path1,0, 1);
			List<String> loginData = new ArrayList();
			for (int i = 1; i <= totalrows; i++) // 1
			{
				for (int j = 0; j < totalcols; j++) // 0
				{
					loginData.add(XLUtility_xls.getCellData(path1,0, i, j));
				}
			}
			System.out.println(loginData.toString());
			
			//Read Data From Excel Using the ColumnName
			Map<String, Integer> columns = XLUtility_xls.getColumns(path1, 0);
			System.out.println(columns.toString());
			System.out.println("++++++++++++data based on column name++++++++++++++++");
			for (int i = 1; i <= totalrows; i++) // 1
			{
				String cellData = XLUtility_xls.getCellData(path1,0, i, columns.get("username"));
					System.out.println(cellData);
			}
			System.out.println("++++++++++++total data ++++++++++++++++");	
			for (int i = 1; i <= totalrows; i++) // 1
			{
				for (int j = 0; j <totalcols; j++) {
					String cellData = XLUtility_xls.getCellData(path1,0, i, j);
					if(!cellData.isEmpty())
					System.out.println(cellData);		
				}
				
			}

		
		}


}
