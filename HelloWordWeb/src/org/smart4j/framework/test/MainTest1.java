package org.smart4j.framework.test;
 
/**
 * Created by liangboqun on 2017/6/27.
 */
public class MainTest1 {
    public static void main(String [] args){
        Hello helloProxy = new HelloProxy();
        helloProxy.sayHello("JACK");
    }
}
