package com.lpg.moudle.redPocket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.lpg.utils.DateUtils;

/**
 * 红包模块
 * @author lpg
 * @date 2018年8月8日
 */
public class RedPocketMoudle {
	
	public static int SevenDay =DateUtils.PER_DAY_SECOND * 7;

	private static RedPocketMoudle instance;

	public static RedPocketMoudle getInstance() {
		if (instance == null) {
			instance = new RedPocketMoudle();
		}
		return instance;
	}

	/**
	 * 时间换空间 key:id
	 */
	private Map<Integer, RedPocket> redPocketMap = new ConcurrentHashMap<>();

	/**
	 * 时间换空间 key:0为世界服  不为0表示军团id
	 */
	private Map<Long, List<RedPocket>> cropsRedPocketMap = new ConcurrentHashMap<>();

	/**
	 * 用户的map
	 */
	private Map<Long, UserRedPocket> userRedPocketMap = new HashMap<>();
	
	public RedPocket getRedPocketById(int id){
		return redPocketMap.get(id);
	}

	public Map<Integer, RedPocket> getRedPocketMap() {
		return redPocketMap;
	}

	/**
	 * 缓存数据
	 */
	private void initCache(RedPocket redPocket) {
		redPocketMap.put(redPocket.getId(), redPocket);
		List<RedPocket> luckMoneyList = cropsRedPocketMap.get(redPocket.getCorpsId());
		if (luckMoneyList == null) {
			luckMoneyList = new ArrayList<>();
			cropsRedPocketMap.put(redPocket.getCorpsId(), luckMoneyList);
		}
		luckMoneyList.add(redPocket);
	}
	
	
	/**
	 * 登陆时加载相关数据
	 * @param uid
	 * @throws Exception
	 */
	public void login(long uid) throws Exception {
		UserRedPocket userRedPocket = new UserRedPocket();
		userRedPocketMap.put(uid, userRedPocket);
	}
	

	/**
	 * 发红包
	 * 背包格子id
	 * @param user
	 * @param type
	 * @param num
	 * @throws Exception 
	 */
	public void redPocketSend(long uid,int id) throws Exception {
		String[] params=new String[]{};
		RedPocket redPocket = new RedPocket(111, 111L, 0L, 111,params,111);
		List<RedPocket> redPocketList = cropsRedPocketMap.get(0L);
		if (redPocketList == null) {
			redPocketList = new ArrayList<>();
			cropsRedPocketMap.put(0L, redPocketList);
		}
		redPocketList.add(redPocket);
		redPocketMap.put(redPocket.getId(), redPocket);
	}
	
	/**
	 * 拆红包
	 * @param user
	 * @param id
	 * @throws Exception 
	 */
	public void redPocketRob(long uid, int id) throws Exception {
		RedPocket redPocket = redPocketMap.get(id);
		if(redPocket==null) {
			return;
		}
		int diamond=0;
		synchronized (redPocket) {
			//判断军团信息
			Long corpsId=redPocket.getCorpsId();
			if(corpsId!=0) {
				//判断军团id
				return;
			}
			//钻石获取的规则
			diamond=RedPocketMethods.getRandomMoney2(redPocket);
			UserRedPocket userRedPocket=userRedPocketMap.get(uid);
			userRedPocket.addData(id, diamond);
			redPocket.addUser(uid,diamond);
		}
		//计算拿了最大红包的人
		if(redPocket.isFull()) {
			dealLuckId(redPocket, id);
		}
		
	}
	
	private void dealLuckId(RedPocket redPocket,int id) {
		redPocket.calc();
		long luckUserId=redPocket.getLuckUserId();
	}
	
	/**
	 * 红包列表展现最近7天内全服发送的红包与自己军团发送的红包详情，上限30条，超出则删除时间最早的红包
	 * 可领取＞时间
	 * 玩家红包记录查看返回
	 * 全服红包（包括已领取的和没领取的），
	 * 军团红包（还在军团，已退出军团，退出了军团后红包记录还是存在的）
	 * @param user
	 * @throws Exception 
	 */
	public void redPocketList2(long uid){
		UserRedPocket userRedPocket=userRedPocketMap.get(uid);	
		Set<RedPocket> redPocketSet = new TreeSet<RedPocket>(new MyComparator(uid));
		List<RedPocket> allRedPocketList = cropsRedPocketMap.get(0L);
		// 全服
		if (allRedPocketList != null) {
			redPocketSet.addAll(allRedPocketList);
		}
		//军团
		Long cropsId=0L;
		if(cropsId!=0){
			List<RedPocket> cropsPocketList=cropsRedPocketMap.get(cropsId);
			if(cropsPocketList!=null) {
				redPocketSet.addAll(cropsPocketList);
			}
		}
		Set<Integer> keySet=userRedPocket.getMapSet();
		for (Integer key : keySet) {
			RedPocket redPocket=redPocketMap.get(key);
			if(redPocket==null) {
				continue;
			}
			if(redPocketSet.contains(redPocket)) {
				continue;
			}
			redPocketSet.add(redPocket);
		}
	}
	
	//展示自己get的红包
	
}


class MyComparator implements Comparator<RedPocket>{  
	
	private long userId=0; 
	
	public MyComparator(long userId) {
		this.userId=userId;
	}
	
    @Override  
    public int compare(RedPocket o1, RedPocket o2) {
    	if(o1.getId()==o2.getId()) {
    		return 0;
    	}else {
    		if(o1.isContainsUser(userId)>o2.isContainsUser(userId)) {
    			return 1;
    		}else if(o1.isContainsUser(userId)==o2.isContainsUser(userId)){	   			
    			if(o1.getEndTime()<o2.getEndTime()) {
    				return 1;
    			}else {
    				return -1;
    			} 			
    		}else {
    			return -1;
    		}
    		
    		
//    		if( o1.<t2) {
//    			return 1;
//    		} else if (t1 == t2) {
//    			if () {
//    				
//    			}
//    		} else {
//    			return -1;
//    		}
//    		
//    		if(o1.isContainsUser(userId)!=o2.isContainsUser(userId)) {
//    			return o1.isContainsUser(userId)< o2.isContainsUser(userId) ?1:-1;
//    		}else {
//    			return o1.getEndTime()<o2.getEndTime()? 1 : -1;
//    		}
    		
//    		if(o1.getEndTime()<o2.getEndTime()) {
//    			return o1.getEndTime()<o2.getEndTime()? -1 : 1;
//    		}else {
//    			return o1.isContainsUser(userId)< o2.isContainsUser(userId) ?-1:1;
//    		}
    		
    	}	
    }  
}

