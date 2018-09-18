package com.lpg.utils;

import java.math.BigDecimal;

/**
 * 数学工具类
 * 
 * @author Brant
 * @mail brtcoder@163.com
 * @date 2018年1月3日 下午5:31:53
 */
public class MathUtils {

	/**
	 * 使用两点式直线公式，获取直线上指定Y坐标的点的X坐标。两点式直线方程：(y-y1)/(y2-y1)=(x-x1)/(x2-x1)
	 * 
	 * @param startX 起始点的X坐标
	 * @param startY 起始点的Y坐标
	 * @param endX 终止点的X坐标
	 * @param endY 终止点的Y坐标
	 * @param y 要求X坐标的点的Y坐标
	 * @return 返回X坐标
	 */
	public static int getXposInLine(int startX, int startY, int endX, int endY, int y) {
		// 如果是垂直于X轴的直线，则直接返回起始点的X坐标
		if (startX == endX) {
			return startX;
		}

		// 如果是垂直于Y轴的直线，则凭y值无法确定X值。此时返回0值
		if (startY == endY) {
			// 对于垂直于
			if (y != startY) {
				throw new IllegalArgumentException("Y：" + y + " out of line");
			}
			return 0;
		}

		int x = (y - startY) / (endY - startY) * (endX - startX) + startX;
		return x;
	}

	/**
	 * 使用两点式直线公式，获取直线上指定X坐标的点的Y坐标。两点式直线方程：(y-y1)/(y2-y1)=(x-x1)/(x2-x1)
	 * 
	 * @param startX 起始点的X坐标
	 * @param startY 起始点的Y坐标
	 * @param endX 终止点的X坐标
	 * @param endY 终止点的Y坐标
	 * @param x 要求Y坐标的点的X坐标
	 * @return 返回Y坐标
	 */
	public static int getYposInLine(int startX, int startY, int endX, int endY, int x) {
		// 如果是垂直于X轴的直线，则凭x值无法确定y值。此时返回0值
		if (startX == endX) {
			// 对于垂直于X轴的直线，如果给定的 x 坐标不等于起始点的坐标，则说明改点不在直线上，此时返回null
			if (x != startX) {
				throw new IllegalArgumentException("X：" + x + " out of line");
			}
			return 0;
		}

		// 如果是垂直于Y轴的直线，直线上所有点的y坐标都相同
		if (startY == endY) {
			return startY;
		}

		int y = (x - startX) / (endX - startX) * (endY - startY) + startY;
		return y;
	}

	/**
	 * 获取将point围绕center旋转后的新坐标点
	 * 
	 * @param center
	 * @param point
	 * @param angle
	 * @return
	 */
//	public static Point rotatePoint(Point center, Point point, int angle) {
//		/*
//		 * 设 (x1, y1) 为要旋转的点，(x2, y2) 为中心点，顺时针旋转角度为θ，则旋转后的新点(x, y) 的坐标公式如下：
//		 * 
//		 * x = (x1 - x2)cosθ - (y1 - y2)sinθ + x2
//		 * y = (y1 - y2)cosθ + (x1 - x2)sinθ + y2
//		 */
//		double sin = Math.sin(angle);
//		double cos = Math.cos(angle);
//
//		double dx = (point.getX() - center.getX()) * cos - (point.getY() - center.getY()) * sin + center.getX();
//		double dy = (point.getY() - center.getY()) * cos + (point.getX() - center.getX()) * sin + center.getY();
//
//		return new Point((int) Math.round(dx), (int) Math.round(dy));
//	}
	
	/**
	 * 获取指定位数的数值的最大值
	 * 
	 * @param num
	 * @return
	 */
	public static long getBitMax(int num) {
		return (long) Math.pow(2, num) - 1;
	}
	
	/**
	 * 将数值value限制在min和max之间。如果小于min，取min。如果大于max，取max
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static int between(int value, int min, int max) {
		if(value < min) {
			return min;
		}
		if(value > max) {
			return max;
		}
		return value;
	}
	
	/**
	 * 将数值value限制在min和max之间。如果小于min，取min。如果大于max，取max
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static double between(double value, double min, double max) {
		if(value < min) {
			return min;
		}
		if(value > max) {
			return max;
		}
		return value;
	}
	
	/**
	 * 将数值value限制在min和max之间。如果小于min，取min。如果大于max，取max
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static long between(long value, long min, long max) {
		if(value < min) {
			return min;
		}
		if(value > max) {
			return max;
		}
		return value;
	}
	
	/**
	 * 检测点(tx, ty) 是否在以点 (x, y) 为中心，以radius为半径的正方形区域内
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param tx
	 * @param ty
	 * @return
	 */
	public static boolean inRange(int x, int y, int radius, int tx, int ty) {
		int left = x - radius;
		int right = x + radius;
		int top = y - radius;
		int bottom = y + radius;
		
		return tx >= left && tx <= right && ty >= top && ty <= bottom;
	}
	
	/**
	 * 检测点(tx, ty) 是否在以点 (x, y) 为中心，以xRadius为X半径，已yRadius为Y半径的矩形区域内
	 * 
	 * @param x
	 * @param y
	 * @param xRadius
	 * @param yRadius
	 * @param tx
	 * @param ty
	 * @return
	 */
	public static boolean inRange(int x, int y, int xRadius, int yRadius, int tx, int ty) {
		int left = x - xRadius;
		int right = x + xRadius;
		int top = y - yRadius;
		int bottom = y + yRadius;
		
		return tx >= left && tx <= right && ty >= top && ty <= bottom;
	}
	
	/**
	 * 精确到小数点后N位。
	 * 
	 * @param num 要处理的双精度浮点型数字
	 * @param digits 精度（即精确到小时点后第几位）
	 * @return
	 */
	public static double toFixed(double num, int digits) {
		return new BigDecimal(num).setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 精确到小数点后N位。
	 * 
	 * @param num 要处理的单精度浮点型数字
	 * @param digits 精度（即精确到小时点后第几位）
	 * @return
	 */
	public static float toFixed(float num, int digits) {
		return new BigDecimal(num).setScale(digits, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
