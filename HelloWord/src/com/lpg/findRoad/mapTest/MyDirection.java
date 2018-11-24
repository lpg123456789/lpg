package com.lpg.findRoad.mapTest;

public class MyDirection {
	
	/**
	 * 上
	 */
	public static final int UP = 0;
	
	/**
	 * 右上
	 */
	public static final int RIGHT_UP = 1;
	
	/**
	 * 右
	 */
	public static final int RIGHT = 2;
	
	/**
	 * 右下
	 */
	public static final int RIGHT_DOWN = 3;
	
	/**
	 * 下
	 */
	public static final int DOWN = 4;
	
	/**
	 * 左下
	 */
	public static final int LEFT_DOWN = 5;
	
	/**
	 * 左
	 */
	public static final int LEFT = 6;
	
	/**
	 * 左上
	 */
	public static final int LEFT_UP = 7;

	public static MyPoint translatePoint(int x, int y, int direction, int step) {
		MyPoint point = new MyPoint(x, y);
		switch (direction) {
		case MyDirection.UP:
			point.setX(x + step);
			break;
		case MyDirection.RIGHT_UP:
			point.setX(x + step);
			point.setY(y - step);
			break;
		case MyDirection.RIGHT:
			point.setY(y - step);
			break;
		case MyDirection.RIGHT_DOWN:
			point.setX(x - step);
			point.setY(y - step);
			break;
		case MyDirection.DOWN:
			point.setX(x - step);
			break;
		case MyDirection.LEFT_DOWN:
			point.setX(x - step);
			point.setY(y + step);
			break;
		case MyDirection.LEFT:
			point.setY(y + step);
			break;
		case MyDirection.LEFT_UP:
			point.setX(x + step);
			point.setY(y + step);
			break;
		}
		return point;
	}
}
