package com.lpg.autoupdate.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelDirectTCPIP;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.ChannelSubsystem;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ShellUtil {
//    public static ChannelShell connect(String host, String user, String psw, int port) {
//        Session session = null;
//        ChannelShell openChannel = null;
//        try {
//            JSch jsch = new JSch();
//            // getSession()只是创建一个session，需要设置必要的认证信息之后，调用connect()才能建立连接。
//            session = jsch.getSession(user, host, port);
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.setPassword(psw);
//            
//            session.connect();
//            // 调用openChannel(String type)
//            // 可以在session上打开指定类型的channel。该channel只是被初始化，使用前需要先调用connect()进行连接。
//            //   Channel的类型可以为如下类型：
//            //   shell - ChannelShell
//            //   exec - ChannelExec
//            //   direct-tcpip - ChannelDirectTCPIP
//            //   sftp - ChannelSftp
//            //   subsystem - ChannelSubsystem
//            // 其中，ChannelShell和ChannelExec比较类似，都可以作为执行Shell脚本的Channel类型。它们有一个比较重要的区别：ChannelShell可以看作是执行一个交互式的Shell，而ChannelExec是执行一个Shell脚本。
//            openChannel = (ChannelShell) session.openChannel("shell");
//            openChannel.connect();
//            
//            return openChannel;
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
//        return openChannel;
//    }
    
    @SuppressWarnings("unchecked")
	public static <T extends Channel> T connect(String host, String user, String psw, int port,Class<T> classes) {
        Session session = null;
        T openChannel = null;
        try {
            JSch jsch = new JSch();
            jsch.addIdentity("F:\\lisongtao-jumpserver");
            // getSession()只是创建一个session，需要设置必要的认证信息之后，调用connect()才能建立连接。
            session = jsch.getSession(user, host, port);
            System.out.println("Session创建成功");  
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
//            session.setPassword(psw);
            
            session.connect();
            
            String s = "";
            
            if(classes == ChannelShell.class){
            	s = "shell";
            }else if(classes == ChannelExec.class){
            	s ="exec";
            }else if(classes == ChannelDirectTCPIP.class){
            	s = "direct-tcpip";
            }else if(classes == ChannelSftp.class){
            	s = "sftp";
            }else if(classes == ChannelSubsystem.class){
            	s = "subsystem";
            }
            // 调用openChannel(String type)
            // 可以在session上打开指定类型的channel。该channel只是被初始化，使用前需要先调用connect()进行连接。
            //   Channel的类型可以为如下类型：
            //   shell - ChannelShell
            //   exec - ChannelExec
            //   direct-tcpip - ChannelDirectTCPIP
            //   sftp - ChannelSftp
            //   subsystem - ChannelSubsystem
            // 其中，ChannelShell和ChannelExec比较类似，都可以作为执行Shell脚本的Channel类型。它们有一个比较重要的区别：ChannelShell可以看作是执行一个交互式的Shell，而ChannelExec是执行一个Shell脚本。
            
            openChannel = (T) session.openChannel(s);
            openChannel.connect();
            
            return openChannel;
        }catch(Exception e){
        	e.printStackTrace();
        }
        return openChannel;
    }
    
    public static void exec(ChannelShell channel,List<String> commandList) throws Exception{
		
    	//获取输入流和输出流
		InputStream instream = channel.getInputStream();
		OutputStream outstream = channel.getOutputStream();
		
		PrintWriter printWriter = new PrintWriter(outstream);
		
		for(String command:commandList){
			printWriter.println(command);
            printWriter.flush();
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(instream));

        String msg = null;
        while((msg = in.readLine())!=null){
            System.out.println(msg);
        }
        
    	
    }

    
    public static void disConnect(ChannelShell channel) throws Exception{
    	
		InputStream instream = channel.getInputStream();
		OutputStream outstream = channel.getOutputStream();
		
	    outstream.close();
	    instream.close();
		
        if (channel != null && !channel.isClosed()) {
        	channel.disconnect();
        }
        Session session = channel.getSession();
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        System.out.println("关闭ssh连接");  
    }
    
}