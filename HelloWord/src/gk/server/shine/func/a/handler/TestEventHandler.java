package gk.server.shine.func.a.handler;

import gk.common.shine.command.Handler;
import gk.server.shine.func.a.message.TestConfigMessage;
import gk.server.shine.manager.ManagerPool;

public class TestEventHandler extends Handler{

	@Override
	protected void action() {
		ManagerPool.getInstance().aManager.testEvent();
	}

}
