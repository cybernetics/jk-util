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

import java.util.Date;

public class ConversionUtil {

	/**
	 *
	 * @param value
	 * @return
	 */
	public static boolean toBoolean(final Object value) {
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
	public static Date toDate(final Object value) {
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
	public static double toDouble(final Object value) {
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
	public static float toFloat(final Object value) {
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
	public static Integer toInteger(final Object value) {
		return (int) ConversionUtil.toDouble(value);
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public static String toString(final Object value) {
		return value == null ? null : value.toString();
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public static java.sql.Time toTime(final Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof java.sql.Time) {
			return (java.sql.Time) value;
		}
		if (value instanceof java.util.Date) {
			final Date date = (java.util.Date) value;
			return new java.sql.Time(date.getTime());
		}
		return null;
	}
}
