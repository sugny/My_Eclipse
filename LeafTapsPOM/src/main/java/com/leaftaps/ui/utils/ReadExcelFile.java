package com.leaftaps.ui.utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	 public static String[][] readExcel(String excelFileName) throws IOException {

		// open book
		XSSFWorkbook workBook = new XSSFWorkbook("./testData/"+ excelFileName +".xlsx");

		// select sheet
		XSSFSheet sheet = workBook.getSheetAt(0);

		// find row size
		int rowSize = sheet.getLastRowNum();
		// find column size
		int columnSize = sheet.getRow(0).getLastCellNum();
		//System.out.println(rowSize);
		// iterate
		String[][] data = new String[rowSize][columnSize];
		for (int i = 1; i <=rowSize; i++) {

			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < columnSize; j++) {

				XSSFCell cell = row.getCell(j);
				String value = cell.getStringCellValue();
				data[i-1][j] = value;
				System.out.println(data[i-1][j]);
			}
		}
		workBook.close();
		return data;
	}
	 
}
