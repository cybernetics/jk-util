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
 * The Interface ExceptionHandler.
 *
 * @author Jalal Kiswani
 * @param <T>
 *            the generic type
 */
@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public interface JKExceptionHandler<T extends Throwable> {

	/**
	 * Handle.
	 *
	 * @param throwable
	 *            the throwable
	 * @param throwRuntimeException
	 *            the throw runtime exception
	 */
	public void handle(T throwable, boolean throwRuntimeException);
}
