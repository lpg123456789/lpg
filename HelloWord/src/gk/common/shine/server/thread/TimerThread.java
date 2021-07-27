package gk.common.shine.server.thread;

import java.util.Iterator;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import gk.common.shine.timer.ITimerEvent;

public class TimerThread extends Timer {

    // 所有需要处理的事件
    private Queue<ITimerEvent> events = new LinkedBlockingQueue<>();

    // 执行命令线程
    private ServerThread main;
    // 事件监控任务
    private TimerTask task;

    public TimerThread(ServerThread main) {
        super(main.threadName + "-Timer");
        this.main = main;

    }

    /**
     * 开始监控所有的事件
     */
    public void start() {
        this.task = new TimerTask() {

            @Override
            public void run() {
                Iterator<ITimerEvent> it = TimerThread.this.events.iterator();
                while (it.hasNext()) {
                    ITimerEvent event = it.next();
                    int loop = event.getLoop();
                    if (event.remain() <= 0l) {
                        if (loop > 0) {
                            loop = loop - 1;
                        }
                        // 该方法会延迟下次添加到执行队列的时间
                        event.setLoop(loop);
                        // 添加事件到执行队列
                        TimerThread.this.main.addCommand(event);
                    }
                    if (loop == 0) {// 达到执行次数。移除这个事件
                        it.remove();
                    }
                }
            }
        };
        // 立即执行任务。每个任务间隔heart毫秒
        schedule(this.task, 0L, this.main.getHeart());
    }

    /**
     * 终止此计时器，丢弃所有当前已安排的任务。这不会干扰当前正在执行的任务（如果存在）。
     * 
     * @param flag
     */
    public void stop(boolean flag) {
        this.events.clear();
        if (this.task != null) {
            this.task.cancel();
        }
        cancel();
    }

    /**
     * 添加计时器处理事件
     * 
     * @param event
     */
    public void addTimerEvent(ITimerEvent event) {
        this.events.add(event);

    }

    /**
     * 移除计时器事件
     * 
     * @param event
     */
    public void removeTimerEvent(ITimerEvent event) {
        this.events.remove(event);

    }
}
