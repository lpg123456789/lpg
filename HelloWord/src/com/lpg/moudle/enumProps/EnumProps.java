package com.lpg.moudle.enumProps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public enum EnumProps {

	//金币
	ONE(1) {
        @Override
        public void apply() {
            System.out.println("one");
        }
    },
	//砖石
    TWO(2) {
        @Override
        public void apply() {
            System.out.println("two");
        }
    }, 
    //经验
    THREE(3) {
        @Override
        public void apply() {
            System.out.println("three");
        }
    };

    public abstract void apply();

    private Integer id;

    EnumProps(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
      
    private static Map<Integer,EnumProps> map=new HashMap<>();
    
	static {
		
		EnumProps[] test = EnumProps.values();
		for (EnumProps enumTest : test) {
			map.put(enumTest.getId(), enumTest);
		}
		
	}
	
	public static  EnumProps getEnumProps(Integer id) {
		return map.get(id);
	}
    
    public static void main(String[] args) {
    	
    	
	}

}
