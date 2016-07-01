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

import java.util.ArrayList;

/**
 * The Class JKDaoException.
 *
 * @author Jalal Kiswani
 */
public class JKDataAccessException extends JKException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The exception. */
	ArrayList<JKDataAccessException> exception = new ArrayList<JKDataAccessException>();

	/**
	 * Instantiates a new JK dao exception.
	 */
	public JKDataAccessException() {
		super();
	}

	/**
	 * Method 'DaoException'.
	 *
	 * @param message
	 *            String
	 */
	public JKDataAccessException(final String message) {
		super(message);
	}

	/**
	 * Method 'DaoException'.
	 *
	 * @param message
	 *            String
	 * @param cause
	 *            the cause
	 */
	public JKDataAccessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new JK dao exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public JKDataAccessException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Adds the.
	 *
	 * @param e
	 *            the e
	 */
	public void add(final JKDataAccessException e) {
		this.exception.add(e);
	}

}
