package com.lpg.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task implements Comparable<Task> {

	// 优先级1：可领取奖励>未完成>已领取奖励
	// 优先级2：优先级1相同，按照排序配置，排序越小越靠上
	private int taskId;// 任务id
	private int type;// 类型
	private int flag;// 可领取奖励 1>未完成 2>已领取奖励3

	public Task(int taskId, int type, int flag) {
		this.taskId = taskId;
		this.type = type;
		this.flag = flag;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "task [taskId=" + taskId + ", type=" + type + ", flag=" + flag + "]";
	}

	//正数增序，负数将序
	@Override
	public int compareTo(Task task) {
		 // 如果number不等按number排序（增序
        if (this.getFlag() != task.getFlag()) {
            return this.getFlag() > task.getFlag() ? 1 : -1;
        } else {
        	return this.getType() > task.getType() ? 1:  -1;
        }
	}

	public static void main(String[] args) {
		List<Task> taskList=new ArrayList<>();
		Task task1=new Task(100, 4, 1);
		Task task2=new Task(101, 1, 3);
		Task task3=new Task(102, 2, 2);
		Task task4=new Task(103, 3, 2);
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		Collections.sort(taskList);
		
		for (Task task : taskList) {
			System.out.println(task.toString());
		}
	}

	
}
