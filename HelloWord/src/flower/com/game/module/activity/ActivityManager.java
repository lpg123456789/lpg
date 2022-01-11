package flower.com.game.module.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import flower.com.game.module.activity.defines.ActivityStatus;
import flower.com.game.module.activity.defines.ActivityType;
import flower.com.game.module.activity.task.ActivityTickTask;
import flower.common.component.service.AbstractService;
import flower.common.task.TaskHandle;
import flower.common.task.TaskManager;

public class ActivityManager extends AbstractService{

	
	private static ActivityManager instance = new ActivityManager();

    public static ActivityManager getInstance() {
        return instance;
    }

    /**
     * 活动类型,活动<br>
     * 含任意状态下的活动实例
     */
    private final ConcurrentMap<Integer, Activity> activityMap = new ConcurrentHashMap<>();

    private TaskHandle tickTask;

    @Override
    protected void onInitialize() throws Exception {
        for (Activity activity : activityMap.values()) {
            activity.initialize();
        }
        refreshConfig();
    }

    @Override
    protected void onStart() {
        for (Activity activity : activityMap.values()) {
            activity.start();
        }
        startTickTask();
    }

    @Override
    protected void onStop() {
        for (Activity activity : activityMap.values()) {
            activity.stop();
        }
        cancelTickTask();
    }

    @Override
    protected void onDestroy() {
        for (Activity activity : activityMap.values()) {
            activity.destroy();
        }
    }

    private void startTickTask() {
        cancelTickTask();
        tickTask = TaskManager.getInstance().scheduleTaskStartAtNextMinute(new ActivityTickTask(), 10);
    }

    private void cancelTickTask() {
        if (tickTask != null) {
            tickTask.cancel();
        }
        tickTask = null;
    }

    /**
     * 注册活动
     * 
     * @param activity
     */
    public void registerActivity(Activity activity) {
        ActivityType activityType = activity.getType();
        int type = activityType.getValue();
        Activity otherActivity = activityMap.put(type, activity);
        if (otherActivity == null) {
            return;
        }
        String clazzName = activity.getClass().getSimpleName();
        String otherClazzName = otherActivity.getClass().getSimpleName();
        // 重复注册
        logger.error("repeat register type[{}] clazz1[{}] clazz2[{}].", activityType, otherClazzName, clazzName);
    }

    public Map<Integer, Activity> getActivityMap() {
        return activityMap;
    }

    @SuppressWarnings("unchecked")
    public <T extends Activity> T getActivity(int type) {
        Activity activity = activityMap.get(type);
        if (activity == null) {
            return null;
        }
        return (T) activity;
    }

    public <T extends Activity> T getActivity(ActivityType activityType) {
        return getActivity(activityType.getValue());
    }

    public ActivityStatus getActivityStatus(int type) {
        Activity activity = activityMap.get(type);
        if (activity == null) {
            return ActivityStatus.END;
        }
        return activity.getStatus();
    }

    public ActivityStatus getActivityStatus(ActivityType activityType) {
        int type = activityType.getValue();
        return getActivityStatus(type);
    }

    public <T extends Activity> List<T> getActivitiesByClass(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Activity activity : activityMap.values()) {
            if (!clazz.isInstance(activity)) {
                continue;
            }
            T tmpActivity = clazz.cast(activity);
            list.add(tmpActivity);
        }
        return list;
    }

    public TaskHandle getTickTask() {
        return tickTask;
    }

    public void setTickTask(TaskHandle tickTask) {
        this.tickTask = tickTask;
    }

    public void refreshConfig() {
        for (Activity activity : activityMap.values()) {
            activity.refreshConfig();
        }
    }
    
}
