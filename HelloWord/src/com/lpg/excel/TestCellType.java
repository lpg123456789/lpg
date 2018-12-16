package com.lpg.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 测试单元格的类型 是空的 单元格有时候是blank类型， 有时候是String类型
 * 
 * @author lpg 2018年12月6日
 */
public class TestCellType {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	public static final String fileName = "E:/D-道具配置.xlsx";

	public static void main(String[] args) {
		TestCellType obj = new TestCellType();
		File file = new File(fileName);
		obj.readExcel(file);
	}

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public void readExcel(File file) {
		try {
			// jxl提供的Workbook类
			Workbook wb = getWorkbok(file);
			// 每个页签创建一个Sheet对象
			Sheet sheet = wb.getSheet("装备");
			// sheet.getRows()返回该页的总行数
			int rowNumber = sheet.getPhysicalNumberOfRows();
			// System.out.println(sheet.getSheetName()+" "+rowNumber);
			for (int i = 0; i <= rowNumber; i++) {
				// 防止有空串的行
				Row row = sheet.getRow(i);
				if (row == null) {
					break;
				}
				// 防止有空串的列
				int cellNum = row.getPhysicalNumberOfCells();
				System.err.println(cellNum);
				for (int j = 0; j <= cellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null) {
						continue;
					}
					CellType cellType = cell.getCellTypeEnum();
					System.err.println(sheet.getSheetName() + "表 , 第 " + (i + 1) + " 行, 第 " + (j + 1) + "列 " + " 类型标志是" + cellType.getCode());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
