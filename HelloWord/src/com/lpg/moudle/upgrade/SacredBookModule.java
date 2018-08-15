package com.lpg.moudle.upgrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *	游戏中最普通，最基本的升级，装备，分解功能，去除一些判断信息
 **/
public class SacredBookModule{

	private static SacredBookModule instance;

	public static SacredBookModule getInstance() {
		if (instance == null) {
			instance = new SacredBookModule();
		}
		return instance;
	}

	/**
	 * 圣典穿戴情况 key 用户id key job key 圣典id SacredBookDS 数据库记录
	 */
	private Map<Long, Map<Integer, Map<Integer, SacredBookDS>>> sacredBookMap = new HashMap<Long, Map<Integer, Map<Integer, SacredBookDS>>>();

	public SacredBookDS initSacredBook(long userId, int job, int templateId) {
		SacredBookDS sacredBookDS = new SacredBookDS();
		sacredBookDS.userId = userId;
		sacredBookDS.star = 0;
		sacredBookDS.level = 1;
		sacredBookDS.job = job;
		sacredBookDS.templateId = templateId;
		sacredBookDS.bookEquipInfo = "0_0_0_0_0";
		return sacredBookDS;
	}
	
	/**
	 * 服务器初始化加载圣典信息 
	 */
	public void initCacheDate() {
		ArrayList<SacredBookDS> sacredBookList = new ArrayList<>();
		if(sacredBookList!=null){
			for (SacredBookDS sacredBookDS : sacredBookList) {
				Map<Integer, Map<Integer, SacredBookDS>> userMap=sacredBookMap.get(sacredBookDS.userId);
				if(userMap==null) {
					userMap=new HashMap<>();
					sacredBookMap.put(sacredBookDS.userId, userMap);
				}
				Map<Integer, SacredBookDS> tempMap = userMap.get(sacredBookDS.job);
				if (tempMap == null) {
					tempMap = new HashMap<Integer, SacredBookDS>();
					userMap.put(sacredBookDS.job, tempMap);
				}
				tempMap.put(sacredBookDS.templateId, sacredBookDS);
			}
		}
	}

	/**
	 * 用户登陆缓存
	 * 
	 * @param user
	 */
	public void login(long uid) {
		List<SacredBookDS> sacredBookList = new ArrayList<>();
		if (sacredBookList != null) {
			if (sacredBookList.size() > 0) {
				Map<Integer, Map<Integer, SacredBookDS>> userMap = new HashMap<>();
				for (SacredBookDS sacredBookDS : sacredBookList) {
					Map<Integer, SacredBookDS> tempMap = userMap.get(sacredBookDS.job);
					if (tempMap == null) {
						tempMap = new HashMap<Integer, SacredBookDS>();
						userMap.put(sacredBookDS.job, tempMap);
					}
					tempMap.put(sacredBookDS.templateId, sacredBookDS);
				}
				sacredBookMap.put(uid, userMap);
			}
		}
	}

	/**
	 * 请求圣典系统界面
	 * @param user
	 * @param job
	 * @throws Exception 
	 */
	public void main(long uid,int job) throws Exception {
		// 获取该职业已有的圣典
		Map<Integer, SacredBookDS> tempMap = new HashMap<>();
		Map<Integer, Map<Integer, SacredBookDS>> sacredBookDSMap = sacredBookMap.get(uid);
		if (sacredBookDSMap != null) {
			Map<Integer, SacredBookDS> jobSacredBookDSMap = sacredBookDSMap.get(job);
			if (jobSacredBookDSMap != null) {
				tempMap.putAll(jobSacredBookDSMap);
			}
		}
	}

	/**
	 * @param user
	 * @param job
	 * @param id
	 */
	public void active(long uid, int job, int id,boolean isSend) throws Exception{
		if (job < 1 || job > 3) {
			return;
		}
		boolean isHave = isHaveSacredBook(uid, job, id);
		if (!isHave) {
			return;
		}
		// 数据库
		SacredBookDS sacredBookDS = initSacredBook(uid, job, id);
		// 内存
		initSacredBookCache(uid, job, sacredBookDS);
	}
	
