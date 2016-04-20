package com.jk.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The Interface HistoryItem.
 */
@Retention(RetentionPolicy.SOURCE)
@Author(name = "Jalal Kiswani", date = "23/9/2014", version = "1.0")
public @interface HistoryItem {

	/**
	 * Version.
	 *
	 * @return the double
	 */
	double Version();

	/**
	 * Date.
	 *
	 * @return the string
	 */
	String Date();

	/**
	 * Developer.
	 *
	 * @return the string
	 */
	String Developer();

	/**
	 * Description.
	 *
	 * @return the string
	 */
	String Description();
}
