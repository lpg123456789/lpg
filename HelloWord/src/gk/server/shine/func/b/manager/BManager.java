package gk.server.shine.func.b.manager;

import gk.common.shine.component.GameService;
import gk.server.shine.persistence.bean.GroupData;
import gk.server.shine.persistence.manager.SaverManager;

public class BManager extends GameService {

	@Override
	public void init() throws Exception {
		System.out.println("bbbbbbbbbb");
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

}
