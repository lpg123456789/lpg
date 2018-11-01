package com.lpg.moudle.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import com.lpg.moudle.arena.data.ArenaRankDS;
import com.lpg.moudle.arena.data.UserRole;
import com.lpg.moudle.arena.model.ArenaRoleInfo;
import com.lpg.moudle.arena.model.ArenaUserRole;
import com.lpg.utils.DateUtils;


public class ArenaModule{
	
	/**
	 * 竞技场初始设立5000名机器人，玩家初始开放竞技场一律根据玩家功能开启时间从5001名开始往后排；
	 */
	private static AtomicInteger BEGIN_NUM=new AtomicInteger(5000);
	
	/**
	 * 10个可视化的位置
	 */
	private static int VISUAL_NUM=10;

	private static ArenaModule instance;
	public static ArenaModule getInstance() {
		if(instance == null) {
			instance = new ArenaModule();
		}
		return instance;
	}
	
	/**
	 * 初始化相关的记录：玩家的排名必须要初始化
	 * @return
	 */
	private ArenaRankDS initArena(UserRole user) {
		ArenaRankDS arenaRankingDS=new ArenaRankDS();
		arenaRankingDS.userId=user.getUserId();
		arenaRankingDS.arenaNum=0;
		arenaRankingDS.costNum=0;
		arenaRankingDS.rankPos=getMaxRankPos()+1;
		arenaRankingDS.topRankPos=arenaRankingDS.rankPos;
		arenaRankingDS.zeroTime=DateUtils.getTodayZeroTime();
		arenaRankingDS.insert();
		return arenaRankingDS;
	} 
		
	/**
	 * 获取最大值
	 * @return
	 */
	private void initMaxRankPos() {
//		ArenaRankDS  arenaRankDS=ArenaRankDS._SM().fetchOne("select * from arenaRank where rankPos = (select Max(rankPos) rankPos FROM arenaRank)");
//		if(arenaRankDS!=null) {
//			if(arenaRankDS.rankPos>BEGIN_NUM.get()) {
//				BEGIN_NUM.set(arenaRankDS.rankPos);
//			}
//		}
	}
	
	/**
	 * 再从数据库获取一次
	 * @return
	 */
	public int getMaxRankPos() {
		//ArenaRankDS  arenaRankDS=ArenaRankDS._SM().fetchOne("select * from arenaRank where rankPos = (select Max(rankPos) rankPos FROM arenaRank)");
		//if(arenaRankDS!=null) {
			//return arenaRankDS.rankPos;
		//}
		//return BEGIN_NUM.get();
		return 1;
	}
	
	/**
	 * 根据userId获得对应的数据库位置 
	 * @param user
	 * @return
	 */
	public ArenaRankDS getArenaRankingByUserId(long userId){	
		return null;
		//return ArenaRankDS._SM().fetchOne("select * from arenaRank where userId = ?" , userId);
	}
	
	/**
	 * 服务器初始化时加排行记录
	 */
	public void serverOpen(){
		//玩家的排行记录查数据库
		ArrayList<ArenaRankDS> list=new ArrayList<>();
		for (ArenaRankDS arenaRankingDS : list) {
			ArenaRoleInfo arenaRoleInfo=new ArenaUserRole(arenaRankingDS);
			ArenaManager.getInstance().addArenaRole(arenaRoleInfo);
		}
		initMaxRankPos();
		//如果加载1到200名机器人，用于竞技排名
		for (int rankPos = 1; rankPos <= 200; rankPos++) {
			ArenaRoleInfo arenaRoleInfo = ArenaManager.getInstance().getArenaRole(rankPos);
			if (arenaRoleInfo == null) {
				ArenaManager.getInstance().initArenaRole(rankPos);
			}
		}
	}
	
	/**
	 * 获取排行榜前除掉机器人的前10名
	 * @return
	 */
	public Map<Integer,ArenaRoleInfo> getRankingInfo() {
		Map<Integer,ArenaRoleInfo> map=new HashMap<>();
		for (int rankPos = 1; rankPos <= 10; rankPos++) {
			ArenaRoleInfo arenaRoleInfo= ArenaManager.getInstance().getArenaRole(rankPos);
			map.put(rankPos, arenaRoleInfo);
		}
		return map;
	}
	
