package com.lpg.roomOrTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不同用户进出房间造成异常 加入房间要同步，退出房间也需要同步 否则，造成游戏开始了，但是人员不够的情况
 * 
 * @author Administrator
 *
 */
public class RoomRight {

	public static boolean flag = false;

	/**
	 * 人数
	 */
	public static int pNum = 4;

	/**
	 * 房间有哪些用户
	 */
	public static Map<String, List<Integer>> RoomToRoleMap = new HashMap<>();

	/**
	 * 用户在哪个房间
	 */
	public static Map<Integer, String> RoleToRoomMap = new HashMap<>();

	public static void main(String[] args) {
		initRoom();

		new Thread(new Runnable() {
			@Override
			public void run() {
				joinRoom(1, "room" + 4);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				exitRoom(1);
			}
		}).start();

		printInfo();
	}

	/**
	 * 初始化房间
	 */
	public static void initRoom() {
		for (int i = 0; i < 10; i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(10 + i);
			RoomToRoleMap.put("room" + i, a);
		}
		System.out.println("初始化了房间 " + RoomToRoleMap.size());
		printInfo();
	}

	/**
	 * 用户是否可以加入房间
	 * 
	 * @param id
	 * @return
	 */
	public static boolean canJoin(Integer id) {
		return !RoleToRoomMap.containsKey(id);
	}

	/**
	 * 判断房间内是否可以加入人
	 * 
	 * @param roomId
	 * @return
	 */
	public static boolean canJoin(String roomId) {
		return !(RoomToRoleMap.get(roomId).size() == 4);
	}

	/**
	 * 用户加入房间
	 * 
	 * @param id
	 * @param roomId
	 */
	public static void joinRoom(Integer id, String roomId) {
		synchronized (id) {
			synchronized (roomId) {
				// 防止一个人加入过多房间，防止一个房间加入过多的人
				if (canJoin(id)) {
					RoomToRoleMap.get(roomId).add(id);
					RoleToRoomMap.put(id, roomId);
					System.out.println("用户 " + id + " 成功加入了房间 " + roomId);
					try {
						// 模拟消耗时间
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					flag = true;
					System.out.println("改变房间状态 " + flag);
				} else {
					System.out.println("用户 " + id + " 加入房间 " + roomId + ",但是没成功");
				}
			}
		}
	}

	/**
	 * 退出房间
	 * 
	 * @param id
	 */
	public static void exitRoom(Integer id) {
		synchronized (id) {
			if (!RoleToRoomMap.containsKey(id)) {
				return;
			}
			String roomId = RoleToRoomMap.get(id);
			synchronized (roomId) {
				if (flag) {
					System.out.println("游戏人数够了，不支持退出房间");
					return;
				}
				RoomToRoleMap.get(roomId).remove(id);
				RoleToRoomMap.remove(id);
			}
		}
	}

	public static void printInfo() {
		for (String key : RoomToRoleMap.keySet()) {
			System.out.println("房间号 " + key + " 人数 " + RoomToRoleMap.get(key));
		}
	}

}
