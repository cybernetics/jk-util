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
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public class JKCollectionUtil {
	public static void fixPropertiesKeys(final Properties prop) {
		final Enumeration<Object> keys = prop.keys();
		while (keys.hasMoreElements()) {
			final String currentKey = (String) keys.nextElement();
			final String fixedKey = fixPropertyKey(currentKey);
			final String value = prop.getProperty(currentKey);
			prop.remove(currentKey);
			prop.setProperty(fixedKey, value);
		}
	}

	public static String fixPropertyKey(final String name) {
		return name.toLowerCase().replace("_", "-");
	}

	// //////////////////////////////////////////////////////////
	public static Object gatRandomItem(final List items) {
		if (items == null || items.size() == 0) {
			return null;
		}
		final int itemIndex = (int) (Math.random() * items.size());
		return items.get(itemIndex);
	}

	/**
	 * Unify the objects in the list according to the hash code IMPORTANT :
	 * hashcode usage here is different from the Java Spec , IT SHOULD BE
	 * UNIUQUE FOR EACH OBJECT WHICH SHOULD HOLD SOMTHING LIKE THE DATABASE
	 * PRIMARY KEY
	 *
	 * @param config
	 */
	public static void unifyReferences(final Hashtable hash, final List list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				final Object itemAtList = list.get(i);
				final Object unifiedReferences = unifyReferences(hash, itemAtList);
				list.set(i, unifiedReferences);
			}
		}
	}

	// //////////////////////////////////////////////////////////
	/**
	 * return unified reference
	 */
	public static Object unifyReferences(final Hashtable hash, Object object) {
		final Object itemAtHash = hash.get(object.hashCode());
		if (itemAtHash == null) {
			hash.put(object.hashCode(), object);
		} else {
			object = itemAtHash;
		}
		return object;
	}
	
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
			buf.append(object == null ? "null" : JKObjectUtil.toString(object));
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
