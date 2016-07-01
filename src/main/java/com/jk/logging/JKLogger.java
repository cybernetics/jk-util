/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jk.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jk.util.JKStringUtil;

/**
 * The Class JKLogger.
 *
 * @author Jalal Kiswani
 */
public class JKLogger {
	private Logger logger;

	/**
	 * Instantiates a new JK logger.
	 *
	 * @param name
	 *            the name
	 */
	public JKLogger(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	/**
	 * Info.
	 *
	 * @param msg
	 *            the msg
	 */
	public void info(Object... msg) {
		logger.info(JKStringUtil.concat(msg));
	}

	/**
	 * Info.
	 *
	 * @param msg
	 *            the msg
	 * @param exception
	 *            the exception
	 */
	public void info(String msg, Throwable exception) {
		logger.info(msg, exception);
	}

	/**
	 * Debug.
	 *
	 * @param msg
	 *            the msg
	 */
	public void debug(Object... msg) {
		logger.debug(JKStringUtil.concat(msg));
	}

	/**
	 * Debug.
	 *
	 * @param msg
	 *            the msg
	 * @param exception
	 *            the exception
	 */
	public void debug(String msg, Throwable exception) {
		logger.debug(msg, exception);
	}

	/**
	 * Trace.
	 *
	 * @param msg
	 *            the msg
	 */
	public void trace(Object... msg) {
		logger.trace(JKStringUtil.concat(msg));
	}

	/**
	 * Trace.
	 *
	 * @param msg
	 *            the msg
	 * @param exception
	 *            the exception
	 */
	public void trace(String msg, Throwable exception) {
		logger.trace(msg, exception);
	}

	/**
	 * Error.
	 *
	 * @param msg
	 *            the msg
	 */
	public void error(Object ...msg) {
		logger.error(JKStringUtil.concat(msg));
	}

	/**
	 * Error.
	 *
	 * @param msg
	 *            the msg
	 * @param exception
	 *            the exception
	 */
	public void error(String msg, Throwable exception) {
		logger.error(msg, exception);
	}

	/**
	 * Error.
	 *
	 * @param throwable
	 *            the throwable
	 */
	public void error(Throwable throwable) {
		error(throwable.getMessage(), throwable);
	}

}
