package com.jk.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
	
	/**
	 * 
	 * @param date
	 * @param pattren
	 * @return
	 */
	public static String formatDate(Date date, String pattren) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattren, new Locale("en", "US"));
		if (date == null) {
			return "";
		}
		return formatter.format(date);
	} 

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatFullTime(Date date) {
		return formatDate(date, "hh:mm:ss SSS");
	}

	/**
	 * 
	 * @param label
	 * @return
	 */
	public static String getCurrentTime() {
		return formatFullTime(new Date()) ;
	}
}
