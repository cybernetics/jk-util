package com.jk.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StringUtil {

	/**
	 * @param sql
	 * @param param
	 * @return
	 */
	public static String compile(String sql, Object... param) {
		for (int i = 0; i < param.length; i++) {
			sql = sql.replaceFirst("\\?", param[i].toString());
		}
		return sql;
	}

	/**
	 * This method create nre properties from the origianl one and remove any
	 * key with the password , then call toString method on this prperties
	 * 
	 * @param properties
	 * @return
	 */
	public static String toString(Properties properties) {
		Properties newProperties = new Properties();
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement().toString();
			if (key.toLowerCase().contains("password")) {
				continue;
			}
			newProperties.setProperty(key, properties.getProperty(key));
		}
		return newProperties.toString();
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String toString(List list) {
		StringBuffer buf = new StringBuffer("[");
		for (Object object : list) {
			if (object != null && object.toString().contains("@")) {
				// most likely toString not overriden
				buf.append(toString(object));
			} else {
				buf.append(object == null ? "null" : object.toString());
			}
			buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static Object toString(Object object) {
		return "[".concat(ToStringBuilder.reflectionToString(object,ToStringStyle.SIMPLE_STYLE)).concat("]");
	}

}
