package com.lpg.moudle.enumProps;

import java.util.HashMap;
import java.util.Map;

public enum EnumPropsType {
	
	ONE(1) {
        @Override
        public void apply() {
            System.out.println("类型1");
        }
    };
	
	private static Map<Integer,EnumPropsType> map=new HashMap<>();
	
	static {
		
		EnumPropsType[] test = EnumPropsType.values();
		
		for (EnumPropsType enumTest : test) {
			map.put(enumTest.getType(), enumTest);
		}
		
	}
	
	public static EnumPropsType getEnumPropsType(int type) {
		return map.get(type);
	}

	public abstract void apply();

    private Integer type;

    EnumPropsType(Integer id) {
        this.type = id;
    }

    public Integer getType() {
        return type;
    }
    
    
}
