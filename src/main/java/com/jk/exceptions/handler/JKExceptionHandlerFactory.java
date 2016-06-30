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

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;

import com.jk.annotations.AnnotationDetector;
import com.jk.annotations.AnnotationHandler;
import com.jk.annotations.Author;
import com.jk.util.JKObjectUtil;

/**
 * A factory for creating ExceptionHandler objects.
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class JKExceptionHandlerFactory {

	/** The instance. */
	private static JKExceptionHandlerFactory instance;

	/**
	 * Gets the single instance of ExceptionHandlerFactory.
	 *
	 * @return single instance of ExceptionHandlerFactory
	 */
	public static JKExceptionHandlerFactory getInstance() {
		if (JKExceptionHandlerFactory.instance == null) {
			JKExceptionHandlerFactory.instance = new JKExceptionHandlerFactory();
		}
		return JKExceptionHandlerFactory.instance;
	}

	/** The default exception handler. */
	private JKExceptionHandler defaultExceptionHandler = new JKDefaultExceptionHandler();

	/** The handlers. */
	private final LinkedHashMap<Class<? extends Throwable>, JKExceptionHandler> handlers = new LinkedHashMap<>();

	/**
	 * Gets the default handler.
	 *
	 * @return the default handler
	 */
	public JKExceptionHandler getDefaultHandler() {
		return this.defaultExceptionHandler;
	}

	/**
	 * Gets the handler.
	 *
	 * @param clas
	 *            the clas
	 * @return the handler
	 */
	public JKExceptionHandlerInfo getHandler(final Class<? extends Throwable> clas) {
		final JKExceptionHandler handler = this.handlers.get(clas);
		if (handler != null) {
			final JKExceptionHandlerInfo info = new JKExceptionHandlerInfo();
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
	public <T extends Throwable> JKExceptionHandlerInfo getHandler(final T t) {
		JKExceptionHandlerInfo info = getHandler(t.getClass());
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
	public void setDefaultExceptionHandler(final JKExceptionHandler defaultExceptionHandler) {
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
	public void setHandler(final Class<? extends Throwable> clas, final JKExceptionHandler handler) {
		this.handlers.put(clas, handler);
	}

	public void registerHanders(String packageString) {
		List<String> list = AnnotationDetector.scanAsList(ExceptionHandler.class, packageString);
		for (String handler : list) {
//			System.out.println(handler);						
			JKExceptionHandler<? extends Throwable> newInstance = JKObjectUtil.newInstance(handler);
			Class<? extends Throwable> clas = JKObjectUtil.getGenericParamter(handler);
			setHandler(clas, newInstance);
		}
	}



	public static void main(String[] args) {
		getInstance().registerHanders("com.jk.annotations");
		JKExceptionUtil.handle(new IOException("test exception"));
	}
}
