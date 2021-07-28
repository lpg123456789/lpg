package gk.server.shine.func.a.manager;

import java.util.List;

import gk.common.shine.component.GameService;
import gk.common.shine.event.GameEvent;
import gk.common.shine.event.impl.AEvent;
import gk.server.shine.data.bean.A_Bean;
import gk.server.shine.manager.ManagerPool;
import gk.server.shine.persistence.bean.GroupData;
import gk.server.shine.persistence.manager.DaoFactory;

public class AManager extends GameService{
	
	@Override
	public void init() throws Exception {
		
	}

	//同步加载
	public void loadData() {
		//调用数据库的方法
		try { 
			Integer id=999999;
			GroupData groupData = DaoFactory.getInstance().select(GroupData.class,id);
			if(groupData==null) {
				logger.info("load success,groupData is null");
			}else {
				logger.info("load success,groupData is "+groupData.getData());
			}			
		} catch (Exception e) {
			logger.error("load data error", e);
		}
	}
	
	//测试配置读取
	public void testConfig() {
		List<A_Bean> list=ManagerPool.getInstance().dataManager.getList(A_Bean.class);
		for (A_Bean a_Bean : list) {
			logger.info("a_Bean	show ,sid is [{}],value is [{}]",a_Bean.getSid(),a_Bean.getValue());
		}
	}

	//测试事件
	public void testEvent() {
		AEvent a=new AEvent(111);
		ManagerPool.getInstance().fireGameEvent(a);
	}
	
	
}
