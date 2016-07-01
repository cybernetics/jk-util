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

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * The Class StringUtil.
 *
 * @author Jalal Kiswani
 */
public class JKStringUtil {

	/**
	 * Compile.
	 *
	 * @param sql
	 *            the sql
	 * @param param
	 *            the param
	 * @return the string
	 */
	public static String compile(String sql, final Object... param) {
		for (final Object element : param) {
			sql = sql.replaceFirst("\\?", element.toString());
		}
		return sql;
	}

	/**
	 * replace under score with space replace \n char with platform indepent
	 * line.separator
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String fixValue(String value) {
		if (value != null && !value.equals("")) {
			final String[] words = value.toLowerCase().split("_");
			value = "";
			for (final String word : words) {
				if (word.length() > 1) {
					value += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
				} else {
					value = word;
				}
			}
		}
		if (value.contains("\\n")) {
			value = value.replace("\\n", System.getProperty("line.separator"));
		}
		return value;
	}

	/**
	 * replace params in string with the using index , for example
	 * "Hello {1} {2}" with Jalal , Kiswani as aparamters will generate Hello
	 * Jalal Kiswani .
	 *
	 * @param value
	 *            the value
	 * @param params
	 *            the params
	 * @return the string
	 */
	public static String setParameters(String value, final Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				value = value.replaceAll("\\{" + i + "\\}", params[i].toString());
			}
		}
		return value;
	}

	/**
	 * Trim.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public static String trim(String str) {
		return StringUtils.trim(str);
	}

	/**
	 * Capitalize fully.
	 *
	 * @param label
	 *            the label
	 * @return the string
	 */
	public static String capitalizeFully(String label) {
		return WordUtils.capitalizeFully(label);
	}

	/**
	 * This method create nre properties from the origianl one and remove any
	 * key with the password , then call toString method on this prperties.
	 *
	 * @param original
	 *            the original
	 * @param string
	 *            the string
	 * @return the string
	 */

	public static String removeLast(String original, String string) {
		int lastIndexOf = original.lastIndexOf(string);
		if (lastIndexOf == -1) {
			return original;
		}
		return original.substring(0, lastIndexOf);
	}

	/**
	 * Escape value.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	// //////////////////////////////////////////////////////////////////
	public static String escapeValue(String value) {
		value = value.replaceAll("'", "\\\\'");
		return "'" + value + "'";
	}

	/**
	 * Checks if is empty.
	 *
	 * @param str
	 *            the str
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}

	/**
	 * Concat.
	 *
	 * @param arr
	 *            the arr
	 * @return the string
	 */
	public static String concat(Object... arr) {
		StringBuffer b = new StringBuffer();
		for (Object string : arr) {
			if (string != null) {
				b.append(string);
				b.append(" ");
			}
		}
		return b.toString();
	}

	/**
	 * Gets the first line.
	 *
	 * @param message
	 *            the message
	 * @return the first line
	 */
	public static String getFirstLine(final String message) {
		if (isEmpty(message)) {
			return message;
		}
		if (message.contains("\n")) {
			return message.split("\n")[0];
		}
		return message;
	}

	/**
	 * Removes the last.
	 *
	 * @param builder
	 *            the builder
	 * @param string
	 *            the string
	 * @return the string builder
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static StringBuilder removeLast(final StringBuilder builder, final String string) {
		final int lastIndexOf = builder.lastIndexOf(string);
		if (lastIndexOf == -1) {
			return builder;
		}
		return new StringBuilder(builder.substring(0, lastIndexOf));
	}

	/**
	 * Copy to clipboard.
	 *
	 * @param text
	 *            the text
	 */
	public static void copyToClipboard(String text) {
		final StringSelection stringSelection = new StringSelection(text);
		final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
	}

}
