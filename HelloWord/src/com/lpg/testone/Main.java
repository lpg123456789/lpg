package com.lpg.testone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

	
	public static void main(String[] args) {
		
		try {
			Date date = new SimpleDateFormat("yyyyMMdd").parse("20190102");
			 Calendar current = Calendar.getInstance();
		        Calendar birthDay = Calendar.getInstance();
		        System.out.println(current==birthDay);
		        birthDay.setTime(date);
		        
		        System.out.println(birthDay.getTime().getYear()+" "+birthDay.get(Calendar.MONTH)+" "+birthDay.get(Calendar.DAY_OF_MONTH));
		        
		        Calendar b = Calendar.getInstance();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //j将一个Date数据写死
		
		
		 
	      
		
	}
	
	public static boolean checkAdult(Date date) {
        Calendar current = Calendar.getInstance();
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTime(date);
        Integer year = current.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (year > 18) {
            return true;
        } else if (year < 18) {
            return false;
        }
        // 如果年相等，就比较月份
        Integer month = current.get(Calendar.MONTH) - birthDay.get(Calendar.MONTH);
        if (month > 0) {
            return true;
        } else if (month < 0) {
            return false;
        }
        // 如果月也相等，就比较天
        Integer day = current.get(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH);
        return  day >= 0;
    }
}
