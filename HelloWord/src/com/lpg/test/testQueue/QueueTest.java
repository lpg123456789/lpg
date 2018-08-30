package com.lpg.test.testQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 测试队列功能，前面的没有移除，后面的进行阻塞
 * @author lpg
 * @date 2018年8月3日 
 */
public class QueueTest {
	
	private  BlockingQueue<TestData>  testQueue = new LinkedBlockingQueue<TestData>();
	
	public static void main(String[] args) {
			
		long nowMill=System.currentTimeMillis();
		int nowSecond=(int) (nowMill/1000);
		QueueTest q=new QueueTest();
		
		TestData t1=new TestData(nowSecond+30, "1111111111111111111111111");
		TestData t2=new TestData(nowSecond+50, "2222222222222222222222222");
		TestData t3=new TestData(nowSecond+40, "33333333333333333333333333");
		TestData t4=new TestData(nowSecond+60, "44444444444444444444444444");
		TestData t5=new TestData(nowSecond+10, "5555555555555555555555555");
		
		try {
			q.testQueue.put(t1);
			q.testQueue.put(t2);
			q.testQueue.put(t3);
			q.testQueue.put(t4);
			q.testQueue.put(t5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		q.MyRun();
	
	}
	
	public void MyRun() {
		while(!testQueue.isEmpty()) {
			TestData md = testQueue.peek();
			if (md == null) {
				break;
			}
			int intervalTime=(int) (md.getFinishTime()-System.currentTimeMillis()/1000);
			if (intervalTime>0) {
				try {
					System.out.println(md.getFinishTime()+"   阻塞 "+intervalTime);
					Thread.sleep(intervalTime*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			System.out.println("执行方法移除方法 ");
			TestData pmd = testQueue.poll();
			if (pmd != null) {
				System.out.println(pmd.toString());
			}
		}
	}  
}
