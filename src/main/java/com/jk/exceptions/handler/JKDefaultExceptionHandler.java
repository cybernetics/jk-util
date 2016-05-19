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

import java.util.logging.Logger;

import com.jk.annotations.Author;

/**
 * The Class DefaultExceptionHandler.
 *
 * @author Jalal Kiswani
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class JKDefaultExceptionHandler implements JKExceptionHandler {

	/** The logger. */
	Logger logger = Logger.getLogger(getClass().getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.exceptions.ExceptionHandler#handle(java.lang.Throwable,
	 * boolean)
	 */
	@Override
	public void handle(final Throwable throwable, final boolean throwRuntimeException) {
		this.logger.severe(throwable.getMessage());
		if (throwRuntimeException) {
			if (throwable instanceof RuntimeException) {
				throw (RuntimeException) throwable;
			}
			throw new RuntimeException(throwable);
		}
	}

}
