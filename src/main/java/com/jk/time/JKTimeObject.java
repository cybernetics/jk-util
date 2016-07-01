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
package com.jk.time;

import java.util.Calendar;
import java.util.Date;

/**
 * The Class JKTimeObject.
 *
 * @author Jalal Kiswani
 */
public class JKTimeObject {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int munite;

	/**
	 * Instantiates a new JK time object.
	 */
	public JKTimeObject() {

	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Sets the hour.
	 *
	 * @param hour
	 *            the new hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * Gets the munite.
	 *
	 * @return the munite
	 */
	public int getMunite() {
		return munite;
	}

	/**
	 * Sets the munite.
	 *
	 * @param munite
	 *            the new munite
	 */
	public void setMunite(int munite) {
		this.munite = munite;
	}

	/**
	 * To time object.
	 *
	 * @param date
	 *            the date
	 * @param time
	 *            the time
	 * @return the JK time object
	 */
	public JKTimeObject toTimeObject(Date date,Date time) {
		JKTimeObject fsTimeObject = new JKTimeObject();
		Calendar timeInstance = Calendar.getInstance();
		timeInstance.setTimeInMillis(time.getTime());
		fsTimeObject.setHour(timeInstance.get(Calendar.HOUR_OF_DAY));
		fsTimeObject.setMunite(timeInstance.get(Calendar.MINUTE));
		
		Calendar dateInstance = Calendar.getInstance();
		dateInstance.setTime(date);
		fsTimeObject.setYear(dateInstance.get(Calendar.YEAR));
		fsTimeObject.setMonth(dateInstance.get(Calendar.MONTH));
		fsTimeObject.setDay(dateInstance.get(Calendar.DAY_OF_MONTH));
		return fsTimeObject;
	}

	/**
	 * After.
	 *
	 * @param thareTime
	 *            the thare time
	 * @return true, if successful
	 */
	public boolean after(JKTimeObject thareTime) {
		if(getYear() == thareTime.getYear() || thareTime.getYear() > getYear()){
			System.out.println("after:: Year true");
			if(thareTime.getMonth() < getMonth()){
				return true;
			}
			if(getMonth() == thareTime.getMonth()){
				System.out.println("after:: Month true");
				if(thareTime.getDay() < getDay()){
					System.out.println("after:: Day true");
					return true;
				}
				if(getDay() == thareTime.getDay()){
					if(getHour() == thareTime.getHour()){
						if(thareTime.getMunite()<getMunite()){
							return true;
						}
					}
					if (getHour() > thareTime.getHour()) {
						return true;
					}
				}
			}
		}
		return false;		
			
	}

	/**
	 * Before.
	 *
	 * @param thareTime
	 *            the thare time
	 * @return true, if successful
	 */
	public boolean before(JKTimeObject thareTime) {
		if(getYear() == thareTime.getYear() || thareTime.getYear() > getYear()){
			if(getMonth() == thareTime.getMonth() || thareTime.getMonth() > getMonth()){
				
				if(thareTime.getMonth() > getMonth()){
						return true;
				}
				if(thareTime.getDay() > getDay()){
					return true;
				}
				if(getDay() == thareTime.getDay()){
					if(getHour() == thareTime.getHour()){
						if(getMunite()<thareTime.getMunite()){
							return true;
						}
					}
					
					if (getHour() < thareTime.getHour()) {
						return true;
					}
		
				}
			}
		}
		
		
		return false;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year
	 *            the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month
	 *            the new month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day
	 *            the new day
	 */
	public void setDay(int day) {
		this.day = day;
	}
}
