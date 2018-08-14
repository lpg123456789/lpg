package com.lpg.moudle.redPocket;

import java.util.Random;

public class RedPocketMethods {

	public static void main(String[] args) {
		for (int num = 0; num < 50000; num++) {
			RedPocket redPocket=new RedPocket(10, 20000);	
			for (int i = 0; i < redPocket.getNum(); i++) {
				long userId=i;
				int diamond=RedPocketMethods.getRandomMoney2(redPocket);
				redPocket.addUser(userId,diamond);
			}
			redPocket.printInfo();
		}
	}
	
	/**
	 * https://www.cnblogs.com/lhat/p/6359039.html
	 * @param redPocket
	 * @return
	 */
	public static int getRandomMoney(RedPocket redPocket) { 
	    if (redPocket.getRemainSize()==1) {
	        return redPocket.getRemainMoney();
	    }
		Random random = new Random();
		int min = 1; //
		int max = redPocket.getRemainMoney() /redPocket.getRemainSize() * 2;//
		int money = random.nextInt() * max;//
		money = money <= min ? 1 : money;//
		return money;
	}
	
	public static int getRandomMoney2(RedPocket redPocket) { 
	    if (redPocket.getRemainSize()==1) {
	        return redPocket.getRemainMoney();
	    }
	    Random r  = new Random();
	    //double min   = 1; //
	    int min =(int) (redPocket.getRemainMoney() / redPocket.getRemainSize()*0.6);  
	    int max  = (int) (redPocket.getRemainMoney() / redPocket.getRemainSize() * 1.4);
	    //int money=RateUtils.rangeRandom(min, max);
	    int money=0;
	    return (int) money;
	}
	
	
}
