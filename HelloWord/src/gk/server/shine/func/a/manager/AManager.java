package gk.server.shine.func.a.manager;

import java.util.List;

import gk.common.shine.component.GameService;
import gk.server.shine.data.bean.A_Bean;
import gk.server.shine.manager.ManagerPool;
import gk.server.shine.persistence.bean.GroupData;
import gk.server.shine.persistence.manager.DaoFactory;

public class AManager extends GameService{
	
	@Override
	public void init() throws Exception {
		System.out.println("aaaaaaaaaaa");
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
	
	//测试配置
	public void testConfig() {
		List<A_Bean> list=ManagerPool.getInstance().dataManager.getList(A_Bean.class);
		for (A_Bean a_Bean : list) {
			logger.info("a_Bean	show ,sid is [0],value is [1]",a_Bean.getSid(),a_Bean.getValue());
			logger.info("a_Bean	show ,sid is {0},value is {1}",a_Bean.getSid(),a_Bean.getValue());
		}
	}
	
	
	
}
