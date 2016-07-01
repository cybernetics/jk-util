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
package com.jk.annotations;

import java.io.IOException;

import com.jk.exceptions.handler.ExceptionHandler;
import com.jk.exceptions.handler.JKExceptionHandler;

/**
 * The Class Test.
 *
 * @author Jalal Kiswani
 */
@ExceptionHandler
public class Test implements JKExceptionHandler<IOException> {

	/* (non-Javadoc)
	 * @see com.jk.exceptions.handler.JKExceptionHandler#handle(java.lang.Throwable, boolean)
	 */
	@Override
	public void handle(IOException throwable, boolean throwRuntimeException) {
		System.out.println("Hayne");
	}

}
