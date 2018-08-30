package com.lpg.test.testList;

public class TestData {

	public String data;
	
	public long time;

	public TestData(String data, long time) {
		this.data = data;
		this.time = time;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TestData [data=" + data + ", time=" + time + "]";
	}

}
