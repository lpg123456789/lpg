package gk.common.shine.utils;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 默认的未捕获错误的处理类<br>
 * 只打印严重报错日志 不做其他处理
 * 
 * @author hdh
 *
 */
public class DefaultUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static DefaultUncaughtExceptionHandler instance = new DefaultUncaughtExceptionHandler();

    public static DefaultUncaughtExceptionHandler getInstance() {
        return instance;
    }

//    private final static Logger logger = LoggerFactory.getLogger(DefaultUncaughtExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //FatalErrorLogUtil.logger.error("thread[{}] uncaughtException.", t.getName(), e);
    }

}
