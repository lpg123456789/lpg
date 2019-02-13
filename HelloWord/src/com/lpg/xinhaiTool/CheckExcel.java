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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 检查excel的格式问题
 * 有的时候是当作 CellType.BLANK 
 * 有时候是 CellType.String String类型可以配Integer，但是Integer类型不能配String
 * @author lpg 2018年12月5日
 */
public class CheckExcel {

	public static final String fileName = "F:\\xianxia\\配置模板表\\D-道具配置.xlsx";

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	// public static final String cellFlag="数据库表格名称";

	public static void main(String[] args) {
		System.out.println("开始读取配置，检测配置数据");
		System.out.println();
		File file = new File(ReadPropExcel.fileName);
		checkExcel(file);
		System.out.println();
		System.out.println("配置检测完毕");
	}

	/**
	 * 检查配置
	 * 
	 * @param file
	 */
	public static void checkExcel(File file) {
		try {
			// jxl提供的Workbook类
			Workbook wb = getWorkbok(file);
			Sheet configSheet = wb.getSheet("导表配置");
			if (configSheet == null) {
				System.err.println("没有导表配置");
				return;
			}
			List<String> resultList = getSheetList(configSheet);
			if (resultList.size() == 0) {
				System.err.println("导表配置没有配置相关表");
				return;
			}
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index < sheet_size; index++) {
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheetAt(index);
				if (!resultList.contains(sheet.getSheetName())) {
					continue;
				}
				Map<Integer, CellType> map = getMapType(sheet);
				int rowNumber = sheet.getPhysicalNumberOfRows(); // 第一行从0开始算
				System.out.println("正在解析表 " + sheet.getSheetName() + "行数是 " + rowNumber + " 定义的类型是 " + map.values());
				for (int i = 2; i <= 10; i++) {
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
						CellType cellType = cell.getCellTypeEnum();
						// 去掉空格的判断
						if (cellType.getCode() == CellType.BLANK.getCode()) {
							continue;
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						String str = cell.getStringCellValue();
						if (str.isEmpty()) {
							continue;
						}
						CellType flagType = map.get(j);
						if (flagType.getCode() == CellType.NUMERIC.getCode()) {// 是整数
							boolean flag = isFitInteger(str);
							if (!flag) {
								System.out.println("不符合 行数" + (i + 1) + " 列数" + (j + 1) + "值是 " + str);
							}
						}
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
	 * 是否匹配都是数字的正则
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFitInteger(String str) {
		return str.matches("\\d+");
	}

	/**
	 * 返回定义的枚举类型
	 * 
	 * @param sheet
	 * @return
	 */
	public static Map<Integer, CellType> getMapType(Sheet sheet) {
		Map<Integer, CellType> map = new HashMap<Integer, CellType>();
		Row row = sheet.getRow(0);
		int cellNum = row.getPhysicalNumberOfCells();
		for (int i = 0; i <= cellNum; i++) {
			Cell cell = row.getCell(i);
			if (cell == null) {
				continue;
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String value = cell.getStringCellValue();
			// System.out.println(value);
			String[] strArr = value.split("\\|");
			if (strArr.length == 1) {
				map.put(i, CellType.NUMERIC);
			} else {
				map.put(i, CellType.STRING);
			}
		}
		return map;
	}

	/**
	 * 返回需要验证的表格
	 * 
	 * @param configSheet
	 * @return
	 */
	public static List<String> getSheetList(Sheet configSheet) {
		List<String> result = new ArrayList<>();
		int rowNumber = configSheet.getPhysicalNumberOfRows();
		for (int i = 1; i <= rowNumber; i++) {
			Row row = configSheet.getRow(i);
			// 防止有空串的行
			if (row == null) {
				break;
			}
			Cell cell = row.getCell(0);
			if (cell == null) {
				break;
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			result.add(cell.getStringCellValue());
		}
		return result;
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
