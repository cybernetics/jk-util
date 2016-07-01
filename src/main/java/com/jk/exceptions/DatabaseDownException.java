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
 * The Class DatabaseDownException.
 *
 * @author Jalal Kiswani
 */
public class DatabaseDownException extends JKServerDownException {

	private static final long serialVersionUID = 8502756430257643464L;
	private final String dbName;

	/**
	 * Instantiates a new database down exception.
	 *
	 * @param ex
	 *            the ex
	 * @param dbName
	 *            the db name
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 */
	public DatabaseDownException(final Exception ex, final String dbName, final String host, final int port) {
		super(ex, host, port);
		this.dbName = dbName;
	}

	/**
	 * Gets the db name.
	 *
	 * @return the db name
	 */
	public String getDbName() {
		return this.dbName;
	}

}
