package com.lpg.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Task2 {

	// 优先级1：可领取奖励>未完成>已领取奖励
	// 优先级2：优先级1相同，按照排序配置，排序越小越靠上
	private int taskId;// 任务id
	private int type;// 类型
	private int flag;// 可领取奖励 1>未完成 2>已领取奖励3

	public Task2(int taskId, int type, int flag) {
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

	public static void main(String[] args) {
		List<Task2> taskList = new ArrayList<Task2>();
		Task2 task1 = new Task2(100, 4, 1);
		Task2 task2 = new Task2(101, 1, 3);
		Task2 task3 = new Task2(102, 2, 2);
		Task2 task4 = new Task2(103, 3, 2);
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);

		Collections.sort(taskList, new Comparator<Task2>() {
			@Override
			public int compare(Task2 o1, Task2 o2) {
				// 如果number不等按number排序（增序
				if (o1.getFlag() != o2.getFlag()) {
					return o1.getFlag() > o2.getFlag() ? 1 : -1;
				} else {
					return o1.getType() > o2.getType() ? 1 : -1;
				}
			}
		});

		for (Task2 task : taskList) {
			System.out.println(task.toString());
		}
	}
}
