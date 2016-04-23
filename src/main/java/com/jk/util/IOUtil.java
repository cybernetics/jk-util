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
package com.jk.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import com.jk.resources.JKResourceLoaderFactory;

/**
 * The Class IOUtil.
 *
 * @author Jalal Kiswani
 */
public class IOUtil {

	/** The logger. */
	static Logger logger = Logger.getLogger(IOUtil.class.getName());

	/**
	 * If the provided class has been loaded from a jar file that is on the
	 * local file system, will find the absolute path to that jar file.
	 *
	 * @param clas
	 *            the clas
	 * @return the string
	 * @throws IllegalStateException
	 *             If the specified class was loaded from a directory or in some
	 *             other way (such as via HTTP, from a database, or some other
	 *             custom classloading device).
	 */
	public static String findPathJar(final Class clas) throws IllegalStateException {
		URL url;
		String extURL;
		try {
			url = clas.getProtectionDomain().getCodeSource().getLocation();
		} catch (final SecurityException ex) {
			url = clas.getResource(clas.getSimpleName() + ".class");
		}
		extURL = url.toExternalForm();
		try {
			url = new URL(extURL);
		} catch (final MalformedURLException mux) {
			// leave url unchanged; probably does not happen
		}
		try {
			return new File(url.toURI()).toString();
		} catch (final Exception ex) {
			return new File(url.getPath()).toString();
		}

	}

	/**
	 * Gets the input stream.
	 *
	 * @param name
	 *            the name
	 * @return the input stream
	 */
	public static InputStream getInputStream(final String name) {
		return JKResourceLoaderFactory.getResourceLoader().getResourceAsStream(name);
//		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
//		if (in == null) {
//			in = ClassLoader.getSystemClassLoader().getResourceAsStream(name);
//			if (in == null) {
//				final File file = new File(name);
//				if (file.exists()) {
//					try {
//						return new FileInputStream(file);
//					} catch (final FileNotFoundException e) {
//						// Eat the exception and return null , same behavior of
//						// getResourceAsStream, for consistency purpose
//					}
//				}
//			}
//		}
//		return in;
	}

	/**
	 * Read properties file.
	 *
	 * @param file
	 *            the file
	 * @return the properties
	 */
	public static Properties readPropertiesFile(final File file) {
		if (!file.exists()) {
			IOUtil.logger.info(String.format("File %s doesnot exists , return empty map", file.getName()));
			return new Properties();
		}
		try (InputStream in = new FileInputStream(file)) {
			final Properties prop = new Properties();
			prop.load(in);
			return prop;
		} catch (final IOException e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Read stream.
	 *
	 * @param inStream
	 *            the in stream
	 * @return the byte[]
	 */
	public static byte[] readStream(final InputStream inStream) {
		try {
			DataInputStream in = null;
			try {
				in = new DataInputStream(inStream);
				final int size = in.available();
				final byte arr[] = new byte[size];
				in.readFully(arr);
				return arr;
			} finally {
				if (in != null) {
					in.close();
				}
			}
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
