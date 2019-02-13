package com.lpg.myTool.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Timetake {

	public static void main(String[] args) {
		String filePath = "E:\\李培光简历.doc";
		// String filePath = "F:\\filetest\\2017-02-06.txt";//路径这样写也可以的
		File file = new File(filePath);
		String time = Timetake.getFileCreateDate(file);
		System.out.println("Timetake.main time=" + time);
	}

	/**
     * @param _file _file
     * @return datetime datetime
     * 测试发现这个方法好用，在 windows环境下
     */
    public static String getFileCreateDate(File _file) {
        File file = _file;
        try {
            Process ls_proc = Runtime.getRuntime().exec("cmd.exe /c dir " + file.getAbsolutePath() + " /tc");
            BufferedReader br = new BufferedReader(new InputStreamReader(ls_proc.getInputStream()));
            for (int i = 0; i < 5; i++) {
                br.readLine();
            }
            String stuff = br.readLine();
            if (stuff == null) {
                return "";
            }
            StringTokenizer st = new StringTokenizer(stuff);
            String dateC = st.nextToken();
            String time = st.nextToken();
            String datetime = dateC.concat(time);
            br.close();
            return datetime;
        } catch (Exception e) {
            return null;
        }
    }

}
