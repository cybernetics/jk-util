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
import com.jk.time.JKTimeObject;

/**
 * The Class DateTimeUtil.
 *
 * @author Jalal Kiswani
 */
public class JKDateTimeUtil {
	
	/**
	 * The Enum CompareDates.
	 *
	 * @author Jalal Kiswani
	 */
	public enum CompareDates {
		DATE1_LESS_THAN_DATE2, DATE1_GREATER_THAN_DATE2, DATE1_EQUAL_DATE2;
	}

	/**
	 * Parses the date.
	 *
	 * @param strDate
	 *            the str date
	 * @param pattern
	 *            the pattern
	 * @return the java.util. date
	 */
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
		return JKDateTimeUtil.formatDate(date, "hh:mm:ss SSS");
	}

	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	public static String getCurrentTime() {
		return JKDateTimeUtil.formatFullTime(new Date());
	}

	/**
	 * Gets the current year.
	 *
	 * @return int
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Gets the current month.
	 *
	 * @return the current month
	 */
	public static int getCurrentMonth() {
		return getMonthFromDate(new Date());
	}

	/**
	 * Gets the year from data.
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getYearFromData(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * s.
	 *
	 * @param date
	 *            Date
	 * @return int / //@v 1.1 start Update Bashar Nadir
	 */
	public static int getMonthFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * Format time.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatTime(Date date) {
		return JKFormatUtil.formatDate(date, "hh:mm");
	}

	/**
	 * Checks if is times eqaualed.
	 *
	 * @param time1
	 *            the time 1
	 * @param time2
	 *            the time 2
	 * @return true, if is times eqaualed
	 */
	public static boolean isTimesEqaualed(Date time1, Date time2) {
		return formatTime(time1).equals(formatTime(time2));
	}

	/**
	 * Gets the num of months.
	 *
	 * @param date1
	 *            the date 1
	 * @param date2
	 *            the date 2
	 * @return the num of months
	 */
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

	/**
	 * Format current date.
	 *
	 * @return the string
	 */
	public static String formatCurrentDate() {
		return JKFormatUtil.formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * Compare two dates.
	 *
	 * @param date1
	 *            the date 1
	 * @param date2
	 *            the date 2
	 * @return the compare dates
	 */
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

	/**
	 * Addd days to current date.
	 *
	 * @param numberOfDays
	 *            the number of days
	 * @return the date
	 */
	public static Date adddDaysToCurrentDate(int numberOfDays) {
		Date date = new Date();
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.DATE, numberOfDays);
		return instance.getTime();
	}

	/**
	 * Checks if is date.
	 *
	 * @param strDate
	 *            the str date
	 * @param pattern
	 *            the pattern
	 * @return true, if is date
	 */
	public static boolean isDate(String strDate, String pattern) {
		try {
			parseDate(strDate, pattern);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Addd months.
	 *
	 * @param numOfMonths
	 *            the num of months
	 * @return the date
	 */
	public static Date adddMonths(int numOfMonths) {
		return addMonths(new Date(), numOfMonths);
	}

	/**
	 * Adds the months.
	 *
	 * @param date
	 *            the date
	 * @param numOfMonths
	 *            the num of months
	 * @return the date
	 */
	public static Date addMonths(Date date, int numOfMonths) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.MONTH, numOfMonths);
		return instance.getTime();
	}

	/**
	 * Gets the difference.
	 *
	 * @param timeFrom
	 *            the time from
	 * @param timeTo
	 *            the time to
	 * @return the difference
	 */
	public static long getDifference(Time timeFrom, Time timeTo) {
		try {
			DateFormat format = new SimpleDateFormat("HH:mm:ss");
			// the a means am/pm marker
			Date date = format.parse(timeFrom.toString());
			Date date2 = format.parse(timeTo.toString());
			long difference = (date2.getTime() - date.getTime()) / 1000 / 60;
			return difference;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the current day in month.
	 *
	 * @return the current day in month
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

	/**
	 * Gets the day of week.
	 *
	 * @param date
	 *            the date
	 * @return the day of week
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Checks if is time conflict.
	 *
	 * @param timeFrom
	 *            the time from
	 * @param timeTo
	 *            the time to
	 * @param otherTimeFrom
	 *            the other time from
	 * @param otherTimeTo
	 *            the other time to
	 * @return true, if is time conflict
	 */
	// //////////////////////////////////////////////////////////////////////////////////
	public static boolean isTimeConflict(Time timeFrom, Time timeTo, Time otherTimeFrom, Time otherTimeTo) {
		if (JKDateTimeUtil.isTimesEqaualed(timeFrom, otherTimeFrom) || JKDateTimeUtil.isTimesEqaualed(timeTo, otherTimeTo)) {
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

	/**
	 * Gets the hour.
	 *
	 * @param timeFrom
	 *            the time from
	 * @return the hour
	 */
	public static int getHour(Date timeFrom) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(timeFrom);
		int hour = instance.get(Calendar.HOUR);
		return hour;

	}

	/**
	 * Equals.
	 *
	 * @param date1
	 *            the date 1
	 * @param date2
	 *            the date 2
	 * @return true, if successful
	 */
	public static boolean equals(Date date1, Date date2) {
		if ((date1 == null && date2 != null) || (date1 != null && date2 == null)) {
			return false;
		}
		return formatDate(date1, JKFormatUtil.MYSQL_DATE_DB_PATTERN).equals(formatDate(date2, JKFormatUtil.MYSQL_DATE_DB_PATTERN));

	}

	/**
	 * Checks if is current time between tow times.
	 *
	 * @param fromDate
	 *            the from date
	 * @param fromTime
	 *            the from time
	 * @param toDate
	 *            the to date
	 * @param timeTo
	 *            the time to
	 * @return true, if is current time between tow times
	 */
	public static boolean isCurrentTimeBetweenTowTimes(Date fromDate, Date fromTime, Date toDate, Date timeTo) {
		JKTimeObject currntTime = getCurrntTime();
		JKTimeObject fromTimeObject = new JKTimeObject();
		JKTimeObject toTimeObject = new JKTimeObject();
		if (currntTime.after(fromTimeObject.toTimeObject(fromDate, fromTime)) && currntTime.before(toTimeObject.toTimeObject(toDate, timeTo))) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the currnt time.
	 *
	 * @return the currnt time
	 */
	public static JKTimeObject getCurrntTime() {
		JKTimeObject fsTimeObject = new JKTimeObject();
		Calendar instance = Calendar.getInstance();
		fsTimeObject.setYear(instance.get(Calendar.YEAR));
		fsTimeObject.setMonth(instance.get(Calendar.MONTH));
		fsTimeObject.setDay(instance.get(Calendar.DAY_OF_MONTH));
		fsTimeObject.setHour(instance.get(Calendar.HOUR_OF_DAY));
		fsTimeObject.setMunite(instance.get(Calendar.MINUTE));
		return fsTimeObject;
	}

	/**
	 * Gets the day difference.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return the day difference
	 */
	public static long getDayDifference(Date startDate, Date endDate) {
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return diffDays;
	}

	/**
	 * Checks if is date eqaualed.
	 *
	 * @param date1
	 *            the date 1
	 * @param date2
	 *            the date 2
	 * @return true, if is date eqaualed
	 */
	public static boolean isDateEqaualed(final java.util.Date date1, final java.util.Date date2) {
		final String d1 = JKFormatUtil.formatDate(date1, JKFormatUtil.MYSQL_DATE_DB_PATTERN);
		final String d2 = JKFormatUtil.formatDate(date2, JKFormatUtil.MYSQL_DATE_DB_PATTERN);
		return d1.equalsIgnoreCase(d2);
	}

	/**
	 * Checks if is period active.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return true, if is period active
	 */
	public static boolean isPeriodActive(final Date startDate, final Date endDate)  {
		if (startDate == null && endDate == null) {
			return true;
		}
		if (startDate == null) {
			throw new JKException("START_DATE_CAN_NOT_BE_NULL");
		}
		if (endDate == null) {
			throw new JKException("END_DATE_CAN_NOT_BE_NULL");
		}
		if (compareTwoDates(startDate, endDate).equals(CompareDates.DATE1_GREATER_THAN_DATE2)) {
			throw new JKException("START_DATE_MUST_BE_BEFORE_END_DATE");
		}
		final boolean startLessThanCurrent = compareTwoDates(startDate, getSystemDate()).equals(CompareDates.DATE1_LESS_THAN_DATE2);
		final boolean endGreaterThanCurrent = compareTwoDates(endDate, getSystemDate()).equals(CompareDates.DATE1_GREATER_THAN_DATE2);
		return startLessThanCurrent && endGreaterThanCurrent;
	}

	/**
	 * Gets the system date.
	 *
	 * @return the system date
	 */
	public static Date getSystemDate() {
		//TODO : it should read the system data from db server of online time server
		return new Date();
	}

	/**
	 * Parses the date.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 */
	public static Date parseDate(String date) {
		return parseDate(date, JKFormatUtil.DEFAULT_DATE_PATTERN);
	}

}
