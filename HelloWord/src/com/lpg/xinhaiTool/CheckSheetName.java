package com.lpg.xinhaiTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 通过sheetName查找那张表
 * 
 * @author lpg 2018年12月14日
 */
public class CheckSheetName {

	public static final String fileDir = "F:\\xianxia\\配置模板表";
	public static final String sheetName = "导表配置";
	public static final String keyWord = "充值返利";

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static void main(String[] args) {
		System.out.println("开始读取配置，读取配置，检查数据库表所在的excel文件");
		File fileDir = new File(ReadAllExcel.fileDir);
		System.out.println();
		File[] fileList = fileDir.listFiles();
		for (File file : fileList) {
			if (file.getName().startsWith("~$")) {
				continue;
			}
			System.out.println("解析excel " + file.getAbsolutePath());
			CheckSheetName.readExcel(file);
		}
		System.out.println();
		System.out.println("配置读取完毕");
	}

	public static void readExcel(File file) {
		try {
			// jxl提供的Workbook类
			Workbook wb = getWorkbok(file);
			Sheet sheet = wb.getSheet(sheetName);
			if (sheet != null) {
				int rowNumber = sheet.getPhysicalNumberOfRows(); // 第一行从0开始算
				for (int i = 0; i <= rowNumber; i++) {
					// 防止有空串的行
					Row row = sheet.getRow(i);
					if (row == null) {
						break;
					}
					// 防止有空串的列
					int cellNum = row.getPhysicalNumberOfCells();
					for (int j = 0; j <= cellNum; j++) {
						Cell cell = row.getCell(j);
						if (cell == null) {
							continue;
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if (cell.getStringCellValue().equals(keyWord)) {
							System.err.println("文件路径在" + file.getAbsolutePath() + " 关键字是 " + cell.getStringCellValue());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(File file) throws IOException {
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel&nbsp;2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}
}
