package com.lpg.autoupdate.util;

/**
 * cmd拼接工具类
 *
 * @author Administrator
 */
public class CmdUtil {

    public static String cd() {
        return "cd";
    }

    public static String cd(String path) {
        return "cd " + path;
    }

    public static String unZip(String zipName) {
        return "unzip -o " + zipName;
    }

    public static String exit() {
        return "exit";
    }

    public static String backUpZip(String zipName) {
        return "cp " + zipName + " " + zipName + ".bak";
    }

    public static String ls() {
        return "ls";
    }

    public static String sh(String shellName) {
        return "sh " + shellName;
    }

    public static String svnUp() {
        return "svn up";
    }

    public static String stopGame(){
        return sh("service.sh stop game");
    }

    public static String startGame(){
        return sh("service.sh start game");
    }


}
