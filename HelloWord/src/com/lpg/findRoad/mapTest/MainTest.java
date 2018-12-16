package com.lpg.findRoad.mapTest;

public class MainTest {

	public static void main(String[] args) {	
		//创建地图
		TestMap testMap=new TestMap(20,20);
		testMap.dealMap();
		System.out.println("**************方法执行前打印地图********************");
		testMap.show();
		//testMap.getView(8, 8);
		testMap.getDistance(new MyPoint(0, 3), new MyPoint(4, 6));
		System.out.println("**************方法执行后打印地图********************");
		testMap.show();
	}
	
}
