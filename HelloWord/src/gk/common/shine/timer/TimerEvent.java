package gk.common.shine.timer;

public abstract class TimerEvent implements ITimerEvent {
    /**
     * 无限循环
     */
    public final static int INFINITE_LOOP = -1;
    /**
     * 结束时间<br>
     * 即下次执行的时间
     */
    private long end;

    // 可循环执行次数 -1为永久执行
    private int loop;
    // 执行间隔时间
    private long delay;
    private long createTime;

    protected TimerEvent(long end) {
        this.end = end;
        this.loop = 1;
    }

    protected TimerEvent(int loop, long delay) {
        this.loop = loop;
        this.delay = delay;
        this.end = (System.currentTimeMillis() + delay);
    }

    @Override
    public long remain() {
        return this.end - System.currentTimeMillis();
    }

    @Override
    public void execute() {
        action();
    }

    protected abstract void action();

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public int getLoop() {
        return this.loop;
    }

    @Override
    public void setLoop(int loop) {
        this.loop = loop;
        this.end = (System.currentTimeMillis() + this.delay);
    }

    public long getDelay() {
        return this.delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}