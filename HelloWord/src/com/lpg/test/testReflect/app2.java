package com.lpg.test.testReflect;

import java.util.Date;

public class app2 {

    public static void main(String[] args) {

        Logger logger = new Logger();

        long result = 0;

        System.out.println(new Date());
        for (int i = 0; i < 2000000000; i++){
            result += logger.cal(i,i);
        }

        System.out.println(result);
        System.out.println(new Date());
       
    }

}