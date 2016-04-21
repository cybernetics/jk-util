package com.jk.resources;

public class JKResourceLoaderFactory {
	private static JKResourceLoader resourceLoader;

	public static JKResourceLoader getResourceLoader() {
		if (resourceLoader == null) {
			return new JKDefaultResourceLoader();
		}
		return resourceLoader;
	}

	public static void setResourceLoader(JKResourceLoader servletResourceLoader) {
		resourceLoader = servletResourceLoader;
	}
}

