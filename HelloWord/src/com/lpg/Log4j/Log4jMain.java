package com.lpg.Log4j;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jMain {

	public static void main(String[] args) {

		String projectTest = System.getProperty("user.dir");
		projectTest += "/src/config/Log4jConfig.properties";
		File file = new File(projectTest);
		if (!file.exists()) {
			System.out.println("文件不存在");
			return;
		}
		PropertyConfigurator.configure(projectTest);
		Logger logger = Logger.getLogger(Log4jMain.class);
		logger.debug(" debug ");
		logger.error(" error ");
		System.out.println("aaaaaa");
	}

}
