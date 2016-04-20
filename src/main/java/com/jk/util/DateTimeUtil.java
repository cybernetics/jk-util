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
	public static String formatDate(final Date date, final String pattren) {
		final SimpleDateFormat formatter = new SimpleDateFormat(pattren, new Locale("en", "US"));
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
	public static String formatFullTime(final Date date) {
		return DateTimeUtil.formatDate(date, "hh:mm:ss SSS");
	}

	/**
	 *
	 * @param label
	 * @return
	 */
	public static String getCurrentTime() {
		return DateTimeUtil.formatFullTime(new Date());
	}
}
