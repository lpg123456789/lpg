package com.lpg.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 热更工具类
 *
 * @author hdh
 */
public class HotfixUtil {

    private final static Logger logger = LoggerFactory.getLogger(HotfixUtil.class);

    /**
     * 需要热更的class文件存放的目录
     */
    private final static String HOTSWAP_CLASS_DIR_PATH = "hotfix/class";
    /**
     * 需要热更的json文件存放的目录
     */
    private final static String RELOAD_JSON_DIR_PATH = "hotfix/json";

    private static final String JSON_FILE_SUBFIX = ".json";
    /**
     * 执行的脚本文件
     */
    private final static String HOTFIX_SCRIPT_FILE = "hotfix/script.txt";
    /**
     * 执行的脚本文件
     */
    private final static String HOTFIX_SCRIPT_FILE_GROOVY = "hotfix/";

    /**
     * 热替换类文件
     *
     * @return
     */
    public static String hotswapClass() {
       return "";
    }

    /**
     * 重加载json配置
     *
     * @return
     */
    public static String reloadJson() {
       return "";
    }

    
    /**
     * 执行指定位置的脚本
     *
     * @return
     */
    public static String runScriptGroovy(String str) {
       return "";
    }
    

    /**
     * 数据修复<br>
     *
     * @return
     */
    public static String dataFix() {
        String value = "success";
        return value;
    }

    /**
     * 数据修复
     *
     * @throws IOException
     */
    private static void repairData() throws Exception {
       
    }


}
