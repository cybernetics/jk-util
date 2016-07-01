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
package com.jk.mail;

import java.io.IOException;
import java.io.InputStream;

import com.jk.util.JKIOUtil;

/**
 * The Class Attachment.
 *
 * @author Jalal Kiswani
 */
public class Attachment {
	
	/**
	 * The Enum MimeType.
	 *
	 * @author Jalal Kiswani
	 */
	public enum MimeType {
	}

	String name;
	byte[] data;
	String description;
	String mimeType;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the mime type.
	 *
	 * @return the mimeType
	 */
	public String getMimeType() {
		return this.mimeType;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the data to set
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	/**
	 * Sets the data.
	 *
	 * @param in
	 *            the new data
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void setData(final InputStream in) throws IOException {
		final byte[] bytes = JKIOUtil.readStream(in);
		setData(bytes);
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the mime type.
	 *
	 * @param mimeType
	 *            the mimeType to set
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
}
