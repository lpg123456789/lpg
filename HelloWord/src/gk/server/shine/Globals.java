package gk.server.shine;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import gk.server.shine.manager.exception.ServerStarupError;

/**
 * lpg
 * 2021年7月15日
 */
public class Globals {
	
	// 配置文件路径,conf下,发布后不可替换
    // 服务器信息配置
    public static final String SERVER_CONFIG = "conf/server.conf";

	
	// log config
    public static final String LOG_CONFIG_FILE = "config/log4j.xml";
    
    
    // 数据库
    public static final String GAME_DB_CONFIG = "config/game-db-config.xml";
    
    // 定时器
    public static final String QUARTS_PROP = "config/quartz.properties";
    
    
    public static final Properties properties = new Properties();
      

    
    public static String getConfigPath(String sFileName) {
        return System.getProperty("user.dir") + File.separator + sFileName;
    }
    
    /**
     * 初始化服务器配置
     */
    public static void init() {
    	try {
            InputStream in = new FileInputStream(getConfigPath(Globals.SERVER_CONFIG));
            Globals.properties.load(new InputStreamReader(in, "UTF-8"));
            in.close();
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new ServerStarupError();
        }

    }
	
}
