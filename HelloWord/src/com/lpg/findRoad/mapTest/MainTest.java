package com.lpg.findRoad.mapTest;

public class MainTest {

	public static void main(String[] args) {	
		//创建地图
		TestMap testMap=new TestMap(7,10);
		testMap.dealMap();
		System.out.println("**************方法执行前打印地图********************");
		testMap.show();
		testMap.getFollow(1, 1, 3);
		System.out.println("**************方法执行后打印地图********************");
		testMap.show();
	}
	
}
