package com.lpg.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * 测试excel表格的隐藏行和隐藏列
 * @author lpg
 * 2018年11月22日
 */
public class TestExcel {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	public static final String fileName="F:\\xianxia\\a.xlsx";
	//F:\\xianxia\\配置模板表\\D-道具配置.xlsx

	public static void main(String[] args) {
		TestExcel obj = new TestExcel();
		File file = new File(fileName);
		obj.testData(file);
	}
	
	
	/**
	 * 测试精度问题
	 */
	public void testData(File file) {
		try {
			// jxl提供的Workbook类
			XSSFWorkbook wb = (XSSFWorkbook) getWorkbok(file);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index < sheet_size; index++) {
				// 每个页签创建一个Sheet对象
				XSSFSheet sheet = wb.getSheetAt(index);
				// sheet.getRows()返回该页的总行数
				int rowNumber = sheet.getPhysicalNumberOfRows();
				for (int i = 1; i <= rowNumber; i++) {
					XSSFRow row = sheet.getRow(i);
					if (null == row) {
						continue;
					}
					
					for (int j = 0; j < 2; j++) {
						XSSFCell cell = row.getCell(j);
						Object data;
						int cellType = cell.getCellType();
						if (cellType == Cell.CELL_TYPE_STRING) {
							data = cell.getStringCellValue();
							String sd = data.toString();
							if (sd.lastIndexOf(".0") >= 0 && sd.lastIndexOf(".0") == sd.length() - 2) {
								sd = sd.substring(0, sd.lastIndexOf(".0"));
								data = Integer.parseInt(sd);
							}
						} else if (cellType == Cell.CELL_TYPE_NUMERIC) {
							double dd = cell.getNumericCellValue();
							data = new BigDecimal(dd);
							// String sd = data.toString();
							// if (sd.lastIndexOf(".0") >= 0 && sd.lastIndexOf(".0") == sd.length()-2) {
							// sd = sd.substring(0, sd.lastIndexOf(".0"));
							// data = Integer.parseInt(sd);
							// }
						} else if (cellType == Cell.CELL_TYPE_BLANK) {
							data = "";
						} else if (cellType == Cell.CELL_TYPE_FORMULA) {
							try {
								String sd = String.valueOf(cell.getNumericCellValue());
								if (sd.lastIndexOf(".0") >= 0 && sd.lastIndexOf(".0") == sd.length() - 2) {
									sd = sd.substring(0, sd.lastIndexOf(".0"));
								}
								data = sd;
							} catch (IllegalStateException e) {
								data = String.valueOf(cell.getRichStringCellValue());
							}
						} else {
							throw new RuntimeException("未定义的类型：" + cellType);
						}
						System.out.print("    "+data+" ");
					}
					System.out.println();
				}

				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public void readExcel(File file) {
		try {
			// jxl提供的Workbook类
			Workbook wb = getWorkbok(file);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index < sheet_size; index++) {
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheetAt(index);
				// sheet.getRows()返回该页的总行数
				int rowNumber = sheet.getPhysicalNumberOfRows();
				System.out.println(sheet.getSheetName()+" "+rowNumber);
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
