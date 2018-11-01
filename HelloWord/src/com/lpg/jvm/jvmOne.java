package com.lpg.jvm;

import java.util.ArrayList;
import java.util.List;

public class jvmOne {

	//一个OOMObject实例大概64k+
    static class OOMObject{
        public byte[] placeholder= new byte[64*1024]; 
    }
    
    public static void fillHeap() throws InterruptedException {
        List<OOMObject> list =new ArrayList<OOMObject>();
        for(int i=0;i<1000;i++){ 
                        Thread.sleep(100);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception{
        fillHeap();
    }
	
}
