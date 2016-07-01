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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import com.jk.exceptions.JKException;
import com.jk.exceptions.handler.JKExceptionUtil;
import com.jk.resources.JKResourceLoaderFactory;

/**
 * The Class IOUtil.
 *
 * @author Jalal Kiswani
 */
public class JKIOUtil {
	private static final String USER_LOCAL_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "jk";
	
	/** The Constant NEW_LINE. */
	public static final String NEW_LINE = System.getProperty("line.separator");
	/** The logger. */
	static Logger logger = Logger.getLogger(JKIOUtil.class.getName());

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
		// InputStream in =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		// if (in == null) {
		// in = ClassLoader.getSystemClassLoader().getResourceAsStream(name);
		// if (in == null) {
		// final File file = new File(name);
		// if (file.exists()) {
		// try {
		// return new FileInputStream(file);
		// } catch (final FileNotFoundException e) {
		// // Eat the exception and return null , same behavior of
		// // getResourceAsStream, for consistency purpose
		// }
		// }
		// }
		// }
		// return in;
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
			JKIOUtil.logger.info(String.format("File %s doesnot exists , return empty map", file.getName()));
			return new Properties();
		}
		try {
			InputStream in = new FileInputStream(file);
			if (in != null) {
				return readPropertiesStream(in);
			}
		} catch (IOException e) {
			JKExceptionUtil.handle(e);
		}
		return null;
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

	/**
	 * Convert to string.
	 *
	 * @param input
	 *            the input
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String convertToString(InputStream input) throws IOException {
		try {
			if (input == null) {
				throw new IOException("Input Stream Cannot be NULL");
			}
			StringBuilder sb1 = new StringBuilder();
			String line;
			try {
				BufferedReader r1 = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				while ((line = r1.readLine()) != null) {
					sb1.append(line);
				}
			} finally {
				input.close();
			}
			return sb1.toString();
		} catch (IOException e) {
			throw new JKException(e);
		}
	}

	/**
	 * Gets the user folder path.
	 *
	 * @param appendFileSeprator
	 *            the append file seprator
	 * @return the user folder path
	 */
	// ////////////////////////////////////////////////////////////////////
	public static String getUserFolderPath(final boolean appendFileSeprator) {
		String path = USER_LOCAL_PATH;
		checkFolderPath(path, true);// to create the folder if not exist
		if (appendFileSeprator) {
			path += System.getProperty("file.separator");
		}
		return path;
	}

	/**
	 * Check folder path.
	 *
	 * @param path
	 *            the path
	 * @param create
	 *            the create
	 * @return the file
	 */
	// ////////////////////////////////////////////////////////////////////
	public static File checkFolderPath(final String path, final boolean create) {
		final File file = new File(path);
		if (!file.exists()) {
			if (create) {
				file.mkdir();
			}
			return null;
		}
		return file;
	}

	/**
	 * Gets the reader.
	 *
	 * @param name
	 *            the name
	 * @return the reader
	 */
	public static Reader getReader(String name) {
		InputStream inputStream = getInputStream(name);
		if (inputStream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			return reader;
		}
		return null;
	}

	/**
	 * Read file.
	 *
	 * @param string
	 *            the string
	 * @return the string
	 */
	public static String readFile(String string) {
		InputStream inputStream = getInputStream(string);
		if (inputStream != null) {
			return new String(readStream(inputStream));
		}
		return null;
	}

	/**
	 * Gets the url contents.
	 *
	 * @param urlString
	 *            the url string
	 * @return the url contents
	 */
	public static String getUrlContents(String urlString) {
		HttpURLConnection con = null;
		try {
			URL url = new URL(urlString);
			con = (HttpURLConnection) url.openConnection();
			con.connect();
			InputStream inputStream = con.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer contents = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				contents.append(line);
				contents.append(NEW_LINE);
			}
			inputStream.close();
			return contents.toString();
		} catch (Exception e) {
			JKExceptionUtil.handle(e);
			return null;// unreachable
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	/**
	 * Gets the value from url.
	 *
	 * @param url
	 *            the url
	 * @param preText
	 *            the pre text
	 * @param length
	 *            the length
	 * @return the value from url
	 */
	public static String getValueFromUrl(String url, String preText, int length) {
		String urlContents = getUrlContents(url);
		int indexOf = urlContents.indexOf(preText);
		if (indexOf != -1) {
			indexOf += preText.length();
			String substring = urlContents.substring(indexOf, indexOf + length);
			return substring;
		}
		return null;
	}

	/**
	 * Gets the extension.
	 *
	 * @param fileName
	 *            the file name
	 * @param withPoint
	 *            the with point
	 * @return the extension
	 */
	public static String getExtension(final String fileName, final boolean withPoint) {
		final int lastIndexOf = fileName.lastIndexOf(".");
		if (!withPoint) {
			return fileName.substring(lastIndexOf + 1);
		}
		return fileName.substring(lastIndexOf);
	}

	/**
	 * Removes the extension.
	 *
	 * @param fileName
	 *            the file name
	 * @return the string
	 */
	public static String removeExtension(String fileName) {
		final String separator = System.getProperty("file.separator");
		String filename;
		// Remove the path upto the filename.
		final int lastSeparatorIndex = fileName.lastIndexOf(separator);
		if (lastSeparatorIndex == -1) {
			filename = fileName;
		} else {
			filename = fileName.substring(lastSeparatorIndex + 1);
		}

		// Remove the extension.
		final int extensionIndex = filename.lastIndexOf(".");
		if (extensionIndex == -1) {
			return filename;
		}
		fileName = fileName.substring(0, lastSeparatorIndex);
		return fileName + File.separator + filename.substring(0, extensionIndex);
	}

	/**
	 * Write data to file.
	 *
	 * @param data
	 *            the data
	 * @param file
	 *            the file
	 * @return the file
	 */
	public static File writeDataToFile(final byte[] data, final File file) {
		return writeDataToFile(data, file, false);
	}

	/**
	 * Write data to file.
	 *
	 * @param data
	 *            the data
	 * @param file
	 *            the file
	 * @param append
	 *            the append
	 * @return the file
	 */
	public static File writeDataToFile(final byte[] data, final File file, final boolean append) {
		try (FileOutputStream out = new FileOutputStream(file, append)) {
			out.write(data);
			out.close();
			return file;
		} catch (Exception e) {
			JKExceptionUtil.handle(e);
			return null;
		}
	}

	/**
	 * Read properties stream.
	 *
	 * @param inputStream
	 *            the input stream
	 * @return the properties
	 */
	public static Properties readPropertiesStream(InputStream inputStream) {
		try {
			final Properties prop = new Properties();
			prop.load(inputStream);
			return prop;
		} catch (IOException e) {
			JKExceptionUtil.handle(e);
			return null;
		} finally {
			close(inputStream);
		}

	}

	private static void close(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				// It is safe to eat this exception
			}
		}
	}

	/**
	 * Write data to temp file.
	 *
	 * @param data
	 *            the data
	 * @param suffix
	 *            the suffix
	 * @return the file
	 */
	public static File writeDataToTempFile(final byte[] data, final String suffix) {
		try {
			File file = File.createTempFile("fs-", suffix);
			return writeDataToFile(data, file);
		} catch (IOException e) {
			JKExceptionUtil.handle(e);
			return null;
		}
	}

	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Write data to temp file.
	 *
	 * @param data
	 *            String
	 * @param ext
	 *            the ext
	 * @return File
	 */
	public static File writeDataToTempFile(final String data, final String ext) {
		try {
			final File file = createTempFile(ext);
			final PrintWriter out = new PrintWriter(new FileOutputStream(file));
			out.print(data);
			out.close();
			return file;
		} catch (IOException e) {
			JKExceptionUtil.handle(e);
			return null;
		}
	}

	/**
	 * Creates the temp file.
	 *
	 * @param ext
	 *            the ext
	 * @return the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File createTempFile(final String ext) throws IOException {
		final File file = File.createTempFile("fs-", "." + ext);
		return file;
	}

	/**
	 * Gets the url.
	 *
	 * @param path
	 *            the path
	 * @return the url
	 */
	public static URL getURL(String path) {
		return JKResourceLoaderFactory.getResourceLoader().getResourceUrl(path);
	}

	/**
	 * Read properties file.
	 *
	 * @param fileName
	 *            the file name
	 * @return the properties
	 */
	public static Properties readPropertiesFile(String fileName) {
		InputStream in = getInputStream(fileName);
		if (in != null) {
			return readPropertiesStream(in);
		}
		return new Properties();
	}

	/**
	 * Gets the report file as stream.
	 *
	 * @param name
	 *            the name
	 * @return the report file as stream
	 */
	public static InputStream getReportFileAsStream(String name) {
		//TODO: make it configurable for both path and extension
		return getInputStream("/resources/reports/".concat(name).concat(".jrxml"));
	}
}
