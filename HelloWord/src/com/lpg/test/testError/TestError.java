package com.lpg.test.testError;

import java.util.ArrayList;
import java.util.List;

public class TestError {

	public static void main(String[] args) {
		
		List<Integer> list=new ArrayList<>();
		for (int i = 0; i <100; i++) {
			System.out.println("添加值 "+i);
			list.add(i);
		}	
		for (int i = 0; i <100; i++) {
			int value=list.remove(i);//
			System.out.println("移除值 "+value+" 下标 "+i);
		}
	
	}

}
