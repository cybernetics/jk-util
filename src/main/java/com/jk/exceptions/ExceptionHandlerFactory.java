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

import java.util.LinkedHashMap;

import com.jk.annotations.Author;

/**
 * A factory for creating ExceptionHandler objects.
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class ExceptionHandlerFactory {

	/** The instance. */
	private static ExceptionHandlerFactory instance;

	/**
	 * Gets the single instance of ExceptionHandlerFactory.
	 *
	 * @return single instance of ExceptionHandlerFactory
	 */
	public static ExceptionHandlerFactory getInstance() {
		if (ExceptionHandlerFactory.instance == null) {
			ExceptionHandlerFactory.instance = new ExceptionHandlerFactory();
		}
		return ExceptionHandlerFactory.instance;
	}

	/** The default exception handler. */
	private ExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();

	/** The handlers. */
	private final LinkedHashMap<Class<? extends Throwable>, ExceptionHandler> handlers = new LinkedHashMap<>();

	/**
	 * Gets the default handler.
	 *
	 * @return the default handler
	 */
	public ExceptionHandler getDefaultHandler() {
		return this.defaultExceptionHandler;
	}

	/**
	 * Gets the handler.
	 *
	 * @param clas
	 *            the clas
	 * @return the handler
	 */
	public ExceptionHandlerInfo getHandler(final Class<? extends Throwable> clas) {
		final ExceptionHandler handler = this.handlers.get(clas);
		if (handler != null) {
			final ExceptionHandlerInfo info = new ExceptionHandlerInfo();
			info.setExceptionClass(clas).setHandler(handler);
			return info;
		}
		return null;
	}

	/**
	 * Gets the handler.
	 *
	 * @param <T>
	 *            the generic type
	 * @param t
	 *            the t
	 * @return the handler
	 */
	public <T extends Throwable> ExceptionHandlerInfo getHandler(final T t) {
		ExceptionHandlerInfo info = getHandler(t.getClass());
		if (info != null) {
			info.setException(t);
		}
		if (info == null && t.getCause() != null) {
			info = getHandler(t.getCause());
		}
		return info;
	}

	/**
	 * Sets the default exception handler.
	 *
	 * @param defaultExceptionHandler
	 *            the new default exception handler
	 */
	public void setDefaultExceptionHandler(final ExceptionHandler defaultExceptionHandler) {
		this.defaultExceptionHandler = defaultExceptionHandler;
	}

	/**
	 * Sets the handler.
	 *
	 * @param clas
	 *            the clas
	 * @param handler
	 *            the handler
	 */
	public void setHandler(final Class<? extends Throwable> clas, final ExceptionHandler handler) {
		this.handlers.put(clas, handler);
	}
}
