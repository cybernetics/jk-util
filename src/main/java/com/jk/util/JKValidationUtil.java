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

/**
 * The Class JKValidationUtil.
 *
 * @author Jalal Kiswani
 */
public class JKValidationUtil {
	
	/**
	 * Checks if is boolean.
	 *
	 * @param param
	 *            the param
	 * @return true, if is boolean
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isBoolean(final String param) {
		if (isEmpty(param)) {
			return false;
		}
		return param.equalsIgnoreCase("true") || param.equalsIgnoreCase("false");
	}

	/**
	 * Checks if is double.
	 *
	 * @param txt
	 *            the txt
	 * @return true, if is double
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isDouble(final String txt) {
		try {
			Double.parseDouble(txt);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Checks if is empty.
	 *
	 * @param str
	 *            the str
	 * @return true, if is empty
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isEmpty(final String str) {
		return str == null || str.trim().equals("");
	}

	/**
	 * Checks if is float.
	 *
	 * @param txt
	 *            the txt
	 * @return true, if is float
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isFloat(final String txt) {
		try {
			Float.parseFloat(txt);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Checks if is int.
	 *
	 * @param str
	 *            the str
	 * @return true, if is int
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isInt(final String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Checks if is integer.
	 *
	 * @param txt
	 *            the txt
	 * @return true, if is integer
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isInteger(final String txt) {
		try {
			Integer.parseInt(txt);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Checks if is upper case.
	 *
	 * @param txt
	 *            the txt
	 * @return true, if is upper case
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isUpperCase(final String txt) {
		boolean upper = true;
		for (final char c : txt.toCharArray()) {
			if (Character.isLowerCase(c)) {
				upper = false;
				break;
			}
		}
		return upper;
	}

}
