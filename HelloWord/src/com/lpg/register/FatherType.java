package com.lpg.register;

public abstract  class FatherType {

	public FatherType(){
		Center.reg(getHuId(), this);
	}
	
	public abstract int getHuId();
	
	public abstract boolean test();
}
