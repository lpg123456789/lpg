package gk.server.shine.func.a.handler;

import gk.common.shine.command.Handler;
import gk.server.shine.func.a.message.TestConfigMessage;
import gk.server.shine.manager.ManagerPool;

public class TestConfigHandler extends Handler{

	@Override
	protected void action() {
		TestConfigMessage message=(TestConfigMessage) this.getMessage();
		System.out.println("TestConfigHandler");
	
		ManagerPool.getInstance().aManager.testConfig();
	}

}
