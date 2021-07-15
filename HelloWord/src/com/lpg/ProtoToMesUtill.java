/**
 * admin
 */
package com.lpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * lpg
 * 2019年12月27日
 */
public class ProtoToMesUtill {

	// pb协议路径
	public static final String pb_PATH = "E:/common/trunk/message/proto/";
	public static final String file = pb_PATH + "achievementMessage.proto";
	// mes路径
	public static final String SERVER_CONFIG = "E:/common/trunk/message/xml";
	public static final String file2 = pb_PATH + "achievementMessage.proto";

	private Map<Integer, String> readerMap = new HashMap<Integer, String>();

	public void init() {
		try {
			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(new File(file)));
			BufferedReader bf = new BufferedReader(inputReader);
			String str;
			int line = 1;
			while ((str = bf.readLine()) != null) {
				readerMap.put(line, str);
				line++;
			}
			bf.close();
			inputReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toMessage() {
		
		try {
			FileWriter fw=new FileWriter("D:\\name.txt");
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			fw.write("\r\n");
			fw.write("<messages package=\"com.game.func.achievement\" proto=\"AchievementProto\" id=\"125\">");
			fw.write("\r\n");
			for(Integer line:readerMap.keySet()) {
	
				String currentStr=readerMap.get(line);
				if(currentStr.startsWith("message Req")) {
					String name=currentStr.split(" ")[1];
					name=name.substring(0,name.length()-1);
					fw.write("\r\n");
					String lastStr=readerMap.get(line-1);
					String[] str=lastStr.split(" ");
					String explain=str[1];
					String id=str[2].split("=")[1].substring(2);
					System.out.println(str[0]+" 		"+str[1]+"		 "+str[2]);
					fw.write("\t");
					String text="<message id=\""+id+"\" name=\""+name+"\" type=\"CS\" explain=\""+explain+"\">";
					//fw.write("<message id=\"101\" name=\"ReqAchievementNewMsg\" type=\"CS\" explain=\"请求成就是否有新消息（进入主界面，红点显示）\">");
					fw.write(text);
					fw.write("\r\n");
					fw.write("\t");
					fw.write("</message>");
					fw.write("\r\n");
				}
				
				if(currentStr.startsWith("message Res")) {
					fw.write("\r\n");
					String lastStr=readerMap.get(line-1);
					String[] str=lastStr.split(" ");
					fw.write("\t");
					fw.write("<message id=\"101\" name=\"ReqAchievementNewMsg\" type=\"CS\" explain=\"请求成就是否有新消息（进入主界面，红点显示）\">");
					fw.write("\r\n");
					fw.write("\t");
					fw.write("</message>");
					fw.write("\r\n");
				}
				
			}
			fw.write("\r\n");
			fw.write("</messages>");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void runCmd() {		
		try {
			String[] envp = {"a","a"};
			Process pr=Runtime.getRuntime().exec("cmd.exe /c start E:/common/trunk/message/tool/proto/genjava2.bat",envp);
			pr.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProtoToMesUtill utill=new ProtoToMesUtill();
		utill.init();
		utill.toMessage();
	}

}
