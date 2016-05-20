package com.jk.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JKLogger {
	private Logger logger;

	public JKLogger(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void info(String msg, Throwable exception) {
		logger.info(msg, exception);
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void debug(String msg, Throwable exception) {
		logger.debug(msg, exception);
	}

	public void trace(String msg) {
		logger.trace(msg);
	}

	public void trace(String msg, Throwable exception) {
		logger.trace(msg, exception);
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable exception) {
		logger.error(msg, exception);
	}

	public void error(Throwable throwable) {
		error(throwable.getMessage(),throwable);
	}

}
