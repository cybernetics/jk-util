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
package com.jk.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The Interface HistoryItem.
 *
 * @author Jalal Kiswani
 */
@Retention(RetentionPolicy.SOURCE)
@Author(name = "Jalal Kiswani", date = "23/9/2014", version = "1.0")
public @interface HistoryItem {

	/**
	 * Date.
	 *
	 * @return the string
	 */
	String Date();

	/**
	 * Description.
	 *
	 * @return the string
	 */
	String Description();

	/**
	 * Developer.
	 *
	 * @return the string
	 */
	String Developer();

	/**
	 * Version.
	 *
	 * @return the double
	 */
	double Version();
}
