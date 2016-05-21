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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.jk.exceptions.JKException;

public class JKDefaultResourceLoader implements JKResourceLoader {

	@Override
	public InputStream getResourceAsStream(String resourceName) {
		URL url = getResourceUrl(resourceName);
		if (url != null) {
			try {
				return url.openStream();
			} catch (IOException e) {
				throw new JKException(e);
			}
		}
		return null;
	}

	@Override
	public URL getResourceUrl(String fileName) {
		if(fileName==null){
			return null;
		}
		URL resource = getClass().getResource(fileName);
		if (resource == null) {
			resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
			if (resource == null) {
				resource = ClassLoader.getSystemResource(fileName);
				if (resource == null) {
					File file = new File(fileName);
					if (file.exists()) {
						try {
							resource = file.toURI().toURL();
						} catch (MalformedURLException e) {
							throw new JKException(e);
						}
					}
				}
			}
		}
		return resource;
	}
}
