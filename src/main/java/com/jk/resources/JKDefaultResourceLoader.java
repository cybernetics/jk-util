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
