package gk.server.shine.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.command.ICommand;
import gk.common.shine.server.thread.ServerThread;

public class ExecPlayerServer {
	
	
	private final static Logger log = LoggerFactory.getLogger(ExecPlayerServer.class);
	
	// 这个执行服务id、
    private final int execId;
    // 执行线程
    private ServerThread serverThread;

	
	public ExecPlayerServer(String threadName, int heart, int execId) {
        this.execId = execId;
        serverThread = new ServerThread(threadName, heart);
    }
	
	/**
     * 开启
     */
    public void start() {
        serverThread.start();
    }
    
    /**
     * 添加一个hander
     * 
     * @param hander
     */
    public void addComm(ICommand hander) {
        serverThread.addCommand(hander);
    }
    
    
    public void addEvent() {
    	
    }
	
	
}