	/**
	 * 竞技场挑战
	 * @throws Exception 
	 */
	public void arenaChallenge(UserRole user,int rankPos) throws Exception{
		//加锁防止并发
		ArenaUserRole arenaUserRole=null;
		synchronized (this) {
			//自己已经在竞技场中了
			if(ArenaManager.getInstance().isChallengeUser(user.getUserId())) {
				return;
			}
			//自己挑战的位置正在被挑战
			if(ArenaManager.getInstance().isChallengeRank(rankPos)) {
				return;
			}
			arenaUserRole=(ArenaUserRole) ArenaManager.getInstance().getArenaRoleByUserId(user.getUserId());
			//自己正在被挑战
			if(ArenaManager.getInstance().isChallengeRank(arenaUserRole.getRankPos())) {
				return;
			}	
		}
		//经过主界面才能生成
		ArenaRoleInfo arenaRoleInfo=ArenaManager.getInstance().getArenaRole(rankPos);
		if(arenaRoleInfo==null) {
			return;
		}
		ArenaRoleInfo userArenaRoleInfo=ArenaManager.getInstance().getArenaRoleByUserId(user.getUserId());
		if(userArenaRoleInfo.getRankPos()==rankPos) {//自己不可挑战自己的位置
			return;
		}
		int status=getChallengeStatus(userArenaRoleInfo.getRankPos(), rankPos);	
		int type=0;
		if(rankPos>userArenaRoleInfo.getRankPos()){//扫荡直接胜利
			if(status!=4) {
				return;
			}
			//直接发放奖励
			
//			ArenaMatchingConfigTemplate arenaMatchingConfig=ArenaConfig.getInstance().getArenaMatchingConfig(rankPos);
//			ArrayList<IGoods> goods = Goods.parseGoods(arenaMatchingConfig.getDayReward());
//			Backpack.getInstance().addGoods(user.getUserId(), goods, ItemLogger.GET_ARENA_WIN_REWARD);
//			arenaUserRole.arenaRankingDS.winNum+=1;
//			
//			if(arenaUserRole.arenaRankingDS.winNum>=10) {
//				String message=Lang.getInstance().get(731509,user.getName(),arenaUserRole.arenaRankingDS.winNum);
//				Broadcast.sendMessage(message, ChatType.WORLD);
//			}
			
			type=1;
		}else {//挑战（统一把玩家和机器人都当作怪物来做）
			if(status!=2) {
				return;
			}
//			int mapId = ArenaConfig.getInstance().getMapId();
//			IMap map = MapManager.getInstance().getMap(mapId, user.getUniqueId());
//			List<MonsterRole> monsterRoleList=new ArrayList<>();
//			List<String> posList=new ArrayList<>();
//			posList.add("34,15");
//			posList.add("34,16");
//			posList.add("34,17");
//			if(arenaRoleInfo.isUser()==1) {
//				Collection<MateRole> mateList = MateManager.getInstance().getMateRoles(arenaRoleInfo.getUserId(), true);
//				int i=0;
//				for (MateRole mateRole : mateList) {
//					MonsterRole monsterRole=new MonsterRole(mateRole);
//					MapTransmitter.addRole(map, monsterRole, new Point(posList.get(i)), 1);
//					i++;
//					monsterRoleList.add(monsterRole);
//				}
//			}else {
//				ArenaRobot arenaRobot=(ArenaRobot) arenaRoleInfo;	
//				for (int i = 0; i < arenaRobot.getSize(); i++) {
//					int templateId=arenaRobot.getId();
//					List<Integer> skillList=arenaRobot.getSkillList(i);
//					int clothId=arenaRobot.getClothId(i);
//					int weaponId=arenaRobot.getWeaponId(i);
//					int job=i+1;
//					String name=arenaRobot.getName();		
//					MonsterRole monster = MonsterManager.getInstance().buildMonster(arenaRobot.getUserId(),templateId, skillList, clothId, weaponId,job,name);
//					MapTransmitter.addRole(map, monster, new Point(posList.get(i)), 1);
//					monsterRoleList.add(monster);
//				}
//			}
//			//切换地图
//			MapTransmitter.changeMap(user, 2, mapId);
//			//添加竞技场战斗标识
//			ArenaManager.getInstance().addChallengeStatus(user.getUserId(),rankPos);
//			//攻击目标
//			BattleModule.getInstance().attack(user, monsterRoleList);	
			type=0;
			
			String des=arenaRoleInfo.isUser()==1?"玩家":"机器人";	
			//加日志
			StringBuilder sb=new StringBuilder();
			sb.append("挑战者id ").append(user.getUserId()).append(" 角色名 ").append(user.getName()).append(" 名次为 ").append(arenaUserRole.getRankPos());
			sb.append(" 挑战 ").append(des);
			sb.append("被挑战者id ").append(arenaRoleInfo.getUserId()).append(" 角色名 ").append(arenaRoleInfo.getName()).append(" 名次为 ").append(arenaRoleInfo.getRankPos());
		}	
		arenaUserRole.arenaRankingDS.arenaNum+=1;
	}
	
