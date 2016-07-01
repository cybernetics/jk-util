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
package com.jk.exceptions;

/**
 * The Class JKInvalidUserException.
 *
 * @author Jalal Kiswani
 */
public class JKInvalidUserException extends JKSecurityException {

	/**
	 * Instantiates a new JK invalid user exception.
	 */
	public JKInvalidUserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK invalid user exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public JKInvalidUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK invalid user exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public JKInvalidUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK invalid user exception.
	 *
	 * @param message
	 *            the message
	 */
	public JKInvalidUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK invalid user exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public JKInvalidUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
