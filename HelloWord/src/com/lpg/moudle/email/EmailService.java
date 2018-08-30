package com.lpg.moudle.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailService {

	public Map<Long,List<Email>> emailMap=new HashMap<>();
	
	/**
	 * 邮件主界面
	 * @param userId
	 */
	public void main(long userId) {
		emailMap.get(userId);
	}
	
	
	/**
	 * 读邮件
	 * @param userId
	 * @param id
	 */
	public void readEmail(int userId,int id) {
		
	}
	

	/**
	 * 获取邮件道具
	 * @param userId
	 * @param id
	 */
	public void getEmailProps(int userId,int id) {
		
	}
	
	
	/**
	 * 删除邮件
	 * @param userId
	 * @param id
	 */
	public void deleteEmail(int userId,int id) {
		
	}
}
