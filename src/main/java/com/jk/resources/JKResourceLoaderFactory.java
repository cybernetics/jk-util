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

/**
 * A factory for creating JKResourceLoader objects.
 */
public class JKResourceLoaderFactory {
	private static JKResourceLoader resourceLoader;

	/**
	 * Gets the resource loader.
	 *
	 * @return the resource loader
	 */
	public static JKResourceLoader getResourceLoader() {
		if (resourceLoader == null) {
			return new JKDefaultResourceLoader();
		}
		return resourceLoader;
	}

	/**
	 * Sets the resource loader.
	 *
	 * @param servletResourceLoader
	 *            the new resource loader
	 */
	public static void setResourceLoader(JKResourceLoader servletResourceLoader) {
		resourceLoader = servletResourceLoader;
	}
}

