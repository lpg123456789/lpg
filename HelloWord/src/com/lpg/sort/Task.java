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

	//这样容易搞复杂
	//正数增序，负数将序
//	@Override
//	public int compareTo(Task task) {
//		 // 如果number不等按number排序（增序
//        if (this.getFlag() != task.getFlag()) {
//            return this.getFlag() > task.getFlag() ? 1 : -1;
//        } else {
//        	return this.getType() > task.getType() ? 1:  -1;
//        }
//	}
	
	
	/* 
	 * 首先按照flag增序，然后按照type增序
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Task task) {
		if(this.getFlag() > task.getFlag()) {
			return 1;
		}else if(this.getFlag() == task.getFlag()) {
			if(this.getType() > task.getType()) {
				return 1;
			}else {
				return -1;
			}
		}else {
			return -1;
		}
	}

	public static void main(String[] args) {
		List<Task> taskList=new ArrayList<>();
		Task task1=new Task(100, 4, 1);
		Task task2=new Task(101, 1, 3);
		Task task3=new Task(102, 2, 2);
		Task task4=new Task(103, 4, 9);
		Task task5=new Task(104, 1, 11);
		Task task6=new Task(105, 5, 23);
		Task task7=new Task(106, 8, 12);
		Task task8=new Task(107, 9, 11);
		Task task9=new Task(108, 10, 22);
		Task task10=new Task(109, 11, 9);
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);
		taskList.add(task4);
		taskList.add(task5);
		taskList.add(task6);
		taskList.add(task7);
		taskList.add(task8);
		taskList.add(task9);
		taskList.add(task10);
		Collections.sort(taskList);
		
		for (Task task : taskList) {
			System.out.println(task.toString());
		}
	}

	
}
