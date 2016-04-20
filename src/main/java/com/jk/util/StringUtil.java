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

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
	 * To string.
	 *
	 * @param list
	 *            the list
	 * @return the string
	 */
	public static String toString(final List list) {
		final StringBuffer buf = new StringBuffer("[");
		for (final Object object : list) {
			if (object != null && object.toString().contains("@")) {
				// most likely toString not overriden
				buf.append(StringUtil.toString(object));
			} else {
				buf.append(object == null ? "null" : object.toString());
			}
			buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}

	/**
	 * To string.
	 *
	 * @param object
	 *            the object
	 * @return the object
	 */
	public static Object toString(final Object object) {
		return "[".concat(ToStringBuilder.reflectionToString(object, ToStringStyle.SIMPLE_STYLE)).concat("]");
	}

	/**
	 * This method create nre properties from the origianl one and remove any
	 * key with the password , then call toString method on this prperties.
	 *
	 * @param properties
	 *            the properties
	 * @return the string
	 */
	public static String toString(final Properties properties) {
		final Properties newProperties = new Properties();
		final Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			final String key = keys.nextElement().toString();
			if (key.toLowerCase().contains("password")) {
				continue;
			}
			newProperties.setProperty(key, properties.getProperty(key));
		}
		return newProperties.toString();
	}

}
