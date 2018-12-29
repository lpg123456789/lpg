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
 * 工具类：根据策划配的excel表格得出的一个工具，根据列进行查询
 * 编写一个工具快速查询道具的表格
 * @author lpg
 * 2018年11月22日
 */
public class ReadPropExcel {
	
	//查询的表
	public static final String fileName="F:\\xianxia\\配置模板表\\D-道具配置.xlsx";
	//查询哪一列
	public static final String value="功德";
	//查询该列的标签，也就是第0行
	public static final String cellFlag="name|string";

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	public static void main(String[] args) {
		System.out.println("开始读取配置，可能读取的行数和excel的行数不一样，因为excel表格的行或列有空串");
		System.out.println();
		File file = new File(ReadPropExcel.fileName);
		ReadPropExcel.readExcel(file);
	}
	
	/**
	 * 根据flagInfo 获取flag的标志
	 * @param sheet
	 * @return
	 */
	public static int getFlagCellIndex(Sheet sheet) {
		int flagCell=-1;
		//必须有这两行，没有证明美欧数据
		if(sheet.getRow(0)==null||sheet.getRow(1)==null) {
			return flagCell;
		}
		Row zeroRow=sheet.getRow(0);
		for (int j = 0; j < zeroRow.getPhysicalNumberOfCells(); j++) {
			Cell cell = zeroRow.getCell(j);
			//防止有空串的隐藏列
			if(cell==null) {
				continue;
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			//System.out.println(cell.getStringCellValue());
			if(cell.getStringCellValue().equals(cellFlag)){
				flagCell=j;
				break;
			}
		}
		return flagCell;
	}

	// 注意：System.out.println内部实现
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
				int flagCell=getFlagCellIndex(sheet);
				if(flagCell==-1) {
					continue;
				}
				int rowNumber = sheet.getPhysicalNumberOfRows(); // 第一行从0开始算
				System.out.println("正在解析表 "+sheet.getSheetName()+ "行数是 "+rowNumber);
				for (int i = 2; i <= rowNumber; i++) {
					//防止有空串的行
					Row row = sheet.getRow(i);
					if(row==null) {
						break;
					}
					//防止有空串的列
					Cell cell = row.getCell(flagCell);
					if(cell==null) {
						break;
					}
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if(cell.getStringCellValue().equals(value)) {
						System.err.println("位置在 "+sheet.getSheetName()+ "表 , 第 "+(i+1)+" 行");
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
