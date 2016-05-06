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

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * The Class StringUtil.
 *
 * @author Jalal Kiswani
 */
public class StringUtil {

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

	public static String trim(String str) {
		return StringUtils.trim(str);
	}

	public static String capitalizeFully(String label) {
		return WordUtils.capitalizeFully(label);
	}

	/**
	 * This method create nre properties from the origianl one and remove any
	 * key with the password , then call toString method on this prperties.
	 *
	 * @param properties
	 *            the properties
	 * @return the string
	 */

	public static String removeLast(String original, String string) {
		int lastIndexOf = original.lastIndexOf(string);
		if (lastIndexOf == -1) {
			return original;
		}
		return original.substring(0, lastIndexOf);
	}

	// //////////////////////////////////////////////////////////////////
	public static String escapeValue(String value) {
		value = value.replaceAll("'", "\\\\'");
		return "'" + value + "'";
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static String escpeString(final String value) {
		return value.replace("'", "");
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static String fixPropertyName(String name) {
		// captialize every char after the underscore , and remove underscores
		final char[] charArray = name.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '_') {
				charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
			}
		}
		name = new String(charArray).replaceAll("_", "");
		return name;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static String getExtension(final String fileName, final boolean withPoint) {
		final int lastIndexOf = fileName.lastIndexOf(".");
		if (!withPoint) {
			return fileName.substring(lastIndexOf + 1);
		}
		return fileName.substring(lastIndexOf);
	}

	public static String getFirstLine(final String message) {
		if (isEmpty(message)) {
			return message;
		}
		if (message.contains("\n")) {
			return message.split("\n")[0];
		}
		return message;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isBoolean(final String param) {
		if (isEmpty(param)) {
			return false;
		}
		return param.equalsIgnoreCase("true") || param.equalsIgnoreCase("false");
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isDouble(final String txt) {
		try {
			Double.parseDouble(txt);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isEmpty(final String str) {
		return str == null || str.trim().equals("");
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isFloat(final String txt) {
		try {
			Float.parseFloat(txt);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isInt(final String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isInteger(final String txt) {
		try {
			Integer.parseInt(txt);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

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

	////////////////////////////////////////////////////////////////////////////////////////////
	public static String removeExtension(String fileName) {
		final String separator = System.getProperty("file.separator");
		String filename;
		// Remove the path upto the filename.
		final int lastSeparatorIndex = fileName.lastIndexOf(separator);
		if (lastSeparatorIndex == -1) {
			filename = fileName;
		} else {
			filename = fileName.substring(lastSeparatorIndex + 1);
		}

		// Remove the extension.
		final int extensionIndex = filename.lastIndexOf(".");
		if (extensionIndex == -1) {
			return filename;
		}
		fileName = fileName.substring(0, lastSeparatorIndex);
		return fileName + File.separator + filename.substring(0, extensionIndex);
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static StringBuilder removeLast(final StringBuilder builder, final String string) {
		final int lastIndexOf = builder.lastIndexOf(string);
		if (lastIndexOf == -1) {
			return builder;
		}
		return new StringBuilder(builder.substring(0, lastIndexOf));
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static String toCamelCase(final String s) {
		final String[] parts = s.split("_");
		String camelCaseString = "";
		for (final String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part) + " ";
		}
		return camelCaseString;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	private static String toProperCase(final String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static String toString(final List<?> list, final String separtor) {
		final StringBuffer buf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			final Object object = list.get(i);
			buf.append(object.toString());
			if (i < list.size() - 1) {
				buf.append(separtor);
			}
		}
		return buf.toString();
	}

}
