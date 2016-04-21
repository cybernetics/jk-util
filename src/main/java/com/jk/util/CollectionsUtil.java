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

/**
 * The Class CollectionsUtil.
 *
 * @author Jalal Kiswani
 */
public class CollectionsUtil {

	/**
	 * To string.
	 *
	 * @param list
	 *            the list
	 * @return the string
	 */
	public static String toString(final List<?> list) {
		final StringBuffer buf = new StringBuffer("[");
		for (final Object object : list) {
			buf.append(object == null ? "null" : ObjectUtil.toString(object));
			buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}

	/**
	 * To string.
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
