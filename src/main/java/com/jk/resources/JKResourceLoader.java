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
package com.jk.resources;

import java.io.InputStream;
import java.net.URL;

/**
 * The Interface JKResourceLoader.
 *
 * @author Jalal Kiswani
 */
public interface JKResourceLoader {
	
	/**
	 * Gets the resource as stream.
	 *
	 * @param resourceName
	 *            the resource name
	 * @return the resource as stream
	 */
	public InputStream getResourceAsStream(String resourceName);

	/**
	 * Gets the resource url.
	 *
	 * @param fileName
	 *            the file name
	 * @return the resource url
	 */
	public URL getResourceUrl(String fileName);
}
