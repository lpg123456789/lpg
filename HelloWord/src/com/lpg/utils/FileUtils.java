package com.lpg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件代理类，提供文件操作的各种封装方法，方便文件操作 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2017年11月1日 上午10:38:18
 */
public class FileUtils {
	
	/**
	 * 根据提供的完整路径在磁盘上创建一个文件，并返回该文件对象
	 * @param filePath 文件的完整路径。例如："C:\\Users\\Administrator\\Desktop\\test.txt"
	 * @param cover 指示是否覆盖已有的文件。如果值为true，则在创建之前会先删除已存在的文件，值为false则不覆盖，直接返回已存在文件的引用
	 * @return 返回新创建File对象（如果是不覆盖创建，并且文件存在，则返回的是已经存在的那个文件）
	 */
	public static File createFile(String filePath, boolean cover){
		File f = new File(filePath);
		if(cover){
			deleteFile(f);
		}
		
		if(!f.exists()){
			try{
				f.createNewFile();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return f;
	}
	
	/**
	 * 创建一个目录
	 * @param dirPath 目录路径
	 * @param cover 指示是否覆盖已存在的目录，true为覆盖，false为不覆盖
	 * @return 返回新创建的File对象（如果是不覆盖创建，并且目录存在，则返回的是已经存在的那个目录文件）
	 */
	public static File mkdir(String dirPath, boolean cover){
		File f = new File(dirPath);
		if(cover){
			deleteFile(f);
		}
		
		if(!f.exists()){
			try{
				//如果父目录不存在，则要调用mkdirs方法，否则无法创建路径
				File p = f.getParentFile();
				if(p == null || !p.exists()){
					f.mkdirs();
				}
				else{
					f.mkdir();
				}
			}
			catch(SecurityException e){
				e.printStackTrace();
			}
		}
		return f;
	}
	
	/**
	 * 使用文件路径删除文件或目录
	 * @param filePath 要删除的文件的路径
	 * @return 删除成功返回true，否则返回false。文件不存在时删除操作视为不成功，返回false
	 */
	public static boolean deleteFile(String filePath){
		return deleteFile(new File(filePath));
	}
	
	/**
	 * 使用File对象为参数删除文件或目录
	 * @param file 要删除的文件
	 * @return 要删除的文件不存在或者删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(File file){
		boolean flag = false;
		//File类的delete方法在删除目录时，要先删除目录中的子文件才行，因此这里要先判断File是否包含子文件的目录
		if(!file.isDirectory() || file.listFiles().length == 0){
			if(file.exists()){
				try{
					flag = file.delete();
				}
				catch(SecurityException e){
					e.printStackTrace();
				}
			}
		}
		else{
			File[] childs = file.listFiles();
			//递归删除子文件
			for(File f:childs){
				deleteFile(f);
			}
			//递归删除子文件自后再删除本身，注意这里不要漏，不然会有空目录删不掉
			deleteFile(file);
		}
		return flag;
	}
	
	/**
	 * 转码文本文件编码
	 * @param file
	 * @param newEncoding
	 * @param oldEncoding
	 * @return
	 */
	public static boolean transcoding(File file, String newEncoding, String oldEncoding){
		String content = readText(file, oldEncoding);
		if(content == null){
			return false;
		}
		return writeText(file, content, newEncoding);
	}
	
	/**
	 * 使用文件路径作为参数往文本文件中写入UTF8文本
	 * @param filePath
	 * @param text
	 */
	public static boolean writeUTF8Text(String filePath, String text){
		File f = new File(filePath);
		if(!f.exists()){
			createFile(filePath, false);
		}
		return writeUTF8Text(f, text);
	}
	
	/**
	 * 使用File对象作为参数往文本文件中写入UTF-8文本
	 * @param txtFile
	 * @param text
	 * @return
	 */
	public static boolean writeUTF8Text(File txtFile, String text){
		return writeText(txtFile, text, "UTF-8");
	}
	
	/**
	 * 使用文件路径作为参数读取UTF-8格式文本文件的内容
	 * @param txtFile 文件对象
	 * @return 读取的文本字符串
	 */
	public static String readUTF8Text(String filePath){
		return readUTF8Text(new File(filePath));
	}
	
	/**
	 * 使用File对象作为参数读取UTF-8格式文本文件的内容
	 * @param txtFile 文件对象
	 * @return 读取的文本字符串
	 */
	public static String readUTF8Text(File txtFile){
		return readText(txtFile, "UTF-8");
	}
	
	/**
	 * 写文本文件
	 * @param txtFile
	 * @param text
	 * @param encoding
	 */
	public static boolean writeText(File txtFile, String text, String encoding){
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		boolean flag = false;
		try{
			fos = new FileOutputStream(txtFile);
			osw = new OutputStreamWriter(fos, encoding);
			osw.write(text);
			osw.flush();
			flag = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(fos != null){
			try{
				fos.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		if(osw != null){
			try{
				osw.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 读取文本文件
	 * @param txtFile
	 * @param encoding
	 * @return
	 */
	public static String readText(File txtFile, String encoding){
		String str = null;
		if(!txtFile.exists() || !txtFile.isFile()){
			System.out.println("Not found "+txtFile.getPath());
			return str;
		}
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			fis = new FileInputStream(txtFile);
			isr = new InputStreamReader(fis, encoding);
			br = new BufferedReader(isr);
			
			StringBuffer sb = new StringBuffer();
			String lineText = "";
			String lsp = System.getProperty("line.separator");
			
			while(lineText != null && (lineText = br.readLine()) != null){
				sb.append(lineText);
				sb.append(lsp);
			}
			
			str = sb.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(fis != null){
			try{
				fis.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		if(isr != null){
			try{
				isr.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

		if(br != null){
			try{
				br.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return str;
	}
	
}
