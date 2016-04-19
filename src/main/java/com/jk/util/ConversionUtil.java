package com.jk.util;

import java.util.Date;

public class ConversionUtil {

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static double toDouble(Object value) {
		if (value == null) {
			return 0;
		}
		return new Double(value.toString());
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Integer toInteger(Object value) {
		return (int) toDouble(value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static float toFloat(Object value) {
		if (value == null) {
			return 0;
		}
		return new Float(value.toString());
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean toBoolean(Object value) {
		boolean result = false;
		if (value != null) {
			if (value.toString().trim().equals("1") || value.toString().trim().toLowerCase().equals("true")) {
				result = true;
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		return result;

	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Date toDate(Object value) {
		if (value instanceof Date) {
			return (Date) value;
		}
		if (value == null || value.equals("null")) {
			return null;
		}
		if (value instanceof String) {
			throw new IllegalStateException("fix me");
		}

		return null;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String toString(Object value) {
		return value == null ? null : value.toString();
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static java.sql.Time toTime(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof java.sql.Time) {
			return (java.sql.Time) value;
		}
		if (value instanceof java.util.Date) {
			Date date = (java.util.Date) value;
			return new java.sql.Time(date.getTime());
		}
		return null;
	}
}
