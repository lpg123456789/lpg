package com.lpg.jvm;

public class jvmTwo {

	// 一个OOMObject实例大概640k+
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024*10];
    }
    
    public static void fillHeap() throws InterruptedException {
        for(int i=0;i<100;i++){
            OOMObject oOmObject =new OOMObject();
            Thread.sleep(1000);
            oOmObject=null;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        fillHeap();
    }
	
}