	/**
	 * @param rankPos
	 */
	public List<ArenaRoleInfo> getArenaRoleByRank(int rankPos) {
		Set<Integer> set=this.getArenaRoleSet(rankPos);
		return this.getArenaRoleInfo(set);
	}
	
	/**
	 * 是否可以挑战
	 * @return
	 */
	public int getChallengeStatus(int userRankPos,int otherRankPos) {
//		Set<Integer> arenaRoleSet=ArenaConfig.getInstance().getChallengeInfo(userRankPos);
//		int status=getStatus(arenaRoleSet, userRankPos, otherRankPos);
		return 1;
	}
	
	/**
	 * 10个坑位的排位Id，根据竞技场的规则进行填满
	 * @param rankPos
	 * @return
	 */
	private Set<Integer> getArenaRoleSet(int rankPos){
		//获取1到5
		Set<Integer> set=new TreeSet<>();
		for (int i = 1; i <=5; i++) {
			set.add(i);
		}
		//获取可挑战
		//set.addAll(ArenaConfig.getInstance().getChallengeInfo(rankPos));
		//获取可扫荡的名次
		//int remianNum=VISUAL_NUM-set.size();
		for (int i = 0; i < 10; i++) {
			if(rankPos>BEGIN_NUM.get()) {
				break;
			}
			if(set.size()>=10) {
				break;
			}
			set.add(rankPos+i);
		}
		return set;
	}
	
	/**
	 * 获取角色
	 * @return
	 */
	private List<ArenaRoleInfo> getArenaRoleInfo(Set<Integer> set){
		List<ArenaRoleInfo> list=new ArrayList<>();
		for (Integer rankPos : set) {
			ArenaRoleInfo arenaRoleInfo=ArenaManager.getInstance().getArenaRole(rankPos);
			if(arenaRoleInfo==null) {
//				boolean flag=ArenaConfig.getInstance().isInitRoot(rankPos);
//				if(!flag) {
//					continue;
//				}
				arenaRoleInfo=ArenaManager.getInstance().initArenaRole(rankPos);
			}
			list.add(arenaRoleInfo);
		}
		return list;
	}
	
	/**
	 * 1.不可挑战    2.可以挑战   3.自己  4.可扫荡 
	 * 获取状态
	 * @return
	 */
	public int getStatus(Set<Integer> set,int userRankPos,int otherRankPos) {
		if (userRankPos > otherRankPos) {
			if (!set.contains(otherRankPos)) {
				return 1;
			} else {
				return 2;
			}
		} else if (userRankPos == otherRankPos) {
			return 3;
		} else {
			return 4;
		}
	}
	
