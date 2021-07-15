package com.lpg.Log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Hello {

	
	 private static Logger logger = LogManager.getLogger(Hello.class.getName());
	 
	    public void getHello() {
	 
	      
	        logger.trace("我是trace");
	        logger.info("我是info信息");
	        logger.error("我是error");
	        logger.fatal("我是fatal");
	 
	        logger.trace("退出程序.");
	        
	    }
	 
	    public static void main(String[] args) {
	        new Hello().getHello();
	    }

	
}
