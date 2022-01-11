package flower.common.task;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import flower.common.utils.ThreadFactoryImpl;


/**
 * 任务管理器
 * 
 * @author hdh
 *
 */
public class TaskManager {

    private final static TaskManager instance = new TaskManager();

    public static TaskManager getInstance() {
        return instance;
    }

    private final ScheduledExecutorService executor;

    public TaskManager() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        ThreadFactory threadFactory = new ThreadFactoryImpl("task");
        this.executor = Executors.newScheduledThreadPool(cpuNum + 1, threadFactory);
    }

    public TaskManager(int poolSize, String threadPreName) {
        ThreadFactory threadFactory = new ThreadFactoryImpl(threadPreName);
        this.executor = Executors.newScheduledThreadPool(poolSize, threadFactory);
    }

    /**
     * 异步执行任务
     * 
     * @param task
     * @return
     */
    public Future<?> scheduleTask(Task task) {
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        TaskRunner taskRunner = new TaskRunner(task);
        return executor.submit(taskRunner);
    }

    /**
     * 延迟执行任务
     * 
     * @param task
     * @param delay
     * @return
     */
    public ScheduledFuture<?> scheduleTask(Task task, long delay) {
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        TaskRunner taskRunner = new TaskRunner(task);
        ScheduledFuture<?> future = executor.schedule(taskRunner, delay, TimeUnit.MILLISECONDS);
        return future;
    }

    /**
     * delay毫秒后 每period毫秒执行一次该任务<br>
     * 
     * @param task
     * @param delay
     * @param period
     * @return
     */
    public TaskHandle scheduleTask(Task task, long delay, long period) {
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        if (period <= 0) {
            throw new IllegalArgumentException("period too small:" + period);
        }
        TaskRunner taskRunner = new TaskRunner(task);
        // scheduleAtFixedRate 每period执行一次
        // scheduleWithFixedDelay 每执行一次 延迟period后再执行
        ScheduledFuture<?> scheduleTask = executor.scheduleAtFixedRate(taskRunner, delay, period, TimeUnit.MILLISECONDS);
        DefaultTaskHandle taskHandle = new DefaultTaskHandle(scheduleTask);
        return taskHandle;
    }

    /**
     * 添加每分钟执行一次的定时任务<br>
     * 调整到整分钟执行
     * 
     * @param task
     * @return
     */
    public TaskHandle scheduleMinuteTask(Task task) {
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        TaskRunner taskRunner = new TaskRunner(task);
        long now = System.currentTimeMillis();
        long nextMinute = TimeUnit.MILLISECONDS.toMinutes(now) + 1;
        long nextMinuteMillis = TimeUnit.MINUTES.toMillis(nextMinute);
        // 调整到整分钟时执行
        long delayTime = nextMinuteMillis - now;
        ScheduledFuture<?> scheduleTask = executor.scheduleAtFixedRate(taskRunner, delayTime, TimeUnit.MINUTES.toMillis(1), TimeUnit.MILLISECONDS);
        DefaultTaskHandle taskHandle = new DefaultTaskHandle(scheduleTask);
        return taskHandle;
    }

    /**
     * 添加定时任务<br>
     * 下1分钟0秒时执行第一次
     * 
     * @param task
     * @return
     */
    public TaskHandle scheduleTaskStartAtNextMinute(Task task, long period) {
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        if (period <= 0) {
            throw new IllegalArgumentException("period too small:" + period);
        }
        TaskRunner taskRunner = new TaskRunner(task);
        long now = System.currentTimeMillis();
        long nextMinute = TimeUnit.MILLISECONDS.toMinutes(now) + 1;
        long nextMinuteMillis = TimeUnit.MINUTES.toMillis(nextMinute);
        // 调整到整分钟时执行
        long delayTime = nextMinuteMillis - now;
        ScheduledFuture<?> scheduleTask = executor.scheduleAtFixedRate(taskRunner, delayTime, period, TimeUnit.MILLISECONDS);
        DefaultTaskHandle taskHandle = new DefaultTaskHandle(scheduleTask);
        return taskHandle;
    }

    /**
     * 添加定时任务<br>
     * 下个delayUnit开始时执行<br>
     * 不支持>=天的单位
     * 
     * @param task
     * @return
     */
    public TaskHandle scheduleTaskStartAtNextTimeUnit(Task task, long period, TimeUnit delayUnit) {
        if (task == null) {
            throw new NullPointerException("task is null");
        }
        if (period <= 0) {
            throw new IllegalArgumentException("period too small:" + period);
        }
        if (delayUnit == null) {
            throw new NullPointerException("delayUnit is null");
        }
        if (delayUnit == TimeUnit.DAYS) {
            throw new IllegalArgumentException("delayUnit no support TimeUnit.DAYS.");
        }
        TaskRunner taskRunner = new TaskRunner(task);
        long now = System.currentTimeMillis();
        long nextUnitBegin = delayUnit.convert(now, TimeUnit.MILLISECONDS) + 1;
        long nextUnitBeginMillis = delayUnit.toMillis(nextUnitBegin);
        long delayTime = nextUnitBeginMillis - now;
        ScheduledFuture<?> scheduleTask = executor.scheduleAtFixedRate(taskRunner, delayTime, period, TimeUnit.MILLISECONDS);
        DefaultTaskHandle taskHandle = new DefaultTaskHandle(scheduleTask);
        return taskHandle;
    }
}
