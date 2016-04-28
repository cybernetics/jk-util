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

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.jk.exceptions.JKException;

/**
 * The Class DateTimeUtil.
 *
 * @author Jalal Kiswani
 */
public class DateTimeUtil {
	public enum CompareDates {
		DATE1_LESS_THAN_DATE2, DATE1_GREATER_THAN_DATE2, DATE1_EQUAL_DATE2;
	}

	public static java.util.Date parseDate(String strDate, String pattern) {
		try {
			SimpleDateFormat parser = new SimpleDateFormat(pattern, Locale.US);
			return parser.parse(strDate);
		} catch (ParseException e) {
			throw new JKException(e);
		}
	} // parseDate

	/**
	 * Format date.
	 *
	 * @param date
	 *            the date
	 * @param pattren
	 *            the pattren
	 * @return the string
	 */
	public static String formatDate(final Date date, final String pattren) {
		final SimpleDateFormat formatter = new SimpleDateFormat(pattren, new Locale("en", "US"));
		if (date == null) {
			return "";
		}
		return formatter.format(date);
	}

	/**
	 * Format full time.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatFullTime(final Date date) {
		return DateTimeUtil.formatDate(date, "hh:mm:ss SSS");
	}

	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	public static String getCurrentTime() {
		return DateTimeUtil.formatFullTime(new Date());
	}

	/**
	 * 
	 * @return int
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return getMonthFromDate(new Date());
	}

	/**
	 * 
	 * @return int
	 * @param date
	 *            Date
	 */
	public static int getYearFromData(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * s
	 * 
	 * @return int / //@v 1.1 start Update Bashar Nadir
	 * @param date
	 *            Date
	 */
	public static int getMonthFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static String formatTime(Date date) {
		return FormatUtil.formatDate(date, "hh:mm");
	}

	public static boolean isTimesEqaualed(Date time1, Date time2) {
		return formatTime(time1).equals(formatTime(time2));
	}

	public static int getNumOfMonths(Date date1, Date date2) {
		Calendar firstDate = Calendar.getInstance();
		Date date = new Date(date1.getTime());
		firstDate.setTime(date);
		Calendar secondDate = Calendar.getInstance();
		Date date3 = new Date(date2.getTime());
		secondDate.setTime(date3);
		int months = firstDate.get(Calendar.MONTH) - secondDate.get(Calendar.MONTH);

		return months;
	}

	public static String formatCurrentDate() {
		return FormatUtil.formatDate(new Date(), "yyyy-MM-dd");
	}

	public static CompareDates compareTwoDates(Date date1, Date date2) {
		Date d1 = new Date(date1.getTime());// to unify the format of the dates
											// before the compare
		Date d2 = new Date(date2.getTime());
		if (d1.compareTo(d2) < 0)
			return CompareDates.DATE1_LESS_THAN_DATE2;
		else if (d1.compareTo(d2) > 0)
			return CompareDates.DATE1_GREATER_THAN_DATE2;
		else
			return CompareDates.DATE1_EQUAL_DATE2;
	}

	public static Date adddDaysToCurrentDate(int numberOfDays) {
		Date date = new Date();
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.DATE, numberOfDays);
		return instance.getTime();
	}

	public static boolean isDate(String strDate, String pattern) {
		try {
			parseDate(strDate, pattern);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Date adddMonths(int numOfMonths) {
		return addMonths(new Date(), numOfMonths);
	}

	public static Date addMonths(Date date, int numOfMonths) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.MONTH, numOfMonths);
		return instance.getTime();
	}

	/**
	 * 
	 * @param timeFrom
	 * @param timeTo
	 * @return
	 * @throws DateParseException
	 */
	public static long getDifference(Time timeFrom, Time timeTo) throws ParseException {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		// the a means am/pm marker
		Date date = format.parse(timeFrom.toString());
		Date date2 = format.parse(timeTo.toString());
		long difference = (date2.getTime() - date.getTime()) / 1000 / 60;
		return difference;
	}

	/**
	 * 
	 * @return
	 */
	public static int getCurrentDayInMonth() {
		Date date = new Date();
		return getDayOfMonth(date);
	}

	private static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	// //////////////////////////////////////////////////////////////////////////////////
	public static boolean isTimeConflict(Time timeFrom, Time timeTo, Time otherTimeFrom, Time otherTimeTo) {
		if (DateTimeUtil.isTimesEqaualed(timeFrom, otherTimeFrom) || DateTimeUtil.isTimesEqaualed(timeTo, otherTimeTo)) {
			return true;
		}

		// other time start time is between start and end time for this time
		if (timeFrom.after(otherTimeFrom) && timeFrom.before(otherTimeTo)) {
			return true;
		}

		// other time e is between start and end time for this time
		if (timeTo.after(otherTimeFrom) && timeTo.before(otherTimeTo)) {
			return true;
		}

		// if time starting before this time and ends after this time
		if (timeFrom.before(otherTimeFrom) && timeTo.after(otherTimeTo)) {
			return true;
		}

		return false;
	}

}
