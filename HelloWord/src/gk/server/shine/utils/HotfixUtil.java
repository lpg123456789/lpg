package gk.server.shine.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
       return "hotswapClass";
    }

    /**
     * 重加载json配置
     *
     * @return
     */
    public static String reloadJson() {
    	 return "reloadJson";
    }

    /**
     * 执行指定位置的脚本
     *
     * @return
     */
    public static String runScriptGroovy(String str) {
        logger.warn("runScriptGroovy start.");
        return "runScriptGroovy";
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

}
