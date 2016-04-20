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
package com.jk.util.exceptions;

public class JKException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -432528857361517859L;

	public JKException() {
		super();
	}

	public JKException(final String message) {
		super(message);
	}

	public JKException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public JKException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JKException(final Throwable cause) {
		super(cause);
	}

}
