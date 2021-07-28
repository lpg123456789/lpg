package gk.server.shine.func.b.manager;

import gk.common.shine.component.GameService;
import gk.common.shine.event.GameEvent;
import gk.common.shine.event.impl.AEvent;
import gk.server.shine.manager.ManagerPool;
import gk.server.shine.persistence.bean.GroupData;
import gk.server.shine.persistence.manager.SaverManager;

public class BManager extends GameService {

	@Override
	public void init() throws Exception {
		ManagerPool.getInstance().getGameEventManager().registerListener(AEvent.Id, this);
	}

	//异步保存
	public void saveData() {
		try {
			Integer id = 999999;
			GroupData groupData = new GroupData();
			groupData.setDataId(id);
			String dataStr = "999999";
			groupData.setData(dataStr);
			SaverManager.getInstance().save(groupData, false);
		} catch (Exception e) {
			logger.error("save academy error", e);
		}
	}

	@Override
	public void handleGameEvent(GameEvent gameEvent) {
		if(gameEvent instanceof AEvent) {
			logger.error("AEvent ");
		}else {
			logger.error("gameEvent");
		}
	}
	

}