	/**
	 * 圣典装备请求穿戴（穿最好的）
	 * @param user
	 * @param job
	 * @param id
	 * @param grid
	 */
	public void libramsWear(long uid, int job, int id, int grid) {
		Map<Integer, Map<Integer, SacredBookDS>> userSacredBookMap = sacredBookMap.get(uid);
		if (userSacredBookMap == null) {
			return;
		}
		Map<Integer, SacredBookDS> jobSacredBookMap = userSacredBookMap.get(job);
		if (jobSacredBookMap == null) {
			return;
		}
		SacredBookDS sacredBookDS = jobSacredBookMap.get(id);
		if (sacredBookDS == null) {
			return;
		}
		String bookEquipInfoStr = sacredBookDS.bookEquipInfo;
		String[] bookEquipInfoArr = bookEquipInfoStr.split("_");
		int value = Integer.parseInt(bookEquipInfoArr[grid - 1]);
		// 格子已有装备，不能重复，只能替换
		if (value != 0) {
			return;
		}
		// 取出最好的装备
		int templateId = getBestEquipByGrid(uid, id, grid);
		// 圣典穿上装备
		String[] str = sacredBookDS.bookEquipInfo.split("_");
		str[grid - 1] = String.valueOf(templateId);
		StringBuffer equipInfo = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			equipInfo.append(str[i] + "_");
		}
		equipInfo.deleteCharAt(equipInfo.length() - 1);
		sacredBookDS.bookEquipInfo = equipInfo.toString();
	}

	private int getBestEquipByGrid(long uid, int id,int grid) {
		int templateId = 0;
		return templateId;
	}
	
	/**
	 * 获取同类型最好的装备
	 * @param user
	 * @param grid
	 * @return
	 */
	private int getBestEquipByProp(long uid,int propId) {
		int templateId = 0;
		return templateId;
	}

	/**
	 * 判断是否已有该圣典
	 * 
	 * @param userSacredBookMap
	 * @return
	 */
	private boolean isHaveSacredBook(long uid, int job, int id) {
		Map<Integer, Map<Integer, SacredBookDS>> userSacredBookMap = sacredBookMap.get(uid);
		if (userSacredBookMap == null) {
			return true;
		} else {
			Map<Integer, SacredBookDS> jobSacredBookMap = userSacredBookMap.get(job);
			if (jobSacredBookMap == null) {
				return true;
			} else {
				SacredBookDS sacredBookDS = jobSacredBookMap.get(id);
				if (sacredBookDS != null) {
					return false;
				}
				return true;
			}
		}
	}

	/**
	 * 激活时添加缓存
	 * 
	 * @param user
	 * @param userSacredBookMap
	 * @param job
	 * @param sacredBookDS
	 */
	private void initSacredBookCache(long uid, int job, SacredBookDS sacredBookDS) {
		Map<Integer, Map<Integer, SacredBookDS>> userSacredBookMap = sacredBookMap.get(uid);
		if (userSacredBookMap == null) {
			userSacredBookMap = new HashMap<>();
			sacredBookMap.put(uid, userSacredBookMap);
		}
		Map<Integer, SacredBookDS> jobSacredBookMap = userSacredBookMap.get(job);
		if (jobSacredBookMap == null) {
			jobSacredBookMap = new HashMap<>();
			userSacredBookMap.put(job, jobSacredBookMap);
		}
		jobSacredBookMap.put(sacredBookDS.templateId, sacredBookDS);
	}

	/**
	 * 圣典升阶
	 * 
	 * @param user
	 * @param job
	 * @param id
	 */
	public void libramsUpLev(long uid, int job, int id) {
		Map<Integer, Map<Integer, SacredBookDS>> userSacredBookMap = sacredBookMap.get(uid);
		if (userSacredBookMap == null) {
			return;
		}
		Map<Integer, SacredBookDS> jobSacredBookMap = userSacredBookMap.get(job);
		if (jobSacredBookMap == null) {
			return;
		}
		SacredBookDS sacredBookDS = jobSacredBookMap.get(id);
		if (sacredBookDS == null) {
			return;
		}
		// 修改内存和数据库
		if (sacredBookDS.star == 10) {
			sacredBookDS.level = sacredBookDS.level + 1;
			sacredBookDS.star = 0;
		} else {
			sacredBookDS.star += 1;
		}
	}

	/**
	 * 装备请求升星
	 * 
	 * @param user
	 * @param job
	 * @param id
	 * @param gridId
	 */
	public void LibramsUp(long uid, int job, int id, int gridId) {
		Map<Integer, Map<Integer, SacredBookDS>> userSacredBookMap = sacredBookMap.get(uid);
		if (userSacredBookMap == null) {
			return;
		}
		Map<Integer, SacredBookDS> jobSacredBookMap = userSacredBookMap.get(job);
		if (jobSacredBookMap == null) {
			return;
		}
		SacredBookDS sacredBookDS = jobSacredBookMap.get(id);
		if (sacredBookDS == null) {
			return;
		}
		String[] str = sacredBookDS.bookEquipInfo.split("_");
		int equipId = Integer.parseInt(str[gridId - 1]);
		if (equipId == 0) {
			return;
		}
		int templateId=0;
		// 修改内存和数据库
		str[gridId - 1] = String.valueOf(templateId);
		StringBuffer equipInfo = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			equipInfo.append(str[i] + "_");
		}
		equipInfo.deleteCharAt(equipInfo.length() - 1);
		sacredBookDS.bookEquipInfo = equipInfo.toString();
	}

	/**
	 * 请求装备替换
	 * 
	 * @param user
	 * @param job
	 * @param id
	 * @param gridId
	 */
	public void libramsReplace(long uid, int job, int id, int grid) {
		Map<Integer, Map<Integer, SacredBookDS>> userSacredBookMap = sacredBookMap.get(uid);
		if (userSacredBookMap == null) {
			return;
		}
		Map<Integer, SacredBookDS> jobSacredBookMap = userSacredBookMap.get(job);
		if (jobSacredBookMap == null) {
			return;
		}
		SacredBookDS sacredBookDS = jobSacredBookMap.get(id);
		if (sacredBookDS == null) {
			return;
		}
		String bookEquipInfoStr = sacredBookDS.bookEquipInfo;
		String[] bookEquipInfoArr = bookEquipInfoStr.split("_");
		int propId = Integer.parseInt(bookEquipInfoArr[grid - 1]);
		// 没有装备，只能替换
		if (propId == 0) {
			return;
		}
		// 取出比当前好的装备
		int templateId = getBestEquipByProp(uid, propId);
		if (templateId == 0) {
			return;
		}
		String[] str = sacredBookDS.bookEquipInfo.split("_");
		if (templateId == propId) {
			return;
		}
		// 圣典穿上装备
		str[grid - 1] = String.valueOf(templateId);
		StringBuffer equipInfo = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			equipInfo.append(str[i] + "_");
		}
		equipInfo.deleteCharAt(equipInfo.length() - 1);
		sacredBookDS.bookEquipInfo = equipInfo.toString();
	}

	/**
	 * 分解获得道具
	 * 
	 * @param user
	 * @param bpid
	 * @param num
	 */
	public void libramsResolve(int bpid, int num) {
		int propId = 111;
		int propNum = 1;
		int getNum = propNum * num;
	}
	
	private boolean canUpStar(long uid,int equipId) {
		return true;
	}
	
}
