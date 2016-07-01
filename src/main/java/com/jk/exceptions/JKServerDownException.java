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

import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * The Class JKServerDownException.
 *
 * @author Jalal Kiswani
 */
public class JKServerDownException extends JKException {

	/**
	 *
	 */
	private static final long serialVersionUID = -2582331807098593228L;
	private int port;
	private String host;

	/**
	 * Instantiates a new JK server down exception.
	 *
	 * @param ex
	 *            the ex
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 */
	public JKServerDownException(final Exception ex, final String host, final int port) {
		super(ex);
		this.host = host;
		this.port = port;
	}

	/**
	 * Instantiates a new JK server down exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public JKServerDownException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return this.host;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		if (getCause() instanceof UnknownHostException) {
			return "Host (" + getHost() + ") is unreachable !!!!";
		}
		if (getCause() instanceof ConnectException) {
			return "Host (" + getHost() + ") is unreachable at Port (" + getPort() + ")!!!!";
		}
		return getCause().getMessage();
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Sets the host.
	 *
	 * @param host
	 *            the host to set
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	/**
	 * Sets the port.
	 *
	 * @param port
	 *            the port to set
	 */
	public void setPort(final int port) {
		this.port = port;
	}

}
