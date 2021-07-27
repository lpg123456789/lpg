package gk.server.shine;

import java.lang.management.ManagementFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import gk.server.shine.persistence.manager.ConnectionFactory;
import gk.server.shine.server.ConsoleServer;
import gk.server.shine.server.GameServer;

public class GameTestMain {

	private static Logger log = Logger.getLogger(GameTestMain.class);

	public static void main(String[] args) throws Exception {

		String log4jConfigPath = Globals.getConfigPath(Globals.LOG_CONFIG_FILE);
		DOMConfigurator.configure(log4jConfigPath);

		log.info("server|logic|start...");

		String name = ManagementFactory.getRuntimeMXBean().getName();
		log.info("pid=" + name.split("@")[0]);

		// 加载服务器配置
		try {
			Globals.init();
			// 初始化数据库连接
            ConnectionFactory.getInstance().init();
            // console服务
            new Thread(ConsoleServer.getInstance()).start();
            // 主服务
            new Thread(GameServer.getInstance()).start();
		} catch (Throwable e) {
			log.info("server|logic|start error.", e);
		}

	}

}
