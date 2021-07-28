package gk.server.shine.server.timer;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import gk.common.shine.timer.TimerEvent;


/**
 * 角色统计信息
 * 
 * @author zhangzhen
 * @date 2017年8月14日
 * @version 1.0
 */
public class ServerInfoTimer extends TimerEvent {

    private final static long TICK_INTERVAL = TimeUnit.SECONDS.toMillis(5);

    public ServerInfoTimer() {
        super(-1, TICK_INTERVAL);
    }

    @Override
    public void action() {
      System.out.println("bbbbbb");
    }

}
