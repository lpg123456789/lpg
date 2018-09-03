package com.lpg.test.testReflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class app3 {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Logger logger = new Logger();
        
        Method m = logger.getClass().getMethod("cal", new Class[]{int.class,int.class});
  
        long result = 0;

        System.out.println(new Date());
        for (int i = 0; i < 2000000000; i++){
            Integer in = (Integer)m.invoke(logger, i,i);
            result += in.intValue();
        }

        System.out.println(result);
        System.out.println(new Date());
    }

}