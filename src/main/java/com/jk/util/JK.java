package com.jk.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

	public static void throww(Throwable t) {
		JKExceptionUtil.handle(t);
	}

	public static Map toMap(Object[] keys, Object[] values) {
		Map map = new HashMap<>();
		int i = 0;
		for (Object key : keys) {
			map.put(key, values[i++]);
		}
		return map;
	}

	public static Map<String, Object> toMap(Object... list) {
		if (list.length % 2 != 0) {
			throw new IllegalArgumentException("List size should be even");
		}
		Map map = new LinkedHashMap();
		for (int i = 0; i < list.length; i += 2) {
			map.put(list[i], list[i + 1]);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(toMap("key1", "value1", "key2", "value2"));
	}

	public static int randomNumber() {
		return randomNumber(0, Integer.MAX_VALUE);
	}

	public static int randomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min,max);
	}
}
