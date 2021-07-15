/**
 * admin
 */
package com.lpg.myFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * lpg
 * 2020年1月12日
 */
public class ReadLine {

	public static void main(String[] args) {
		
		String a="insert";
		String reg = "/([/][^/]+)$(";
		
		File readFile = new File("E:\\server\\1.9.0\\logic\\src\\com\\game\\log\\sqlmap\\AccLevlRecordLog.xml");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(readFile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {// BufferedReader有readLine()，可以实现按行读取
				String b=tempString.trim().toLowerCase();
				if(b.startsWith("insert")) {
					System.out.println(b);
					String blueurl = a.replace(reg, "");

					System.out.println(blueurl);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
