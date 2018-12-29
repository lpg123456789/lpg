package com.lpg.xinhaiTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 获取excel某一列的数据，用于和其它数据表进行比较 
 * 例如：某一张sheet表配置的道具id，但是道具总表里面没有该道具id
 * @author lpg 2018年11月28日
 */
public class ReadColExcel {

	/**
	 * 源excel
	 */
	private static final String SorceFileName = "F:\\xianxia\\配置模板表\\S-商店配置.xlsx";
	private static String sheetName = "竞技商店";
	private static int colIndex = 2;

	/**
	 * 目标excel
	 */
	private static final String targetFileName = "F:\\xianxia\\配置模板表\\D-道具配置.xlsx";
	public static final String cellFlag="id";

	public static void main(String[] args) {
		File sorcefile = new File(ReadColExcel.SorceFileName);
		List<String> propList = ReadColExcel.readSourceExcel(sorcefile);
		System.out.println("源文件数量为" + propList.size());
		File targetfile = new File(ReadColExcel.targetFileName);
		Map<String,Integer> map=readTargetExcel(targetfile);
		System.out.println("目标数量为" + map.size());
		
		int num=0;
		for (String string : propList) {
			if(!map.containsKey(string)) {
				num+=1;
				//System.out.println("被删除的有 "+string);
			}else {
				//System.out.println("包含的装备有 "+string);
			}		
		}
		System.out.println("一共删除了装备数量为 "+num);
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

	/**
	 * 读取目标的excel文件
	 * @param file
	 * @return
	 */
	public static Map<String,Integer> readTargetExcel(File file) {
		Map<String,Integer> map=new HashMap<>();
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
					map.put(cell.getStringCellValue(), 1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	
	
	
	/**
	 * 读取excel文件特定列
	 * @param file
	 * @return
	 */
	public static List<String> readSourceExcel(File file) {
		List<String> propList = new ArrayList<>();
		try {
			// jxl提供的Workbook类
			Workbook wb = getWorkbok(file);
			Sheet sheet = wb.getSheet(ReadColExcel.sheetName);
			int rowNumber = sheet.getPhysicalNumberOfRows(); // 第一行从0开始算
			System.out.println("正在源excel " + sheet.getSheetName() + "行数是 " + rowNumber);
			for (int i = 2; i <= rowNumber; i++) {
				// 防止有空串的行
				Row row = sheet.getRow(i);
				if (row == null) {
					break;
				}
				// 防止有空串的列
				Cell cell = row.getCell(colIndex);
				if (cell == null) {
					break;
				}
				cell.setCellType(Cell.CELL_TYPE_STRING);
				propList.add(cell.getStringCellValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propList;
	}

	/**
	 * 判断Excel的版本,获取Workbook
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
	
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
}
