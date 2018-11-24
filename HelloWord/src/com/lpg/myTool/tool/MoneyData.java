package com.lpg.myTool.tool;

public class MoneyData {
	
	public String day;

	public String zhiFuBao;
	
	public String weiXin;
	
	public String gongShang;
	
	public String zhongGuo;
	
	public String zhaoShang;
	
	public String desc;
	
	public MoneyData(String zhiFuBao,String weiXin,String gongShang,String zhongGuo,String zhaoShang,String desc) {
		this.day = MoneyFile.getTodayStr();
		this.zhiFuBao=zhiFuBao;
		this.weiXin=weiXin;
		this.gongShang=gongShang;
		this.zhongGuo=zhongGuo;
		this.zhaoShang=zhaoShang;
		this.desc=desc;
	}
	
	public MoneyData(String day) {
		this.day=day;
	}
	
	public String toFileData() {
		StringBuilder sb=new StringBuilder();
		sb.append("支付宝 ：").append(zhiFuBao).append("\r\n");
		sb.append("微信 ：").append(weiXin).append("\r\n");
		sb.append("工商银行 ：").append(gongShang).append("\r\n");
		sb.append("中国银行 ：").append(zhongGuo).append("\r\n");
		sb.append("招商银行 ：").append(zhaoShang).append("\r\n");
		sb.append("备注 ：").append(desc).append("\r\n");
		return sb.toString();
	}
	
	public void initData(String str) {
		if(str.startsWith("支付宝")) {
			this.zhiFuBao=getRealData(str);
		}
		if(str.startsWith("微信")) {
			this.weiXin=getRealData(str);
		}
		if(str.startsWith("工商银行")) {
			this.gongShang=getRealData(str);
		}
		if(str.startsWith("中国银行")) {
			this.zhongGuo=getRealData(str);
		}
		if(str.startsWith("招商银行")) {
			this.zhaoShang=getRealData(str);
		}
		if(str.startsWith("备注")) {
			this.desc=getRealData(str);
		}
	}
	
	public String getRealData(String str) {
		if(str==null) {
			return "";
		}
		String[] data=str.split("：");
		if(data.length!=2) {
			return "";
		}
		return data[1];
	}
		
}
