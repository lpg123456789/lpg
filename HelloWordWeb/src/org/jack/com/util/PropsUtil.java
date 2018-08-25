package org.jack.com.util;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * Created by jack on 2015/12/26.
 * 属性文件工具类
 */
public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
 
    /*
    * 加载属性文件
    *
    * */
    public static Properties loadProps(String fileName) {
        Properties properties = null;
        BufferedReader bufferedReader = null;
        try {
        	bufferedReader = new BufferedReader(new FileReader(fileName));
            properties = new Properties();
            properties.load(bufferedReader);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (bufferedReader != null) {
                try {
                	bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return properties;
    }
    
    /*
    * 获取字符型属性（默认为空字符串）
    *
    * */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }
 
    /*
    * 获取字符型属性（可指定默认值）
    * */
    public static String getString(Properties props, String key, String
            defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
 
    /*
    * 获取数值类型属性（默认为0）
    * */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }
 
    /*
    * 获取数值类型属性（可指定默认值）
    * */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }
 
    /*
    * 获取布尔型属性（默认值为false）
    * */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }
 
    /*
    * 获取布尔型属性（可指定默认值）
    * */
    public static boolean getBoolean(Properties props, String key, Boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
