package gk.cross.shine;

import java.io.File;
import java.util.Properties;


/**
 *  @author lpg
 *	2021年8月2日
 */
public class Globals {
	// 配置文件路径,conf下,发布后不可替换
	// 服务器信息配置
	public static final String SERVER_CONFIG = "conf/crossServer.conf";

	// log config
	public static final String LOG_CONFIG_FILE = "config/log4j.xml";

	//cross db
	public static final String CROSS_DB_CONFIG = "config/cross-db-config.xml";
	
	
	public static final Properties properties = new Properties();

	public static String getConfigPath(String sFileName) {
		return System.getProperty("user.dir") + File.separator + sFileName;
	}

	public static int getLanguageVersion() {
		return 0;
	}
}
