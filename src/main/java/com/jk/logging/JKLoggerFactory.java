package com.jk.logging;


public class JKLoggerFactory {

	private JKLoggerFactory(){
		
	}
	
	public static JKLogger getLogger(Class class1 ){
		return new JKLogger(class1.getName());
	} 
	
	public static void main(String[] args) {
		JKLogger logger = JKLoggerFactory.getLogger(Integer.class);
		logger.info("asdfads");
	}
}
