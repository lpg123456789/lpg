package gk.common.shine.timer;

import gk.common.shine.command.ICommand;

/**
 * 定时事件的接口
 * 
 * @author zhangzhen
 * @date 2017年8月15日
 * @version 1.0
 */
public interface ITimerEvent extends ICommand {

    /**
     * 保持时间
     * 
     * @return
     */
    public abstract long remain();

    public abstract int getLoop();

    public abstract void setLoop(int loop);
}
