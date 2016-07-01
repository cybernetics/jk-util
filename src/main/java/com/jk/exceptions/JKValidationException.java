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

import com.fs.commons.desktop.validation.Problems;

/**
 * The Class JKValidationException.
 *
 * @author Jalal Kiswani
 */
public class JKValidationException extends JKException implements JKNonPrintableException {
	Problems problems;

	/**
	 * Instantiates a new JK validation exception.
	 */
	public JKValidationException() {
		super();
	}

	/**
	 * Instantiates a new JK validation exception.
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
	public JKValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new JK validation exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public JKValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new JK validation exception.
	 *
	 * @param message
	 *            the message
	 */
	public JKValidationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new JK validation exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public JKValidationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new JK validation exception.
	 *
	 * @param problems
	 *            the problems
	 */
	public JKValidationException(Problems problems) {
		this.problems = problems;
	}
	 
 	/**
	 * Gets the problems.
	 *
	 * @return the problems
	 */
 	public Problems getProblems() {
		return problems;
	}

}
