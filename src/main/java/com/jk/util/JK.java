package com.jk.util;

import java.util.List;

import com.jk.exceptions.handler.JKExceptionUtil;

public class JK {
	public static final String NEW_LINE = System.getProperty("line.separator");
	public static final String FIELD_SEPARATOR = ";";
	public static final String CSV_SEPARATOR = ",";

	public static void print(Object... params) {
		String fullText = buildToString(params);
		System.out.println(fullText);
	}

	public static String buildToString(Object... params) {
		StringBuffer finalMessage = new StringBuffer();
		int i = 0;
		for (Object object : params) {
			if (i++ > 1) {
				finalMessage.append(FIELD_SEPARATOR);
			}
			if (object instanceof List<?>) {
				finalMessage.append(JKCollectionUtil.toString((List<?>) object));
			} else {
				finalMessage.append(JKObjectUtil.toString(object, true));
			}
		}
		String fullText = finalMessage.toString();
		return fullText;
	}

	public static String concat(Object... params) {
		StringBuffer finalMessage = new StringBuffer();
		for (Object object : params) {
			finalMessage.append(JKObjectUtil.toString(object, true));
		}
		return finalMessage.toString();
	}

	public static void handle(Throwable t) {
		JKExceptionUtil.handle(t);
	}

	public static void line() {
		System.out.println("-------------------------------------------------");
	}

	public static void printBlock(Object... params) {
		line();
		print(params);
		line();
	}

	public static void debug() {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
	}
}
