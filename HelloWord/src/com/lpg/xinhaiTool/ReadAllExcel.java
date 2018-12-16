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
 * 读取某个目录下的所有excel来找到某个值
 * @author lpg
 * 2018年12月13日
 */
public class ReadAllExcel {
	
	public static final String fileDir="F:\\xianxia\\配置模板表";
	public static final String value="2010005";

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	
	/**
	 * true:表示完全匹配
	 * false：表示模糊匹配
	 */
	public static final boolean flag=false;

	public static void main(String[] args) {
		System.out.println("开始读取配置，可能读取的行数和excel的行数不一样，因为excel表格的行或列有空串");
		System.out.println();
		File fileDir = new File(ReadAllExcel.fileDir);
		File[] fileList=fileDir.listFiles();
		for (File file : fileList) {
			if(file.getName().startsWith("~$")) {
				continue;
			}
			System.out.println();
			System.out.println("**************************************");
			System.out.println("解析excel "+file.getAbsolutePath());
			ReadAllExcel.readExcel(file);
		}
		System.out.println();
		System.out.println("配置读取完毕");
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
				int rowNumber = sheet.getPhysicalNumberOfRows(); // 第一行从0开始算
				System.out.println("正在解析表 "+sheet.getSheetName()+ "行数是 "+rowNumber);
				for (int i = 2; i <= rowNumber; i++) {
					//防止有空串的行
					Row row = sheet.getRow(i);
					if(row==null) {
						break;
					}
					//防止有空串的列			
					int cellNum=row.getPhysicalNumberOfCells();
					for (int j = 0; j <= cellNum; j++) {
						Cell cell = row.getCell(j);
						if(cell==null) {
							continue;
						}
						boolean printFlag=false;
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if(flag) {
							if(cell.getStringCellValue().equals(value)) {
								printFlag=true;
							}
						}else{
							if(cell.getStringCellValue().contains(value)) {
								printFlag=true;
							}
						}
						if(printFlag) {
							System.err.println("关键字位置在 "+sheet.getSheetName()+ "表 , 第 "+(i+1)+" 行, 第 "+(j+1)+"列 "+" 值   "+cell.getStringCellValue());
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
