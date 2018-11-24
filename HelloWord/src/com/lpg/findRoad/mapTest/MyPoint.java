package com.lpg.findRoad.mapTest;

public class MyPoint {

	private int x;
	
	private int y;
	
	public int direction;
	
	public MyPoint(int x,int y,int direction) {
		this.x=x;
		this.y=y;
		this.direction=direction;
	}
	
	public MyPoint(int x,int y) {
		this.x=x;
		this.y=y;
	}

	public void setY(int y) {
		this.y= y;
	}

	public void setX(int x) {
		this.x= x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
