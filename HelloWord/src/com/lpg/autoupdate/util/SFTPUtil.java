package com.lpg.autoupdate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
  
public class SFTPUtil {  
	
    /** 
     * 文件重命名 
     *  
     * @param directory 目录 
     * @param oldname 旧文件名   
     * @param newname 新文件名 
     * @param sftp 
     */  
    public static void renameFile(String directory, String oldname, String newname,  
            ChannelSftp sftp) {  
        try {  
            sftp.cd(directory);  
            sftp.rename(oldname, newname);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 文件上传 
     *  
     * @param directory 目录 
     * @param uploadFile 要上传的文件名 
     * @param sftp 
     */  
    public static void upload(String directory, String uploadFile, ChannelSftp sftp) {  
        try {  
            sftp.cd(directory);  
            File file = new File(uploadFile);  
            sftp.put(new FileInputStream(file), file.getName(),ChannelSftp.OVERWRITE);
            System.out.println("文件:"+file.getAbsolutePath()+"上传完毕");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 文件下载 
     *  
     * @param directory 目录 
     * @param downloadFile 要下载文件名 
     * @param saveFile 保持的文件名 
     * @param sftp 
     */  
    public static void download(String directory, String downloadFile,  
            String saveFile, ChannelSftp sftp) {  
        try {  
            sftp.cd(directory);  
            File file = new File(saveFile);  
            sftp.get(downloadFile, new FileOutputStream(file));  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
  
    /** 
     * 文件删除 
     *  
     * @param directory 目录 
     * @param deleteFile 要删除的文件名 
     * @param sftp 
     */  
    public static void delete(String directory, String deleteFile, ChannelSftp sftp) {  
        try {  
            sftp.cd(directory);  
            sftp.rm(deleteFile);  
            System.out.println("删除成功");  
        } catch (Exception e) {  
            System.out.println("删除失败");  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 列出目录下的文件 
     *  
     * @param directory 目录 
     * @param sftp 
     * @return 
     * @throws SftpException 
     */  
    @SuppressWarnings("rawtypes")
	public static Vector listFiles(String directory, ChannelSftp sftp)  
            throws SftpException {  
        return sftp.ls(directory);  
    }  
    
    public static void disConnect(ChannelSftp sftp) throws JSchException{
        if (sftp != null && !sftp.isClosed()) {
        	sftp.disconnect();
        }
        Session session = sftp.getSession();
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        System.out.println("关闭ssh连接");  
    }
  
}  