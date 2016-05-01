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
package com.jk.util;

import java.util.Arrays;
import java.util.logging.Logger;

import com.jk.logging.JKLogger;
import com.jk.logging.JKLoggerFactory;

/**
 * The Class DebugUtil.
 *
 * @author Jalal Kiswani
 */
public class DebugUtil {

	/** The logger. */
	static JKLogger logger = JKLoggerFactory.getLogger(DebugUtil.class);

	/**
	 * Gets the exception caller class.
	 *
	 * @param t
	 *            the t
	 * @return the exception caller class
	 */
	public static Class<?> getExceptionCallerClass(final Throwable t) {
		final StackTraceElement[] stackTrace = t.getStackTrace();
		for (final StackTraceElement stackTraceElement : stackTrace) {
			logger.debug(stackTraceElement.getClassName().concat(".").concat(stackTraceElement.getMethodName()));
		}
		return null;
	}

	/**
	 * Prints the current time.
	 *
	 * @param label
	 *            the label
	 */
	public static void printCurrentTime(final Object label) {
		logger.debug(DateTimeUtil.getCurrentTime() + "  :" + label);
	}

	/**
	 * Prints the stack trace.
	 */
	public static void printStackTrace() {
		logger.debug(Arrays.toString(Thread.currentThread().getStackTrace()).replaceAll(",", "\\\n"));
	}

	public static String getCaller() {
		return Thread.currentThread().getStackTrace()[2].toString();
	}

}
