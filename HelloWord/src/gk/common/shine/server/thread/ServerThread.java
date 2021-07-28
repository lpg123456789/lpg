package gk.common.shine.server.thread;

import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;

import gk.common.shine.command.ICommand;
import gk.common.shine.timer.ITimerEvent;

public class ServerThread extends Thread {

	private Logger log = Logger.getLogger(ServerThread.class);

	private LinkedBlockingDeque<ICommand> command_queue = new LinkedBlockingDeque<ICommand>();

	/**
	 * 事件处理类
	 */
	private TimerThread timer;

	// 线程名称
	protected String threadName;
	/**
	 * 事件处理执行的时间间隔
	 */
	private int heart;
	/**
	 * 是否关闭线程
	 */
	private boolean stop;

	public ServerThread(String threadName, int heart) {

		this.threadName = threadName;
		this.heart = heart;

		// 执行时间间隔大于0的情况创建一个timer 事件管理线程
		if (this.getHeart() > 0) {
			this.timer = new TimerThread(this);
		}

		// 未捕获到异常的处理方式
		setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				if (ServerThread.this.timer != null) {
					ServerThread.this.timer.stop(true);// 停止timer
				}
			}
		});
		log.info("[" + threadName + "]Thread create");
	}

	@Override
	public void run() {
		log.info("[" + threadName + "]Thread start");
		
		 // 间隔时间大于0 时间管理线程开始
        if (this.getHeart() > 0 && this.timer != null) {
            this.timer.start();
        }

		this.stop = false;
		// 循环执行的次数
		int loop = 0;
		while (!this.stop) {
			ICommand command = (ICommand) this.command_queue.poll();
			if (command == null) {// 没有指令则等待添加。
				try {
					synchronized (this) {
						loop = 0;
						// this.processingCompleted = true;
						wait();
					}
				} catch (Exception e) {
					this.log.error(e, e);
				}
				continue;
			}
			// 执行
			try {
				loop++;
				// 开始时间
				long start = System.currentTimeMillis();
				command.execute();// 执行命令
				long costTime = System.currentTimeMillis() - start;
				if (costTime > 50l) {// 单个执行大于50毫秒。提示
					this.log.warn(getName() + "-->" + command.getClass().getSimpleName() + " run:" + costTime);
				}
				if (loop > 1000) {// 循环执行大于1000.休息1毫秒
					loop = 0;
					Thread.sleep(1L);
				}
			} catch (Exception e) {
				this.log.error(e, e);
			}
		}
	}

	/**
	 * 停止线程。清理命令执行队列
	 * 
	 * @param flag
	 */
	public void stop(boolean flag) {
		log.info("[" + threadName + "]Thread stop");
		this.stop = flag;
		this.command_queue.clear();
		try {
			synchronized (this) {
				notify();
			}
		} catch (Exception e) {
			this.log.error("Main Thread" + this.threadName + "Notify Exception" + e.getMessage());
		}
	}

	/**
	 * 添加执行命令
	 * 
	 * @param command
	 */
	public void addCommand(ICommand command) {
		try {
			boolean add = this.command_queue.add(command);
			if (!add) {
				log.error("Thread[" + threadName + "] add[" + command.getClass().getName() + "] error.command_queue is full.");
			}
			synchronized (this) {
				notify();
			}
		} catch (Exception e) {
			this.log.error("Thread[" + this.threadName + "] 唤醒线程异常" + e.getMessage());
		}
	}
	
	 /**
     * 添加事件
     * 
     * @param timer
     */
    public void addTimerEvent(ITimerEvent timer) {
        if (this.timer != null) {
            this.timer.addTimerEvent(timer);
        }
    }

	public boolean isStop() {
		return this.stop;
	}

	public String getThreadName() {
		return this.threadName;
	}

	public int getHeart() {
		return heart;
	}

}
