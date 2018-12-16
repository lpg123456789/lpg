package com.lpg.test.testList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRand {

	public static void main(String[] str) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}
		System.out.println("orginal List:");
		for (Integer s : list) {
			System.out.println(s);
		}
		System.out.println();

		Collections.shuffle(list);
		System.out.println("after shuffle List:");
		for (Integer s : list) {
			System.out.println(s);
		}
	}

}
