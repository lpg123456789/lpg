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

/**
 * 工具类：根据策划配的excel表格得出的一个工具，根据列进行查询
 * 编写一个工具快速查询道具的表格
 * @author lpg
 * 2018年11月22日
 */
public class ReadPropExcel {
	
	public static final String fileName="E:\\文档\\trunk\\配置模板表\\D-道具配置.xlsx";
	public static final String propId="40001";

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static void main(String[] args) {
		File file = new File(ReadPropExcel.fileName);
		ReadPropExcel.readExcel(file);
	}

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public static void readExcel(File file) {
		try {
			// jxl提供的Workbook类
			Workbook wb = getWorkbok(file);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index < sheet_size; index++) {
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheetAt(index);
				if(sheet.getRow(0)==null||sheet.getRow(1)==null) {
					continue;
				}
				//确认id在哪一列
				int flagCell=-1;
				Row zeroRow=sheet.getRow(0);
				for (int j = 0; j < zeroRow.getPhysicalNumberOfCells(); j++) {
					Cell cell = zeroRow.getCell(j);
					//隐藏列
					if(cell==null) {
						continue;
					}
					cell.setCellType(Cell.CELL_TYPE_STRING);
					System.out.println(cell.getStringCellValue());
					if(cell.getStringCellValue().equals("id")) {
						flagCell=j;
						break;
					}
				}
				if(flagCell==-1){
					continue;
				}
				// sheet.getRows()返回该页的总行数
				int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
				for (int i = 2; i <= rowNumber; i++) {
					Row row = sheet.getRow(i);
					if(row==null) {
						break;
					}
					
					Cell cell = row.getCell(flagCell);
					
					if(cell==null) {
						break;
					}
					
					Cell cell2 = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell2.setCellType(Cell.CELL_TYPE_STRING);
					//System.out.println(index+" "+i+" ");
					if(cell.getStringCellValue().equals(propId)) {
						System.out.println(index+" "+(i+1)+" 测试测试");
					}
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
