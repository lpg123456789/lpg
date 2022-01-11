package com.lpg.jsoncompare;

/**
 * lpg
 * 2021年9月8日
 */
public class Obj {

	private String name;
	
	private String _age;
	
	private String $a;
	
	private String _ab;
	
	private boolean isCCC;//isA的判断
	
	private int temp;
	
	private Test test;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get_age() {
		return _age;
	}

	public void set_age(String _age) {
		this._age = _age;
	}

	public String get$a() {
		return $a;
	}

	public void set$a(String $a) {
		this.$a = $a;
	}

	public String getA_b() {
		return _ab;
	}

	public void setA_b(String a_b) {
		this._ab = _ab;
	}

	public int getTemp() {
		return temp+10;
	}

	public void setTemp(int temp) {
		this.temp = temp+2;
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	




	public boolean isCCC() {
		return isCCC;
	}

	public void setCCC(boolean isCCC) {
		this.isCCC = isCCC;
	}






	class Test {
		
		private String testA;

		public String getTestA() {
			return testA;
		}

		public void setTestA(String testA) {
			this.testA = testA;
		}
		
	}
	
	
}
