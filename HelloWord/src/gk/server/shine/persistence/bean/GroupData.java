package gk.server.shine.persistence.bean;

import gk.server.shine.persistence.PersistenceData;

public class GroupData implements PersistenceData{
	// TODO dataId大区服不唯一,合服时需要清空处理

	/**
	 * dataId GroupDataType
	 */
	private int dataId;
	/**
	 * 服务器数据
	 */
	private String data = "";

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public Object getPrimaryKey() {
		return dataId;
	}
}
