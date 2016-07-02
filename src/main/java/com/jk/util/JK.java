package com.jk.util;

public class JK {
	public static final String NEW_LINE = System.getProperty("line.separator");

	public static void print(Object msg) {
		print2(msg);
	}

	public static void print2(Object msg, Object... params) {
		StringBuffer finalMessage = new StringBuffer();
		if (msg instanceof String && msg != null) {
			finalMessage.append(String.format(msg.toString(), params));
		} else {
			finalMessage.append(JKObjectUtil.toString(msg));
			for (Object object : params) {
				finalMessage.append(";");
				finalMessage.append(JKObjectUtil.toString(object));
			}
		}
		finalMessage.append(NEW_LINE);
		System.out.println(finalMessage.toString());
	}
}
