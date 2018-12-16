package com.lpg.findRoad.mapTest;

/**
 * 视野上的矩形
 * @author lpg
 * 2018年11月27日
 */
public class Rectangle {

	/**
	 * 左边缘X坐标
	 */
	private int left;

	/**
	 * 右边缘X坐标
	 */
	private int right;

	/**
	 * 顶部边缘Y坐标
	 */
	private int top;

	/**
	 * 底部边缘Y坐标 
	 */
	private int bottom;

	public Rectangle() {
	}
	
	public static void main(String[] args) {
		
		
	}
	

	/**
	 * 构造矩形
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public Rectangle(int left, int top, int right, int bottom) {
		set(left, top, right, bottom);
	}
	
	/**
	 * 复制构造矩形
	 * 
	 * @param rect
	 */
	public Rectangle(Rectangle rect) {
		set(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom());
	}
	
	/**
	 * 
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 */
	public Rectangle(int x, int y, int radius) {
		set(x, y, radius);
	}
	
	/**
	 * 使用中心坐标和半径设置对称正方形
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @return
	 */
	public Rectangle set(int x, int y, int radius) {
		set(x - radius, y - radius, x + radius, y + radius);
		return this;
	}

	/**
	 * 设置矩形
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return
	 */
	public Rectangle set(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		return this;
	}
	
	/**
	 * 移动矩形
	 * 
	 * @param x X轴上的移动量
	 * @param y Y轴上的移动量
	 * @return
	 */
	public Rectangle move(int x, int y) {
		left += x;
		right += x;
		top += y;
		bottom += y;
		return this;
	}
	
	/**
	 * 移动矩形。返回移动后的新矩形，不改变当前矩形
	 * 
	 * @param x X轴上的移动量
	 * @param y Y轴上的移动量
	 * @return
	 */
	public Rectangle translate(int x, int y) {
		return new Rectangle(left + x, top + y, right + x, bottom + y);
	}

	/**
	 * 获取矩形的左边缘 X坐标。
	 * 
	 * @return
	 */
	public int getLeft() {
		return left;
	}

	/**
	 * 设置矩形的左边缘 X坐标。
	 * 
	 * @param left
	 */
	public void setLeft(int left) {
		this.left = left;
	}
	
	/**
	 * 获取矩形的顶部 Y坐标
	 * 
	 * @return
	 */
	public int getTop() {
		return top;
	}

	/**
	 * 设置矩形的顶部 Y坐标
	 * 
	 * @param top
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * 获取矩形的右边缘 X坐标。
	 * 
	 * @return
	 */
	public int getRight() {
		return right;
	}

	/**
	 * 设置矩形的右边缘 X坐标。
	 * 
	 * @param right
	 */
	public void setRight(int right) {
		this.right = right;
	}

	/**
	 * 矩形的底部Y坐标
	 * 
	 * @return
	 */
	public int getBottom() {
		return bottom;
	}

	/**
	 * 设置矩形的底部Y坐标
	 * 
	 * @param bottom
	 */
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	/**
	 * 矩形宽度
	 * 
	 * @return
	 */
	public int getWidth() {
		return right - left;
	}

	/**
	 * 矩形高度
	 * 
	 * @return
	 */
	public int getHeight() {
		return bottom - top;
	}
	
	/**
	 * 矩形面积（宽 * 高）
	 * 
	 * @return
	 */
	public int getArea() {
		return getWidth() * getHeight();
	}
	
	/**
	 * 检测两个矩形是否相交
	 * 
	 * @param vp
	 * @return
	 */
	public boolean intersect(Rectangle vp) {
		int minx = Math.max(vp.left, this.left);
		int miny = Math.max(vp.top, this.top);
		
		int maxx = Math.min(vp.right, this.right);
		int maxy = Math.min(vp.bottom, this.bottom);
		
		return !(minx > maxx || miny > maxy);
	}
	
	/**
	 * 检测指定的点是否包含在当前矩形中
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(int x, int y) {
		return x >= this.left && x <= right && y >= this.top && y <= this.bottom;
	}
	
	/**
	 * 检查指定的矩形是否包含在当前矩形中
	 * 
	 * @param vp
	 * @return
	 */
	public boolean contains(Rectangle vp) {
		return contains(vp.left, vp.top) && contains(vp.right, vp.bottom);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle vp = (Rectangle) obj;
			return vp.left == this.left && vp.top == this.top && vp.right == this.right && vp.bottom == this.bottom;
		}
		return false;
	}
}
