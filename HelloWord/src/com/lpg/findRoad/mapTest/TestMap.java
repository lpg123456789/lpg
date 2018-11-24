package com.lpg.findRoad.mapTest;

/**
 *  获取方向。0：上 ，1：右上， 2：右 ，3：右下 ，4：下 ，5：左下 ，6：左 ，7：左上。
 * 用数字标志位置
 * 0表示障碍点，1表示
 * @author lpg
 * 2018年11月23日
 */
public class TestMap {
	
	/**
	 * 列
	 */
	private int cols;
	
	/**
	 * 行
	 */
	private int rows;

	/**
	 * 地图的格子数据
	 */
	private String[][] map=null;
	
	/**
	 * 传参数
	 */
	public TestMap(int rows,int cols) {
		this.rows=rows;
		this.cols=cols;
		initMap();
	}
	
	/**
	 * 地图初始化
	 */
	private void initMap() {
		map=new String[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = MapConstant.pass;
			}
		}
	}
	
	/**
	 * 处理地图
	 */
	public void dealMap() {
		map[0][0]=MapConstant.barrier;
		map[0][1]=MapConstant.barrier;
		map[0][2]=MapConstant.barrier;
	}
	
	/**
	 * 显示打印
	 */
	public void show() {
		for (int i = 0;i<this.rows;i++){
            for (int j = 0;j<this.cols;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }
	}
	
	
	/**
	 * 获取指定位置后面的1个位置的点
	 */
	public void getFollow(int x,int y,int direction) {
		System.out.println("原始值     x为"+x+"  y为"+y+" 方向为"+direction);
		if(!this.isBlock(x, y)) {
			map[x][y]=MapConstant.pos;
		}
		boolean flag=false;
		MyPoint myPoint=MyDirection.translatePoint(x, y, direction, 1);
		if(!this.isBlock(myPoint.getX(), myPoint.getY())) {
			map[myPoint.getX()][myPoint.getY()]=MapConstant.target;
			flag=true;
		}
		//位置可能是障碍点，如果是障碍点，则去自己九宫格附近的
		if(!flag) {
			MyPoint nearPoint=getNearPoint(x, y);
			map[nearPoint.getX()][nearPoint.getY()]=MapConstant.target;
		}
		
	}
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public MyPoint getNearPoint(int x,int y) {
		for(int i=x-1;i<=x+1;i++) {
			for(int j=y-1;j<=y+1;j++) {
				if(!this.isBlock(i, j)) {
					return new MyPoint(i, j);
				}
			}
		}
		return new MyPoint(x, y);
	}
	
	
	/**
	 * 是否是障碍点
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isBlock(int x, int y) {
		if(x < 0 || x > getXmax() || y < 0 || y > getYmax()) {
			return true;
		}
		return map[x][y]==MapConstant.barrier;
	}
	
	/**
	 * 行最大
	 * @return
	 */
	private int getXmax() {
		return rows-1;
	}

	/**
	 * 列最大
	 * @return
	 */
	private int getYmax() {
		return cols-1;
	}

}
