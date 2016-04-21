package com.jk.resources;

import java.io.InputStream;
import java.net.URL;

public interface JKResourceLoader {
	
	/**
	 * @param resourceName
	 * @return
	 */
	public InputStream getResourceAsStream(String resourceName);

	public URL getResourceUrl(String fileName);
}
