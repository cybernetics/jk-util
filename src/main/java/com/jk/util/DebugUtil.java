package com.jk.util;

import java.util.logging.Logger;

public class DebugUtil {
	static Logger logger = Logger.getLogger(DebugUtil.class.getName());

	/**
	 * 
	 * @param label
	 */
	public static void printCurrentTime(Object label) {
		System.err.println(DateTimeUtil.getCurrentTime() + "  :" + label);
	}
}
