package gk.server.shine.server.task;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Iterator;

/**
 * 每日刷新任务
 *
 * @author zhangzhen
 * @date 2017年8月15日
 * @version 1.0
 */
public class EverydayReTask implements Job {
    Logger log = Logger.getLogger(EverydayReTask.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
        	log.info("EverydayReTask EverydayReTask EverydayReTask");
        } catch (Exception e) {
            log.error("EverydayRe error", e);
        }
    }
    
}
