package com.lpg.autoupdate.util;

import java.util.LinkedList;
import java.util.List;

/**
 * cmd链
 * @author Administrator
 *
 */
public class CmdList {
	
	List<String> commandList = new LinkedList<>();
	
	public CmdList cd() {
		commandList.add(CmdUtil.cd());
		return this;
	}
	
	public CmdList cd(String path) {
		commandList.add(CmdUtil.cd(path));
		return this;
	}

	public  CmdList unZip(String zipName) {
		commandList.add(CmdUtil.unZip(zipName));
		return this;
	}

	public  CmdList exit() {
		commandList.add(CmdUtil.exit());
		return this;
	}
	
	public  CmdList backUpZip(String zipName) {
		commandList.add(CmdUtil.backUpZip(zipName));
		return this;
	}
	
	public  CmdList ls(){
		commandList.add(CmdUtil.ls());
		return this;
	}
	
	public CmdList addCmd(String cmd){
		commandList.add(cmd);
		return this;
	}
	
	public List<String> getCmds(){
		return commandList;
	}
	
	public void print(){
		commandList.forEach(System.out::println);
	}
	

}
