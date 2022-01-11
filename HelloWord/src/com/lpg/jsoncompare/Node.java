package com.game;

/**
 * lpg
 * 2021年9月13日
 */
public class Node {

	private String id;
	private String pId;
	private String name;
	private boolean open;

	Node() {
		super();
	}

	public Node(String id, String pId, String name, boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
