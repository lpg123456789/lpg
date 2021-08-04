package gk.cross.shine;

import org.apache.log4j.xml.DOMConfigurator;

import gk.cross.shine.server.ConsoleServer;
import gk.cross.shine.server.CrossServer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class CrossMain {

	private static Logger log = Logger.getLogger(CrossMain.class);
	
	public static void main(String[] args) {
		
		String log4jConfigPath = Globals.getConfigPath(Globals.LOG_CONFIG_FILE);
		DOMConfigurator.configure(log4jConfigPath);

		log.info("server|cross|start...");
		
		try {
			InputStream in = new FileInputStream(Globals.getConfigPath(Globals.SERVER_CONFIG));
			Globals.properties.load(new InputStreamReader(in, "UTF-8"));
			in.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// console服务
		new Thread(ConsoleServer.getInstance()).start();
		// 跨服服务
		new Thread(CrossServer.getInstance()).start();
		
	}
}
