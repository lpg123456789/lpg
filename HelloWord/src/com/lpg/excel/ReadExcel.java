package com.lpg.excel;

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

public class ReadExcel {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static void main(String[] args) {
		ReadExcel obj = new ReadExcel();
		// 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
		File file = new File("E:/c.xls");
		System.out.println("list中的数据打印出来");
		obj.readExcel(file);
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
				int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
				for (int i = 0; i <= rowNumber; i++) {
					Row row = sheet.getRow(i);
					if(row==null) {
						break;
					}
					int physicalNumberOfCells=row.getPhysicalNumberOfCells();
					for (int j = 0; j < physicalNumberOfCells; j++) {
						Cell cell = row.getCell(j);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						System.out.print(cell.getStringCellValue() + " ");
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