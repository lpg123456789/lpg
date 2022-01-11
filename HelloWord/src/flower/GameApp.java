package flower;

import flower.com.game.common.config.ServerSettings;
import flower.com.game.context.GameContext;
import flower.com.game.persistence.GameSqlFactory;
import flower.com.game.persistence.LogSqlFactory;

public class GameApp {

	public static void main(String[] args) throws Exception {
		init();
		startup();
	}

	private static void init() {
		 // logger 服务器配置
		 ServerSettings.init();
		
		//游戏库连接
		 GameSqlFactory.getInstance().init();
		//日志库连接 
		 LogSqlFactory.getInstance().init();
		
		//初始化各模块游戏
		 GameContext.getInstance().initialize();
		
		//执行控制台监听 非必要
		 startConsoleListener();
		
	}

	private static void startup() throws Exception {
		 GameContext.getInstance().start();
	}
	
	
	 /**
     * 开始控制监听，接受控制台指令输入，回车执行
     */
    private static void startConsoleListener() {
        ConsoleUtil.startConsoleListener();
    }

	
}
