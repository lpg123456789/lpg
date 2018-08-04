package com.lpg.testQueue;

public class TestData {

	private int finishTime = 0;
	private String data = "";

	public TestData(int finishTime, String data) {
		this.finishTime = finishTime;
		this.data = data;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TestData [finishTime=" + finishTime + ", data=" + data + "]";
	}
	
}