package com.jk.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class IOUtil {
	static Logger logger = Logger.getLogger(IOUtil.class.getName());

	/**
	 * 
	 * @param inStream
	 * @return
	 */
	public static byte[] readStream(InputStream inStream) {
		try {
			DataInputStream in = null;
			try {
				in = new DataInputStream(inStream);
				int size = in.available();
				byte arr[] = new byte[size];
				in.readFully(arr);
				return arr;
			} finally {
				if (in != null) {
					in.close();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static Properties readPropertiesFile(File file) {
		if (!file.exists()) {
			logger.info(String.format("File %s doesnot exists , return empty map", file.getName()));
			return new Properties();
		}
		try (InputStream in = new FileInputStream(file)) {
			Properties prop = new Properties();
			prop.load(in);
			return prop;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
