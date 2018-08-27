package com.lpg.moudle.enumProps;

public class Main {

	public static void main(String[] args) {
		//场景：添加道具时，道具有大类小类
		//小类：添加金币，砖石，经验
		//大类：添加普通装备类，添加化身装备类
		
		int id=10;
		EnumProps enumProps=EnumProps.getEnumProps(id);
		if(enumProps!=null) {
			enumProps.apply();
		}else {
			int type=getTypeById(id);
			EnumPropsType enumPropsType=EnumPropsType.getEnumPropsType(type);
			if(enumPropsType!=null) {
				enumPropsType.apply();
			}
		}
			
	}
	
	public static int getTypeById(int id) {
		return 1;
	}
	
}