	/**
	 * gm指令指定排名
	 * 如果原先这个排名被占据了就互换，否则指定
	 * @throws Exception 
	 */
	public synchronized void gmArenaSort(long userId, int newPos) throws Exception {
		if (newPos >= 10000) {
			return;
		}
		//自己已经在竞技场挑战不能gm
		if(ArenaManager.getInstance().isChallengeUser(userId)) {
			//Broadcast.sendOperationMessage(userId,731501);
			return;
		}
		ArenaRoleInfo myArenaRoleInfo = ArenaManager.getInstance().getArenaRoleByUserId(userId);
		int myRankPos = myArenaRoleInfo.getRankPos();
		//自己在竞技场中被挑战不能gm
		if(ArenaManager.getInstance().isChallengeRank(myRankPos)) {
			//Broadcast.sendOperationMessage(731501, userId);
			return;
		}
		if (myRankPos == newPos) {
			//Broadcast.sendOperationMessage("位置一样", userId);
			return;
		}
		//该位置在竞技场中不能被挑战
		if(ArenaManager.getInstance().isChallengeRank(newPos)) {
			//Broadcast.sendOperationMessage(731501, userId);
			return;
		}	
		StringBuilder sb = new StringBuilder();
		sb.append("玩家 ").append(myArenaRoleInfo.getName()).append(" 使用gm指令改变名次 ");
		sb.append("原先名次 ").append(myRankPos).append(" 输入名次").append(newPos);
		ArenaRoleInfo enemyArenaRoleInfo = ArenaManager.getInstance().getArenaRole(newPos);
		if (enemyArenaRoleInfo != null) {// 该位置有玩家，机器人排名不入数据库，纯内存
			//TitleModule.getInstance().updateNewCompetitiveTitle(myArenaRoleInfo, enemyArenaRoleInfo);		
			String des = enemyArenaRoleInfo.isUser() == 1 ? "玩家" : "机器人";
			sb.append(des).append(enemyArenaRoleInfo.getName()).append(" 被动改变名次 ").append(" 原名次"+enemyArenaRoleInfo.getRankPos());
			enemyArenaRoleInfo.updatePos(myRankPos);
			enemyArenaRoleInfo.update();
			ArenaManager.getInstance().getArenaRankingMap().put(myRankPos, enemyArenaRoleInfo);	
			sb.append(" 新名次 ").append(myRankPos);
		}else {
			//TitleModule.getInstance().gmUpdateNewCompetitiveTitle(myArenaRoleInfo, newPos);
			
			//这个地方要注意
			ArenaManager.getInstance().getArenaRankingMap().remove(myRankPos);
			sb.append("新位置没有其它玩家 ，固直接移除原位置玩家");
		}
		sb.append(myArenaRoleInfo.getName());
		sb.append(" 原先名次 ").append(myArenaRoleInfo.getRankPos());
		myArenaRoleInfo.updatePos(newPos);
		myArenaRoleInfo.update();
		ArenaManager.getInstance().getArenaRankingMap().put(newPos, myArenaRoleInfo);
		sb.append(" 改变后的名次 ").append(myArenaRoleInfo.getRankPos());
		//ActionLogger.getInstance().addLogs(userId, ActionLogger.ARENA_SORT, sb.toString());
		
	}
	
	
	/**
	 * @param flag  0表示失败，1表示成功
	 * 防止gm指令发生
	 */
	public synchronized void dealArenaBattleResult(long userId, int flag) {
		try {
			String reward = "";// 基础奖励
			String newRankReward = "";// 刷新了历史最高纪录奖励
			int num = 0;// 提示数量
			int rewardType = 0;// 日志记录类型
			Integer enemyPos = ArenaManager.getInstance().getChallengeStatus(userId);// 被挑战的名次
			StringBuilder sb = new StringBuilder();
			if (enemyPos == null) {
				return;
			}
			// 邮件发送奖励
			//ArenaMatchingConfigTemplate arenaMatchingConfig = ArenaConfig.getInstance().getArenaMatchingConfig(enemyPos);
			ArenaRoleInfo myArenaRoleInfo = ArenaManager.getInstance().getArenaRoleByUserId(userId);
			ArenaUserRole myArenaUserRole = (ArenaUserRole) myArenaRoleInfo;
			ArenaRoleInfo enemyArenaRoleInfo = ArenaManager.getInstance().getArenaRole(enemyPos);
			String des = enemyArenaRoleInfo.isUser() == 1 ? "玩家" : "机器人";
			
			if (flag == 1) {// 挑战成功		
				
				//修改竞技场称号
				//TitleModule.getInstance().updateNewCompetitiveTitle(myArenaRoleInfo, enemyArenaRoleInfo);
				
				sb.append("挑战者 ").append(myArenaRoleInfo.getName()).append("原名次").append(myArenaRoleInfo.getRankPos());
				sb.append("  被挑战者 ").append(des).append(enemyArenaRoleInfo.getName()).append(" 原名次 ").append(enemyArenaRoleInfo.getRankPos());
				
				// 历史排名奖励
//				if (enemyPos < myArenaUserRole.arenaRankingDS.topRankPos) {
//					int newRankRewardNum = ArenaConfig.getInstance().getNewRankReward(myArenaUserRole.arenaRankingDS.topRankPos, enemyPos);
//					newRankReward = arenaMatchingConfig.getHighestReward().split(",")[0] + "," + newRankRewardNum;
//				}
				
				// 刷新排名
				//reward = arenaMatchingConfig.getReward1();
				num = myArenaRoleInfo.getRankPos() - enemyPos;
				// 排序
				ArenaManager.getInstance().arenaSort(userId);
				// 记录战报
				//this.initArenaReport(userId, 1, enemyArenaRoleInfo.getPower(), num, enemyArenaRoleInfo.getName());
				// 如果对手是玩家，则记录战报
//				if (enemyArenaRoleInfo.isUser() == 1) {
//					this.initArenaReport(enemyArenaRoleInfo.getUserId(), 2, myArenaRoleInfo.getPower(), num, myArenaRoleInfo.getName());
//				}
//				rewardType = ItemLogger.GET_ARENA_WIN_REWARD;
				myArenaUserRole.arenaRankingDS.winNum+=1;
				
//				广播			
//				if(myArenaUserRole.arenaRankingDS.winNum>=10) {
//					String message=Lang.getInstance().get(731509,myArenaUserRole.getName(),myArenaUserRole.arenaRankingDS.winNum);
//					Broadcast.sendMessage(message, ChatType.WORLD);
//				}
							
				sb.append("  挑战者 ").append(myArenaRoleInfo.getName()).append(" 挑战成功，名次变为 ").append(myArenaRoleInfo.getRankPos());
				sb.append("  被挑战者 ").append(des).append(enemyArenaRoleInfo.getName()).append(" 名次变为 ").append(enemyArenaRoleInfo.getRankPos());
			} else {// 挑战失败
				
				myArenaUserRole.arenaRankingDS.winNum=0;
				
				//reward = arenaMatchingConfig.getReward2();
//				rewardType = ItemLogger.GET_ARENA_FAILE_REWARD;
				sb.append("挑战者 ").append(myArenaRoleInfo.getName()).append(" 挑战失败，名次是原来名次 ").append(myArenaRoleInfo.getRankPos());
				sb.append("被挑战者 ").append(des).append(enemyArenaRoleInfo.getName()).append(" 名次是原来名次 ").append(enemyArenaRoleInfo.getRankPos());
			}
			
//			ActionLogger.getInstance().addLogs(userId, ActionLogger.ARENA_SORT, sb.toString());
			// 最高排名奖励
//			if (!StringUtils.isEmpty(newRankReward)) {
//				IGoods goods = new Goods(newRankReward);
//				UserModule.getInstance().addDiamond(userId, goods.num(), DiamondLogger.GET_ARENE, "竞技场历史排名突破获得");
//			}
//			// 邮件发送奖励
//			ArrayList<IGoods> goods = Goods.parseGoods(reward);
//			Backpack.getInstance().addGoods(userId, goods, rewardType);
			// 发协议
//			int reslut = flag == 1 ? 1 : 2;// 1、胜利 2、失败
//			ArenaEvent.getInstance().sendArenaSettlement(userId, reslut, newRankReward, reward, myArenaRoleInfo.getRankPos(), num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ArenaManager.getInstance().removeChallengeStatus(userId);
		}
	}
	
