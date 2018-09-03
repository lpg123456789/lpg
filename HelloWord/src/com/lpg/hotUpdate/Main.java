package com.lpg.hotUpdate;

import java.lang.management.ManagementFactory;

import com.sun.tools.attach.VirtualMachine;

public class Main {

	public static void main(String[] args) throws Exception {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		//这里为了方便测试，打印出来进程id
		String pid = name.split("@")[0];
		System.out.println("进程Id：" + pid);
		test();
			
		lpg(pid);
		//热更新执行之后，再次使用这个类
		test();

	}
	
	//jar包要记得配置相关的信息
	public static void lpg(String pid) throws Exception{
		// 这个pid在实际应用中可以通过args参数传进来
//		/String pid = "9228";
		// VirtualMachine是jdk中tool.jar里面的东西，所以要在pom.xml引用这个jar
		VirtualMachine vm = VirtualMachine.attach(pid);
		// 这个路径是相对于被热更的服务的，也就是这个pid的服务，也可以使用绝对路径。
		vm.loadAgent("config\\myLoadAgent.jar","XXXXXXXXXXXXXXXXXXX");
		// vm.detach();
		// Thread.sleep(300000);
	}

	public static void test() {
		TestHot testHot = new TestHot();
		testHot.print();
	}
}
