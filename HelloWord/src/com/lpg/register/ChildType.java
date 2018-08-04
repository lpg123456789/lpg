package com.lpg.register;

public class ChildType extends FatherType {

	@Override
	public int getHuId() {
		return 1;
	}

	@Override
	public boolean test() {
		return false;
	}

}