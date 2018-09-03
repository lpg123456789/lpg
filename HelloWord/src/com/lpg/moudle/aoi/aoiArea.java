package com.lpg.moudle.aoi;

/**
 * 数组测试网格九宫格视野
 * @author lpg
 * @date 2018年8月31日 
 */
public class aoiArea {

	
	static int[][] data = { 
			{ 0, 0, 1, 1, 1, 1, 0 }, 
			{ 0, 1, 1, 1, 1, 1, 0 }, 
			{ 0, 0, 1, 1, 1, 1, 0 },
			{ 1, 0, 0, 1, 0, 0, 1 }, 
			{ 1, 1, 0, 1, 0, 0, 0 }, };
	
	static int[][] data1 = { 
			{ 0, 0, 1, 1, 1, 1, 0 }, 
			{ 0, 1, 1, 1, 1, 1, 0 }, 
			{ 0, 0, 1, 1, 1, 1, 0 },
			{ 1, 0, 0, 1, 0, 0, 1 }, 
			{ 1, 1, 0, 1, 0, 0, 0 }, 
			{ 1, 1, 0, 0, 0, 1, 0 }, 
			{ 1, 1, 1, 1, 1, 1, 0 }, };
	
	
	public static void printView(int x,int y) {
		System.out.println("x和y的坐标分别是  "+x+","+y);
		for (int i = x-1; i <= x+ 1; i++) {
			if(i<0||i>data.length-1) {		
				System.out.println("i为"+i+" ,"+"越界continue");
				continue;
			}
//			for (int j = y-1; j <= y+1; j++) {	
//				
//				if(j<0||j>data[].length-1) {		
//					System.out.println("j为"+j+" ,"+"越界continue");
//					continue;
//				}
//				System.out.println("输出的 i和j为"+i+","+j);
//				System.out.println("输出的 i和j为"+i+","+j+" 值为 "+data[i][j]);
//			}
		}
	}
	
	/**
	 * 打印信息
	 */
	public static void pirintInfo() {
		System.out.println("data的  	x"+data[0].length+" y"+data.length);
	}
	
	public static void main(String[] args) {
		pirintInfo();
		
		for (int i = 0; i < data[0].length; i++) {
			for (int j = 0; j < data.length; j++) {
				printView(i,j);
				//System.out.print(data[i][j]+" ");
				System.out.println("***************************************************");
			}
			
		}
		
		//printView(0,0);
	}
	
}
