package gk.cross.shine.cross.across.handler;

import gk.common.shine.command.Handler;
import gk.cross.shine.cross.across.message.ACrossMessage;
import gk.cross.shine.manager.ManagerPool;

public class ACrossHandler extends Handler{

	@Override
	protected void action() {
		ACrossMessage message=(ACrossMessage) this.getMessage();
		ManagerPool.getInstance().aCrossManager.aMain();
	}

}
