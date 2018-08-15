package com.lpg.moudle.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import com.lpg.utils.DateUtils;


/**
 * 采矿结束定时器
 * MiningTimer  
 * Description: 
 * @author xd
 * @date 2018年4月28日 
 *
 */
public class MiningTimer {
	
	private AtomicBoolean isRunning = new AtomicBoolean(false);
	
	//队列实现
	private LinkedBlockingQueue<MiningData>  miningQueue = new LinkedBlockingQueue<MiningData>();
	
	private ConcurrentHashMap<Long, MiningData> miningMap = new ConcurrentHashMap<Long, MiningData>();
	
	public static MiningTimer instance;
	
	public static MiningTimer getInstance() {
		if (instance == null) {
			instance = new MiningTimer();
		}
		return instance;
	}

	
	/**
	 * 采矿开始时添加采矿结束处理数据----开始采矿
	 * @param userId
	 * @param finishTime	结束时间，单位秒
	 */
	public void addMining(long userId,int finishTime) {
		MiningData md = new MiningData(userId,finishTime);
		this.miningQueue.add(md);
		this.miningMap.put(userId, md);
		
		if (isRunning.compareAndSet(false,true)) {
			//如果线程未启动，那么启动线程
			new Thread(new MiningTask()).start();
		}
	}
		
	/**
	 * 采矿任务
	 * MiningTask  
	 * Description: 
	 * @author xd
	 * @date 2018年4月28日 
	 *
	 */
	public class MiningTask implements Runnable {
		@Override
		public void run() {
			while(!miningQueue.isEmpty()) {
				MiningData md = miningQueue.peek();
				if (md == null) {
					break;
				}
				int nowTime = DateUtils.getCurrentSecond();
				if (md.finishTime > nowTime) {
					try {
						Thread.sleep((md.finishTime-nowTime)*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				MiningData pmd = miningQueue.poll();
				if (pmd != null) {
					miningMap.remove(pmd);
					MiningModule.completeMining(pmd.userId);			
				}
			}
			isRunning.set(false);
		}
	}
	
	
}

class MiningData {
	
	public long userId;
	
	public int finishTime;
	
	public MiningData(long userId,int finishTime) {
		this.userId = userId;
		this.finishTime = finishTime;
	}
}
