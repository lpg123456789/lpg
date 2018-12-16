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
	 * 视野 
	 */
	private ViewDistance viewDistance=new ViewDistance();
	
	
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
	 * 如果没有的话，九宫格附件找一波
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
	 * 	获取视野范围 
	 */
	public void getView(int x,int y) {
		System.out.println("原始值     x为"+x+"  y为"+y);
		for (int i = x-viewDistance.getX(); i <= x+viewDistance.getX(); i++) {
			for (int j = y-viewDistance.getY(); j <= y+viewDistance.getY(); j++) {
				if(!this.isInView(i, j)) {
					map[i][j]=MapConstant.view;
				}
			}
		}
		map[x][y]=MapConstant.oneself;
	}
	
	/**
	 * 根据所在点获取矩形
	 */
	public Rectangle getRectangle(int x,int y) {
		int left = (int) Math.max(0, x - viewDistance.getX());
		int top = (int) Math.max(0, y - viewDistance.getY());
		int right = (int) Math.min(this.getXmax(), x + viewDistance.getX());
		int bottom = (int) Math.min(this.getYmax(), y + viewDistance.getY());
		Rectangle rectangle=new Rectangle(left, top, right, bottom);
		MarkRectangle(rectangle,x,y);
		return rectangle;
	}
	
	/**
	 * 打印矩形
	 * @param x
	 * @param y
	 */
	public void MarkRectangle(Rectangle rectangle,int x,int y) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(rectangle.contains(i, j)) {
					map[i][j]=MapConstant.rectangle;
				}
			}
		}
		map[x][y]=MapConstant.oneself;
	}
	
	/**
	 * 获取距离
	 * @param MyPoint1
	 * @param MyPoint2
	 */
	public void getDistance(MyPoint p1,MyPoint p2) {
		
		map[p1.getX()][p1.getY()]=MapConstant.begin;
		map[p2.getX()][p2.getY()]=MapConstant.end;
		
		double c = 0;
		double i = Math.pow((p1.getX() - p2.getX()), 2);
		double j = Math.pow((p1.getY() - p2.getY()), 2);
		c = Math.sqrt(i + j);
		
		System.out.println(c);
	}
	
	/**
	 * 获取旧格子
	 */
	public void getOldGrid() {
		
	}
	
	/**
	 * 获取新格子
	 */
	public void getNewGrid() {
		
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
	 * 是否在视野内
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInView(int x, int y) {
		if(x < 0 || x > getXmax() || y < 0 || y > getYmax()) {
			return true;
		}
		return false;
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
