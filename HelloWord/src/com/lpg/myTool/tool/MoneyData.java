package com.lpg.myTool.tool;

public class MoneyData {
	
	public boolean flag=false;
	
	public String day="";

	public String zhiFuBao="";
	
	public String weiXin="";
	
	public String gongShang="";
	
	public String zhongGuo="";
	
	public String zhaoShang="";
	
	public String desc="";
	
	public String detail="";
	
	public String totalMoney="";
	
	public MoneyData(String zhiFuBao,String weiXin,String gongShang,String zhongGuo,String zhaoShang,String desc,String detail) {
		this.day = MoneyFile.getTodayStr();
		this.zhiFuBao=zhiFuBao;
		this.weiXin=weiXin;
		this.gongShang=gongShang;
		this.zhongGuo=zhongGuo;
		this.zhaoShang=zhaoShang;
		this.desc=desc;
		this.detail=detail;
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
		sb.append("总额 ：").append(getTotal()).append("\r\n");
		sb.append("备注 ：").append(desc).append("\r\n");
		sb.append("\r\n");
		sb.append("*****************************").append("\r\n");
		sb.append("\r\n");
		sb.append("具体 ：").append("\r\n").append(detail).append("\r\n");
		return sb.toString();
	}
	
	public int getTotal(){
		return StrToInt(zhiFuBao)+StrToInt(weiXin)+StrToInt(gongShang)+StrToInt(zhongGuo)+StrToInt(zhaoShang);
	}
	
	public int StrToInt(String str){
		return Integer.parseInt(str);
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
		if(str.startsWith("总额")) {
			this.totalMoney=getRealData(str);
		}
		if(str.startsWith("备注")) {
			this.desc=getRealData(str);
		}
		if(str.startsWith("备注")) {
			this.desc=getRealData(str);
		}
		//打印具体的信息
		if(str.startsWith("具体")){
			flag=true;
			return;
		}
		if(flag){
			StringBuilder sb=new StringBuilder();
			sb.append(str).append("\r\n");
			detail+=sb.toString();
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
