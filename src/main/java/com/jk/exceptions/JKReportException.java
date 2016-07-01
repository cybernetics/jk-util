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

/**
 * The Class JKReportException.
 *
 * @author Jalal Kiswani
 */
public class JKReportException extends Exception {

	/**
	 * Instantiates a new JK report exception.
	 */
	public JKReportException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK report exception.
	 *
	 * @param arg0
	 *            the arg 0
	 * @param arg1
	 *            the arg 1
	 * @param arg2
	 *            the arg 2
	 * @param arg3
	 *            the arg 3
	 */
	public JKReportException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK report exception.
	 *
	 * @param arg0
	 *            the arg 0
	 * @param arg1
	 *            the arg 1
	 */
	public JKReportException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK report exception.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	public JKReportException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new JK report exception.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	public JKReportException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
