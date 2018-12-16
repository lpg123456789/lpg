package com.lpg.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 删除某一行的数据
 * 
 * @author lpg 2018年11月28日
 */
public class RemoveData {

	public static final String fileName = "E:\\测试删除2.xlsx";

	public static void main(String[] args) {
		// RemoveData.removeExcel();
		RemoveData.removeAndUpdate();
	}

	/**
	 * 清除内容，不上移
	 */
	public static void removeExcel() {
		try {
			File file = new File(fileName);
			Workbook wb = Excel.getWorkbok(file);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(1);
			sheet.removeRow(row);
			FileOutputStream os = new FileOutputStream(fileName);
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清除内容并且上移
	 */
	public static void removeAndUpdate() {
		try {
			File file = new File(fileName);
			Workbook wb = Excel.getWorkbok(file);
			Sheet sheet = wb.getSheetAt(0);
			sheet.shiftRows(1, 2, -1);
			FileOutputStream os = new FileOutputStream(fileName);
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试
	 */
	public static void removeAndUpdate2() {
		try {
			File file = new File(fileName);
			Workbook wb = Excel.getWorkbok(file);
			Sheet sheet = wb.getSheetAt(0);
			sheet.shiftRows(3, 6, -1);
			FileOutputStream os = new FileOutputStream(fileName);
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
