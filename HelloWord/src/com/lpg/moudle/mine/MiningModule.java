package com.lpg.moudle.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 挖矿系统逻辑类
 * @author yzc
 *
 */
public class MiningModule{
	
	/**
	 * 当前挖矿的矿工人数
	 */
	private AtomicInteger currentNum = new AtomicInteger();
	
	/**
	 * 挖矿人数-1,挖矿结束-1
	 * @return
	 */
	public int decreaseMining() {
		return this.currentNum.decrementAndGet();
	}
	
	/**
	 * 请求挖矿
	 * @param user
	 * @param type
	 */
	public void miningDig(long uid) {
		
	}
	
	/**
	 * 队列完成
	 * @param uid
	 */
	public static void completeMining(long uid){
		
	}
	
	

}
