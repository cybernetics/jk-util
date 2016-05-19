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
package com.jk.exceptions.handler;

/**
 * The Class ExceptionHandlerInfo.
 *
 * @author Jalal Kiswani
 */
public class JKExceptionHandlerInfo {

	/** The handler. */
	JKExceptionHandler handler;

	/** The exception class. */
	Class<? extends Throwable> exceptionClass;

	/** The exception. */
	Throwable exception;

	/**
	 * Instantiates a new exception handler info.
	 */
	public JKExceptionHandlerInfo() {
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public Throwable getException() {
		return this.exception;
	}

	/**
	 * Gets the exception class.
	 *
	 * @return the exception class
	 */
	public Class<? extends Throwable> getExceptionClass() {
		return this.exceptionClass;
	}

	/**
	 * Gets the handler.
	 *
	 * @return the handler
	 */
	public JKExceptionHandler getHandler() {
		return this.handler;
	}

	/**
	 * Sets the exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the exception handler info
	 */
	public JKExceptionHandlerInfo setException(final Throwable exception) {
		this.exception = exception;
		return this;
	}

	/**
	 * Sets the exception class.
	 *
	 * @param exceptionClass
	 *            the exception class
	 * @return the exception handler info
	 */
	public JKExceptionHandlerInfo setExceptionClass(final Class<? extends Throwable> exceptionClass) {
		this.exceptionClass = exceptionClass;
		return this;
	}

	/**
	 * Sets the handler.
	 *
	 * @param handler
	 *            the handler
	 * @return the exception handler info
	 */
	public JKExceptionHandlerInfo setHandler(final JKExceptionHandler<?> handler) {
		this.handler = handler;
		return this;
	}

}
