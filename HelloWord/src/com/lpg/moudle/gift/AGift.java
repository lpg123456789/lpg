package com.lpg.moudle.gift;

public abstract class AGift {

	public abstract int getFlag();

	public abstract boolean canGetGift(long userId,String giftId);
}
