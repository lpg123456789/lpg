package flower.com.game.module.activity.task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flower.common.task.Task;


/**
 * 活动定时检查任务<br>
 * 定时刷新活动状态<br>
 * {@link ActivityConstant#TICK_INTERVAL}
 * 
 * @author hdh
 *
 */
public class ActivityTickTask implements Task {

    private final static Logger logger = LoggerFactory.getLogger(ActivityTickTask.class);

    private int counter;

    @Override
    public void execute() throws Exception {
    	
    }

}
