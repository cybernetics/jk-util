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
package com.jk.thread;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Class JKThreadLocal.
 *
 * @author Jalal Kiswani
 */
public class JKThreadLocal {

	/** The Constant thread. */
	private static final ThreadLocal<Map<String, Object>> thread = new ThreadLocal<>();

	/**
	 * Adds the all.
	 *
	 * @param map
	 *            the map
	 */
	public static void put(final Map<? extends String, ? extends Object> map) {
		JKThreadLocal.get().putAll(map);
	}

	/**
	 * Gets the.
	 *
	 * @return the map
	 */
	public static Map<String, Object> get() {
		Map<String, Object> map = JKThreadLocal.thread.get();
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
			JKThreadLocal.set(map);
		}
		return map;
	}

	/**
	 * Gets the value.
	 *
	 * @param name
	 *            the name
	 * @return the value
	 */
	/*
	 *
	 */
	public static Object getValue(final String name) {
		return JKThreadLocal.get().get(name);
	}

	/**
	 * Removes the.
	 */
	public static void remove() {
		JKThreadLocal.thread.remove();
	}

	/**
	 * Sets the.
	 *
	 * @param map
	 *            the map
	 */
	public static void set(final Map map) {
		JKThreadLocal.thread.set(map);
	}

	/**
	 * Sets the value.
	 *
	 * @param name
	 *            the name
	 * @param attribute
	 *            the attribute
	 */
	public static void setValue(final String name, final Object attribute) {
		JKThreadLocal.get().put(name, attribute);
	}
}
