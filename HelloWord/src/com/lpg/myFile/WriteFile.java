package com.lpg.myFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteFile {

	public static void writeInFileByfb() {
        File f=new File("E:\\Java\\jmoa\\TestDiff\\src\\test\\resource\\test_fb.txt");
        String content="要写入文件的新内容";
        FileWriter fw=null;
        BufferedWriter bw=null;
        try{
            if(!f.exists()){
                f.createNewFile();
            }
             fw=new FileWriter(f.getAbsoluteFile(),true);  //true表示可以追加新内容  
                         //fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
             bw=new BufferedWriter(fw);
             bw.write(content);
             bw.close();
        }catch(Exception e){
           e.printStackTrace();
        }  
    }
	
	
	
}
