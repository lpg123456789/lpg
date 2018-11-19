package com.lpg.test.testObject.one;

public class ClassFather {

	public MyObject myObject1;
	
	public MyObject myObject2=new MyObject();
	
	public ClassFather(MyObject myObject){	
		this.myObject1=myObject;
	}
	
	public void calc(){
		myObject1.age--;
		myObject2.age++;
	}
	
	public void print(){
		System.out.println(this.getClass().getName()+"类处理");
		System.out.println("myObject1的值为 "+myObject1.age+" 地址是"+myObject1.toString());
		System.out.println("myObject2的值为 "+myObject2.age+" 地址是"+myObject2.toString());
	}
	
}
