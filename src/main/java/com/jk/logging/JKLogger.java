package com.jk.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;


public class JKLogger {
	private Logger logger = null;
	private String name;
	
	public JKLogger(String name) {
		logger = LoggerFactory.getLogger(name);
		this.name = name;
	}
	
	public void info(String... msgs){
		if(msgs == null){
			return ;
		}
		for (String msg : msgs) {
			logger.info(msg);
		}
	}
	
	public void debug(String... msgs){
		if(msgs == null){
			return ;
		}
		for (String msg : msgs) {
			logger.debug(msg);
		}
	}
	
	public void trace(String... msgs){
		if(msgs == null){
			return ;
		}
		for (String msg : msgs) {
			logger.trace(msg);
		}
	}
	
	public void error(String... msgs){
		if(msgs == null){
			return ;
		}
		for (String msg : msgs) {
			logger.error(msg);
		}
	}
	
	public void warn(String... msgs){
		if(msgs == null){
			return ;
		}
		for (String msg : msgs) {
			logger.warn(msg);
		}
	}
	
	public void error(Throwable t){
		if(t == null){
			return ;
		}
		error(t.getMessage() , t);
	}
	
	public void error(String msg,Throwable t){
		if(t == null){
			return ;
		}
		logger.error(msg, t);
	}

	public String getName() {
		return name;
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}
	
}
