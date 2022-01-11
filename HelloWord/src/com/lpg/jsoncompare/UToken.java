package com.game;
import java.util.Map;
import java.util.HashMap;
public class UToken {

	private boolean isSuc=true;
	private boolean isSwitchAccount=true;
	private boolean isShowCenter=true;
	private boolean isForceAccount=true;
	private String userType;
	private String openId;	
	private String timestamp;
	private String serverSign;	
	private String subKey;
	private String extraData;
	private boolean isShowSwitchAccount;
	private Map<String,Object> paramsMap;
	public UToken() {
		this.isSuc = false;
		this.paramsMap = new HashMap<>();
	}

	public UToken(boolean isSuc, boolean isSwitchAccount, boolean isShowCenter,
			String userType, String openId, String timestamp, String serverSign, String subKey, String extraData,boolean isForceAccount,boolean isShowSwitchAccount) {
		this.isSuc = isSuc;
		this.isSwitchAccount = isSwitchAccount;
		this.isShowCenter = isShowCenter;
		this.userType = userType;
		this.openId = openId;
		this.timestamp = timestamp;
		this.serverSign = serverSign;
		this.subKey = subKey;
		this.extraData = extraData;
		this.isForceAccount = isForceAccount;
		this.isShowSwitchAccount = isShowSwitchAccount;
		this.paramsMap = new HashMap<>();
	}

	public void setParam(String key,Object value){
		this.paramsMap.put(key , value);
	}

	public Object getParam(String key){
		return this.paramsMap.get(key);
	}
	
	public boolean isSuc() {
		return isSuc;
	}

	public void setSuc(boolean isSuc) {
		this.isSuc = isSuc;
	}

	public boolean isSwitchAccount() {
		return isSwitchAccount;
	}
	
	public boolean isForceAccount() {
		return isForceAccount;
	}

	public void setSwitchAccount(boolean isSwitchAccount) {
		this.isSwitchAccount = isSwitchAccount;
	}

	public boolean isShowCenter() {
		return isShowCenter;
	}

	public void setShowCenter(boolean isShowCenter) {
		this.isShowCenter = isShowCenter;
	}
	
	public void setForceAccount(boolean isForceAccount) {
		this.isForceAccount = isForceAccount;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getServerSign() {
		return serverSign;
	}

	public void setServerSign(String serverSign) {
		this.serverSign = serverSign;
	}
	
	public String getSubKey() {
		return subKey;
	}

	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}
	
	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public boolean isShowSwitchAccount() {
		return isShowSwitchAccount;
	}

	public void setShowSwitchAccount(boolean isShowSwitchAccount) {
		this.isShowSwitchAccount = isShowSwitchAccount;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}
	
	
}
