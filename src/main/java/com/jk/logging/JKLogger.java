package com.jk.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JKLogger {
	private Logger logger = null;
	
	public JKLogger(String name) {
		logger = LoggerFactory.getLogger(name);
	}
	
//	public void info(String msg){
//		logger.info(msg);
//	}
	
	public void debug(String msg){
		logger.debug(msg);
	}
	
	public void trace(String msg){
		logger.trace(msg);
	}
	
	public void error(String msg){
		logger.error(msg);
	}
	
	public static void info(String str){
		System.err.println(str);
	}

	public static void fatal(String string) {
		System.out.println(string);
	}
	
	
}
