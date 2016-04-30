package com.jk.logging;

import java.util.Hashtable;
import java.util.Map;


public class JKLoggerFactory {
	private static Map<Class, JKLogger> loggers = new Hashtable<Class, JKLogger>();
	private static Object lock = new Object();

	private JKLoggerFactory(){
		
	}
	
	public static JKLogger getLogger(Class clazz ){
		if(loggers.get(clazz) == null){
			synchronized (lock) {
				if(loggers.get(clazz) == null){
					loggers.put(clazz, new JKLogger(clazz.getName()));
				}
			}
		}
		return loggers.get(clazz);
	} 
	
	public static void main(String[] args) {
		JKLogger logger = JKLoggerFactory.getLogger(Integer.class);
		logger.info("asdfads");
	}
}
