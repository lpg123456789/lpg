package com.lpg.moudle.gift;

public class NewPlayerGift extends AGift {

	public static NewPlayerGift instance=new NewPlayerGift();
	
	private NewPlayerGift() {
		
	}

	@Override
	public int getFlag() {
		return GiftType.newPlayerGift;
	}

	@Override
	public boolean canGetGift(long userId, String giftId) {
		doSomething();
		return false;
	}
	
	private void doSomething() {
		
	}
	
}
