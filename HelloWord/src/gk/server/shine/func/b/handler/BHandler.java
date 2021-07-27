package gk.server.shine.func.b.handler;

import gk.common.shine.command.Handler;
import gk.server.shine.func.a.message.AMessage;
import gk.server.shine.func.b.message.BMessage;
import gk.server.shine.manager.ManagerPool;

public class BHandler extends Handler{

	@Override
	protected void action() {
		BMessage message=(BMessage) this.getMessage();
		System.out.println("BHandler");
		
		ManagerPool.getInstance().bManager.saveData();
	}

}
