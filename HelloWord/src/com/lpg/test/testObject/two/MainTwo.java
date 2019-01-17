package com.lpg.test.testObject.two;

public class MainTwo {

	public static void main(String[] args) {
		Father myObject = new UserMirror();
		testFather(myObject);
	}

	public static void testFather(Father myObject) {
		if (myObject instanceof Father) {
			System.out.println("属于Father");
		} else {
			System.out.println("不属于Father");
		}
		if (myObject instanceof Monster) {
			System.out.println("属于Monster");
		} else {
			System.out.println("不属于Monster");
		}
		if (myObject instanceof User) {
			System.out.println("属于User");
		} else {
			System.out.println("不属于User");
		}
		if (myObject instanceof UserMate) {
			System.out.println("属于UserMate");
		} else {
			System.out.println("不属于UserMate");
		}
		if (myObject instanceof UserMirror) {
			System.out.println("属于UserMirror");
		} else {
			System.out.println("不属于UserMirror");
		}
	}

}
