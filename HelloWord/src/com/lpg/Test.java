/**
 * admin
 */
package com.lpg;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * lpg
 * 2019年12月27日
 */
public class Test {

	public static void main(String[] args) {
		
		  String str = "1,2,3";
	        int[] a = Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
	        int[] src = {1,2,3,4,5,6,7,8,9,10};
	        List<Integer> list = Arrays.stream( src ).boxed().collect(Collectors.toList());

	        Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s));
		
	        Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s));
	        
	        List<Integer> b= Arrays.stream(str.split(",")) .map(s -> Integer.parseInt(s)).collect(Collectors.toList());
	        System.out.println(b);
		
		
		 ReadWriteLock rtLock = new ReentrantReadWriteLock();
		 try {
			 rtLock.writeLock().lock();
			rtLock.readLock().lock();
			 System.out.println("get readLock.");
			
			 System.out.println("blocking");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			rtLock.readLock().unlock();
			 rtLock.writeLock().unlock();
		}
		 

		 //Regex regex = new Regex("^\d{6}$", "11");
		 
		
		 boolean bool=Pattern.matches("^\\d{8}$","19901921");
		 System.out.println(bool);
	}
}
