/*
 *
 */
package com.jk.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jk.annotations.Author;

/**
 * The Class FormatUtil.
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class FormatUtil {

	/** The Constant DEFAULT_DATE_PATTERN. */
	private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

	/** The Constant DEFAULT_TIME_PATTERN. */
	private static final String DEFAULT_TIME_PATTERN = "hh:mm:ss";

	/** The Constant DEFAULT_TIMESTAMP_PATTERN. */
	private static final String DEFAULT_TIMESTAMP_PATTERN = "dd/MM/yyyy hh:mm:ss";

	/** The Constant DEFAULT_DOUBLE_FORMAT. */
	private static final String DEFAULT_DOUBLE_FORMAT = "###,###,##0.000";

	/** The format map. */
	private static Map<String, Format> formatMap = new LinkedHashMap<String, Format>();

	/**
	 * Format.
	 * 
	 * @param object
	 *            the object
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public static String format(Object object, String pattern) {
		if (object instanceof Date) {
			return formatDate((Date) object, pattern);
		}
		if (object instanceof Time) {
			return formatTime((Time) object, pattern);
		}
		if (object instanceof Timestamp) {
			return formatTimeStamp((Timestamp) object, pattern);
		}
		if (object instanceof java.util.Date) {
			return formatDate((java.util.Date) object, pattern);
		}
		if (object instanceof Double) {
			return formatDouble((Double) object, pattern);
		}
		if (object != null) {
			return object.toString();
		}
		return null;
	}

	/**
	 * Format time stamp.
	 * 
	 * @param date
	 *            the date
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public synchronized static String formatTimeStamp(Timestamp date, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = DEFAULT_TIMESTAMP_PATTERN;
		}
		return getDateFormatter(pattern).format(date);
	}

	/**
	 * Format time.
	 * 
	 * @param object
	 *            the object
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public synchronized static String formatTime(Time object, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = DEFAULT_TIME_PATTERN;
		}
		return getDateFormatter(pattern).format(object);
	}

	/**
	 * Format date.
	 * 
	 * @param object
	 *            the object
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public synchronized static String formatDate(java.util.Date object, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = DEFAULT_DATE_PATTERN;
		}
		return getDateFormatter(pattern).format(object);
	}

	/**
	 * Format double.
	 * 
	 * @param amount
	 *            the amount
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public static String formatDouble(Double amount, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = DEFAULT_DOUBLE_FORMAT;
		}
		return getNumberFormatter(pattern).format(amount);
	}

	/**
	 * Gets the date formatter.
	 * 
	 * @param pattern
	 *            the pattern
	 * @return the date formatter
	 */
	public static Format getDateFormatter(String pattern) {
		Format format = formatMap.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			formatMap.put(pattern, format);
		}
		return format;
	}

	/**
	 * Gets the number formatter.
	 * 
	 * @param pattern
	 *            the pattern
	 * @return the number formatter
	 */
	public static Format getNumberFormatter(String pattern) {
		Format format = formatMap.get(pattern);
		if (format == null) {
			format = new DecimalFormat(pattern);
			formatMap.put(pattern, format);
		}
		return format;
	}

}