	/**
	 * 退出视为失败
	 * @param user
	 */
	public void exit(UserRole user) {
//		if(user.getMap().getType() != MapType.TYPE_PB || user.getMap().getSubType() != MapType.SUB_TYPE_FB_ARENA) {
//			return;
//		}	
//		//发送奖励
//		try {
//			Integer rankPos=ArenaManager.getInstance().getChallengeStatus(user.getUserId());
//			int userRankPos=ArenaManager.getInstance().getArenaRoleByUserId(user.getUserId()).getRankPos();	
//			if(rankPos!=null) {
//				ArenaMatchingConfigTemplate arenaMatchingConfig=ArenaConfig.getInstance().getArenaMatchingConfig(rankPos);
//				String reward=arenaMatchingConfig.getReward2();
//				ArrayList<IGoods> goods = Goods.parseGoods(reward);
//				Backpack.getInstance().addGoods(user.getUserId(), goods, ItemLogger.GET_ARENA_FAILE_REWARD);
//				//发协议
//				ArenaEvent.getInstance().sendArenaSettlement(user.getUserId(), 2, "", reward, userRankPos, 0);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			MapTransmitter.changeMap(user, 2, user.getPreMapId());
//			ArenaManager.getInstance().removeChallengeStatus(user.getUserId());
//		}
		
		ArenaManager.getInstance().removeChallengeStatus(user.getUserId());
	}
	
	/**
	 * 功能开放和功能兼容初始化玩家数据
	 * 生成排名记录
	 * @param user
	 * @throws Exception 
	 */
	public void initPlayArenaInfo(UserRole user) throws Exception{
		ArenaRankDS arenaRankDS=this.getArenaRankingByUserId(user.getUserId());
		if(arenaRankDS==null) {
			synchronized (this) {
				//从数据库获取避免  数据库一致，内存不一致的情况
				arenaRankDS=initArena(user);
				BEGIN_NUM.set(getMaxRankPos());
				StringBuilder sb=new StringBuilder();
				sb.append(user.getUserId()).append("创建排名,排名是 ").append(arenaRankDS.rankPos);
			}
		}
		ArenaRoleInfo newArenaRoleInfo=new ArenaUserRole(arenaRankDS);
		ArenaManager.getInstance().addArenaRole(newArenaRoleInfo);
	}
	
}
