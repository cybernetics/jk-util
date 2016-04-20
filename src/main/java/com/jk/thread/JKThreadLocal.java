package com.jk.thread;

import java.util.LinkedHashMap;
import java.util.Map;




public class JKThreadLocal {
	private static final ThreadLocal<Map<String, Object>> thread = new ThreadLocal<>();

	/**
	 * 
	 * @param map
	 */
	public static void set(Map map) {
		thread.set(map);
	}

	/**
	 * 
	 * @return
	 */
	public static Map<String, Object> get() {
		Map<String, Object> map = thread.get();
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
			set(map);
		}
		return map;
	}

	/**
	 * 
	 * @param map
	 */
	public static void addAll(Map<? extends String, ? extends Object> map) {
		get().putAll(map);
	}

	/*
	 * 
	 */
	public static Object getValue(String name) {
		return get().get(name);
	}

	/**
	 * 
	 */
	public static void remove() {
		thread.remove();
	}

	public static void setValue(String name, Object attribute) {
		get().put(name, attribute);
	}
}
