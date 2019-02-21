package com.lpg.regularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则其实就是根据字符模式与所搜索的字符串进行匹配
//https://www.cnblogs.com/zhao1949/p/6064277.html
public class Test {

	public static void main(String[] args) {
		test5();
	}

	public static void test1() {
		// matches()判断字符串是否匹配某个表达式，"."表示任何一个字符
		p("abcc".matches("...."));
		// 将字符串"a2389a"中的数字用*替换，\d 表示“0--9”数字 \其中一个是转义
		p("a2389a".replaceAll("\\d", "*"));
		// 将任何是a--z的字符串长度为3的字符串进行编译，这样可以加快匹配速度
		Pattern p = Pattern.compile("[a-z]{2}");
		// 进行匹配，并将匹配结果放在Matcher对象中
		Matcher m = p.matcher("ab");
		p(m.matches());
		// 上面的三行代码可以用下面一行代码代替
		p("abc".matches("[a-z]{3}"));
	}
	
	
	/**
	 * a* 指的是0次或多次都是a，如果有b就不匹配，如p("bb".matches("a*"));
	 * 长度还是要匹配上的
	 */
	public static void test2() {
		// 初步认识. * + ?
	//	p("a".matches("."));// 
	//	p("aa".matches("aa"));// 
	//	p("".matches("a*"));//  
	//	p("aaa".matches("a+"));// 
	//	p("b".matches("a*"));//
	//	p("aaaa".matches("a?"));// 
	//	p("".matches("a?"));// 
	//	p("a".matches("a?"));// 
	//	p("1232435463685899".matches("\\d{3,100}"));// 
	//	p("192.168.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));// 
	//	p("192".matches("[0-2][0-9][0-9]"));// 
	//	p("aaa".matches("a{3}?"));
	}
	
	public static void test3() {
		//范围
     //  p("a".matches("[abc]"));//true
     //  p("a".matches("[^abc]"));//false
     //  p("A".matches("[a-zA-Z]"));//true
     //  p("A".matches("[a-z]|[A-Z]"));//true
     //  p("A".matches("[a-z[A-Z]]"));//true
     //  p("R".matches("[A-Z&&[RFG]]"));//true
     //  p("a".matches("[a-d[m-p]]"));//true
        p("e".matches("[a-e&&[def]]"));//true
	}
	
	public static void test4() {
		   //边界匹配
        p("hello sir".matches("^h.*"));//true
        p("hello sir".matches(".*ir$"));//true
        p("hello sir".matches("^h[a-z]{1,3}o\\b.*"));//true
        p("hellosir".matches("^h[a-z]{1,3}o\\b.*"));//false
        //空白行:一个或多个(空白并且非换行符)开头，并以换行符结尾
        p(" \n".matches("^[\\s&&[^\\n]]*\\n$"));//true
	}
	
	public static void test5() {
		//email  [.-]并集
        p("asdsfdfagf@adsdsfd.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));//true
        
        //matches() find() lookingAt()
        Pattern p = Pattern.compile("\\d{3,5}");
        Matcher m = p.matcher("123-34345-234-00");
        
        //将整个"123-34345-234-00"用正则表达式引擎查找匹配，当到第一个"-"不匹配了，就停止，
        //但不会将不匹配的"-"吐出来
        p(m.matches());
        //将不匹配的"-"吐出来
        m.reset();
        
        //1:当前面有p(m.matches());查找子字符串从"...34345-234-00"开始
        //将会是第1,2两个查到"34345"和"234" 后面2个查不到为false
        //2:当前面有p(m.matches());和m.reset();查找子字符串从"123-34345-234-00"开始
        //将为true,true,true,false
          p(m.find());
          p(m.start()+"---"+m.end());
          p(m.find());
          p(m.start()+"---"+m.end());
          p(m.find());
          p(m.start()+"---"+m.end());
          p(m.find());
        //要是没找到就会报异常java.lang.IllegalStateException
          //p(m.start()+"---"+m.end());
        
        p(m.lookingAt());
        p(m.lookingAt());
       p(m.lookingAt());
       p(m.lookingAt());
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}