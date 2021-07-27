package gk.server.shine.func.a.handler;

import gk.common.shine.command.Handler;
import gk.server.shine.func.a.message.AMessage;
import gk.server.shine.manager.ManagerPool;

public class AHandler extends Handler{

	@Override
	protected void action() {
		AMessage message=(AMessage) this.getMessage();
		System.out.println("AHandler");
	
		ManagerPool.getInstance().aManager.loadData();
	}

}
