/**
 * admin
 */
package com.lpg.lambdaTest;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * lpg
 * 2019年12月23日
 */
public class C {

	public static void main(String[] args) {
		
		C c=new C();
		c.test6();
		c.test7();
		c.test8();

	}

	public void test6() {
		Supplier<String> supplier = () -> "532323".substring(0, 2);
		System.out.println(supplier.get());
	}

	public void test7() {
		Function<String, String> function = (x) -> x.substring(0, 2);
		System.out.println(function.apply("我是中国人"));
	}

	public void test8() {
		Predicate<String> predicate = (x) -> x.length() > 5;
		System.out.println(predicate.test("12345678"));
		System.out.println(predicate.test("123"));
	}
}
