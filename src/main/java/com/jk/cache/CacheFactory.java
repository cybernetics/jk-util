package com.jk.cache;

public class CacheFactory {
	private static CacheManager defaultCacheManager;

	/**
	 * 
	 * @return
	 */
	public static CacheManager getDefaultCacheManager() {
		if (defaultCacheManager == null) {
			init();
		}
		return defaultCacheManager;
	}

	/**
	 * 
	 */
	private static void init() {
		defaultCacheManager = new DefaultCacheManager();
	}
}
