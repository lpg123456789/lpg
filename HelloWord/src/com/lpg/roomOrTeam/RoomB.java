package com.lpg.roomOrTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * B模拟一个人进了多个房间
 * @author Administrator
 */
public class RoomB {
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

		final Integer id = 88;
		for (int i = 0; i < 10; i++) {
			final int temp = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					joinRoom(id, "room" + temp);

				}
			}).start();
		}

		try {
			Thread.sleep(3000); // 休眠3秒，确保线程执行完毕
		} catch (Exception e) {

		}

		printInfo();
	}

	/**
	 * 初始化房间
	 */
	public static void initRoom() {
		for (int i = 0; i < 10; i++) {
			RoomToRoleMap.put("room" + i, new ArrayList<Integer>());
		}
		System.out.println("初始化了房间 " + RoomToRoleMap.size());
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
	 * 用户加入房间
	 * 
	 * @param id
	 * @param roomId
	 */
	public static void joinRoom(Integer id, String roomId) {
		if (canJoin(id)) {
			try {
				// 模拟消耗时间
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RoomToRoleMap.get(roomId).add(id);
			RoleToRoomMap.put(id, roomId);
		}
	}

	public static void printInfo() {
		for (String key : RoomToRoleMap.keySet()) {
			System.out.println("房间号 " + key + " 人数 " + RoomToRoleMap.get(key));
		}
	}

}