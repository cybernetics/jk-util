package com.jk.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jk.util.StringUtil;

public class JKLogger {
	private Logger logger;

	public JKLogger(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	public void info(Object... msg) {
		logger.info(StringUtil.concat(msg));
	}

	public void info(String msg, Throwable exception) {
		logger.info(msg, exception);
	}

	public void debug(Object... msg) {
		logger.debug(StringUtil.concat(msg));
	}

	public void debug(String msg, Throwable exception) {
		logger.debug(msg, exception);
	}

	public void trace(Object... msg) {
		logger.trace(StringUtil.concat(msg));
	}

	public void trace(String msg, Throwable exception) {
		logger.trace(msg, exception);
	}

	public void error(Object ...msg) {
		logger.error(StringUtil.concat(msg));
	}

	public void error(String msg, Throwable exception) {
		logger.error(msg, exception);
	}

	public void error(Throwable throwable) {
		error(throwable.getMessage(), throwable);
	}

}
