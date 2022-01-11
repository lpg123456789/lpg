package com.lpg.autoupdate.auto;

import com.jcraft.jsch.ChannelShell;
import com.lpg.autoupdate.util.CmdUtil;
import com.lpg.autoupdate.util.ShellUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 33
 *
 * @author list
 * 1.项目自动化打包 pack()
 * 2.备份linux服务器的包 backup()
 * 3.上传包到linux服务器 upload()
 * 4.自动解压 unzip()
 */
public class AutoGo33 {

    private static String serverPath = "/data/server";//linux根目录

    private static String host = "10.19.200.43";
    private static int port = 2222;
    private static String username = "lisongtao";
    private static String password = "";

    public static void main(String args[]) throws Exception {

        pack(true, "logic", "trunk");

    }

    /**
     * 内网打包
     *
     * @param updateServer 是否更新内1
     * @throws Exception
     */
    public static void pack(boolean updateServer, String dir, String version) throws Exception {


        ChannelShell shell = ShellUtil.connect(host, username, password, port, ChannelShell.class);

        List<String> commandList = new ArrayList<>();

        commandList.add("10.19.200.33");
        commandList.add("sudo su");
        commandList.add(CmdUtil.cd(serverPath));
        commandList.add(CmdUtil.ls());
        commandList.add(CmdUtil.cd("build"));
        commandList.add(CmdUtil.sh("build.sh " + dir + " " + version));
        if (updateServer) {
            commandList.add(CmdUtil.cd("../test1"));
            commandList.add(CmdUtil.svnUp());
            commandList.add(CmdUtil.stopGame());
            commandList.add(CmdUtil.startGame());
        }
//        commandList.add(CmdUtil.exit());

        ShellUtil.exec(shell, commandList);

        ShellUtil.disConnect(shell);

        //System.exit(0);
    }

    /**
     * 内网更新
     *
     * @throws Exception
     */
    public static void updateVersion(String ip, Set<String> logicDirs, String dir, String version) throws Exception {

        ChannelShell shell = ShellUtil.connect(host, username, password, port, ChannelShell.class);

        List<String> commandList = new ArrayList<>();

        commandList.add(ip);
        commandList.add("sudo su");
        for (String logicDir : logicDirs) {
            commandList.add(CmdUtil.cd(serverPath));
            commandList.add(CmdUtil.ls());
            commandList.add(CmdUtil.cd(logicDir));
            commandList.add(CmdUtil.svnUp());
            commandList.add(CmdUtil.stopGame());
            commandList.add(CmdUtil.startGame());
        }

        ShellUtil.exec(shell, commandList);

        ShellUtil.disConnect(shell);

    }

    /**
     * 内网更新
     *
     * @throws Exception
     */
    public static void createTable(String ip, Set<String> logicDirs, String dir, String version) throws Exception {

        ChannelShell shell = ShellUtil.connect(host, username, password, port, ChannelShell.class);

        List<String> commandList = new ArrayList<>();

        commandList.add(ip);
        commandList.add("sudo su");
        for (String logicDir : logicDirs) {
            commandList.add(CmdUtil.cd("/data/server"));
            commandList.add(CmdUtil.ls());
            commandList.add(CmdUtil.cd(logicDir));
            commandList.add(CmdUtil.svnUp());
            commandList.add(CmdUtil.stopGame());
            commandList.add(CmdUtil.startGame());
        }

        ShellUtil.exec(shell, commandList);

        ShellUtil.disConnect(shell);

    }


}
