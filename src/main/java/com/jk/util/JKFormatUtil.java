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
 *
 * @author Jalal Kiswani
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class JKFormatUtil {

	/** The Constant DEFAULT_DATE_PATTERN. */
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

	/** The Constant DEFAULT_TIME_PATTERN. */
	public static final String DEFAULT_TIME_PATTERN = "hh:mm:ss";

	/** The Constant DEFAULT_TIMESTAMP_PATTERN. */
	public static final String DEFAULT_TIMESTAMP_PATTERN = "dd/MM/yyyy hh:mm:ss";

	/** The Constant DEFAULT_DOUBLE_FORMAT. */
	public static final String DEFAULT_DOUBLE_FORMAT = "###,###,##0.000";

	/** The Constant DEFAULT_NUMBER_FORMAT. */
	public static final String DEFAULT_NUMBER_FORMAT = "###,###,##0";

	/** The Constant MYSQL_DATE_DB_PATTERN. */
	public static final String MYSQL_DATE_DB_PATTERN = "yyyy-MM-dd";

	/** The format map. */
	public static Map<String, Format> formatMap = new LinkedHashMap<String, Format>();

	/**
	 * Format.
	 *
	 * @param object
	 *            the object
	 * @param pattern
	 *            the pattern
	 * @return the string
	 */
	public static String format(final Object object, final String pattern) {
		if (object instanceof Date) {
			return JKFormatUtil.formatDate((Date) object, pattern);
		}
		if (object instanceof Time) {
			return JKFormatUtil.formatTime((Time) object, pattern);
		}
		if (object instanceof Timestamp) {
			return JKFormatUtil.formatTimeStamp((Timestamp) object, pattern);
		}
		if (object instanceof java.util.Date) {
			return JKFormatUtil.formatDate((java.util.Date) object, pattern);
		}
		if (object instanceof Double) {
			return JKFormatUtil.formatDouble((Double) object, pattern);
		}
		if (object != null) {
			return object.toString();
		}
		return null;
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
	public synchronized static String formatDate(final java.util.Date object, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = JKFormatUtil.DEFAULT_DATE_PATTERN;
		}
		return JKFormatUtil.getDateFormatter(pattern).format(object);
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
	public static String formatDouble(final Double amount, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = JKFormatUtil.DEFAULT_DOUBLE_FORMAT;
		}
		return JKFormatUtil.getNumberFormatter(pattern).format(amount);
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
	public synchronized static String formatTime(final Time object, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = JKFormatUtil.DEFAULT_TIME_PATTERN;
		}
		return JKFormatUtil.getDateFormatter(pattern).format(object);
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
	public synchronized static String formatTimeStamp(final Timestamp date, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = JKFormatUtil.DEFAULT_TIMESTAMP_PATTERN;
		}
		return JKFormatUtil.getDateFormatter(pattern).format(date);
	}

	/**
	 * Gets the date formatter.
	 *
	 * @param pattern
	 *            the pattern
	 * @return the date formatter
	 */
	public static Format getDateFormatter(final String pattern) {
		Format format = JKFormatUtil.formatMap.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			JKFormatUtil.formatMap.put(pattern, format);
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
	public static Format getNumberFormatter(final String pattern) {
		Format format = JKFormatUtil.formatMap.get(pattern);
		if (format == null) {
			format = new DecimalFormat(pattern);
			JKFormatUtil.formatMap.put(pattern, format);
		}
		return format;
	}

	/**
	 * Format number.
	 *
	 * @param count
	 *            the count
	 * @return the string
	 */
	public static String formatNumber(Number count) {
		Format numberFormatter = getNumberFormatter(DEFAULT_NUMBER_FORMAT);
		return numberFormatter.format(count);
	}

	/**
	 * Format date.
	 *
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(final java.util.Date date) {
		return formatDate(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * Format time.
	 *
	 * @param time
	 *            the time
	 * @return the string
	 */
	public static String formatTime(final Time time) {
		return formatDate(time, "hh:mm");
	}
}
