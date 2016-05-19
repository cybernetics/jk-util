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

import com.jk.annotations.Author;

/**
 * The Class ExceptionUtil.
 *
 * @author Jalal Kiswani
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class JKExceptionUtil {

	/**
	 * Handle.
	 *
	 * @param t
	 *            the t
	 */
	public static void handle(final Throwable t) {
		JKExceptionUtil.handle(t, true);
	}

	/**
	 * Handle.
	 *
	 * @param t
	 *            the t
	 * @param throwRuntimeException
	 *            the throw runtime exception
	 */
	public static void handle(final Throwable t, final boolean throwRuntimeException) {
		final JKExceptionHandlerFactory factory = JKExceptionHandlerFactory.getInstance();
		final JKExceptionHandlerInfo info = factory.getHandler(t);
		if (info != null) {
			info.getHandler().handle(info.getException(), throwRuntimeException);
		} else {
			factory.getDefaultHandler().handle(t, throwRuntimeException);
		}

	}

}
