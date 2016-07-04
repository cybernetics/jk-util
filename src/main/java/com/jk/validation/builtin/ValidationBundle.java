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
package com.jk.validation.builtin;

import com.jk.locale.JKMessage;

/**
 * The Class ValidationBundle.
 *
 * @author Jalal Kiswani
 */
public class ValidationBundle {

	/**
	 * Gets the message.
	 *
	 * @param class1
	 *            the class 1
	 * @param string
	 *            the string
	 * @param port
	 *            the port
	 * @return the message
	 */
	public static String getMessage(final Class class1, final String string, final int port) {
		return JKMessage.get(string, port);
	}

	/**
	 * Gets the message.
	 *
	 * @param class1
	 *            the class 1
	 * @param string
	 *            the string
	 * @param objects
	 *            the objects
	 * @return the message
	 */
	public static String getMessage(final Class class1, final String string, final Object[] objects) {
		return JKMessage.get(string, objects);
	}

	/**
	 * Gets the message.
	 *
	 * @param class1
	 *            the class 1
	 * @param string
	 *            the string
	 * @param compName
	 *            the comp name
	 * @return the message
	 */
	public static String getMessage(final Class class1, final String string, final String compName) {
		return JKMessage.get(string, compName);
	}

	/**
	 * Gets the message.
	 *
	 * @param class1
	 *            the class 1
	 * @param string
	 *            the string
	 * @param compName
	 *            the comp name
	 * @param model
	 *            the model
	 * @return the message
	 */
	public static String getMessage(final Class class1, final String string, final String compName, final String model) {
		return JKMessage.get(string, compName);
	}

	/**
	 * Gets the message.
	 *
	 * @param class1
	 *            the class 1
	 * @param string
	 *            the string
	 * @param compName
	 *            the comp name
	 * @param curr
	 *            the curr
	 * @param charsetName
	 *            the charset name
	 * @return the message
	 */
	public static String getMessage(final Class class1, final String string, final String compName, final String curr, final String charsetName) {
		return JKMessage.get(string, compName);
	}

}