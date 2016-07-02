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
package com.jk.cache.simple;

import java.util.HashMap;
import java.util.Map;

import com.jk.cache.JKCacheManager;
import com.jk.logging.JKLogger;
import com.jk.logging.JKLoggerFactory;

/**
 * The Class AbstractCacheManager.
 *
 * @author Jalal Kiswani
 */
public abstract class JKAbstractCacheManager implements JKCacheManager {

	/** The Constant NULLABLE_MAP_CLASS. */
	private static final Class<Object> NULLABLE_MAP_CLASS = Object.class;

	/** The logger. */
	protected JKLogger logger = JKLoggerFactory.getLogger(getClass());

	/** The cachables maps. */
	private final Map<Class<?>, Map<Object, Object>> cachablesMaps = new HashMap<>();

	/** The allow nullable. */
	private boolean allowNullable = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#cache(java.lang.Object,
	 * com.jk.cache.Object)
	 */
	@Override
	public void cache(final Object key, final Object object) {
		this.logger.debug("@cache ");

		if (object == null) {
			if (!isAllowNullable()) {
				return;
			}
			this.logger.debug("logging key :", key, " with null");
			getCachableMap(JKAbstractCacheManager.NULLABLE_MAP_CLASS).put(key, null);
		} else {
			cache(key, object, object.getClass());
		}
	}

	@Override
	public <T> void cache(final Object key, final Object object, Class<T> clas) {
		this.logger.debug("@cache v2 ");

		if (object == null && !isAllowNullable()) {
			return;
		} else {
			this.logger.debug("logging key :", key, " with object : ", object, " with Class : ", clas);
			getCachableMap(clas).put(key, object);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#clear(java.lang.Class)
	 */
	@Override
	public void clear(final Class<?> clas) {
		this.logger.debug("@clear:".concat(clas.getName()));
		getCachableMap(clas).clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#get(java.lang.Object, java.lang.Class)
	 */
	@Override
	public <T> T get(final Object key, final Class<T> clas) {
		this.logger.debug("@get :", key, " with class : ", clas.getName());
		final T Object = (T) getCachableMap(clas).get(key);
		logger.debug("Cached object : ", Object);
		return Object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#getCachableMap(java.lang.Class)
	 */
	@Override
	public Map<Object, Object> getCachableMap(final Class<?> clas) {
		this.logger.debug("@getCachableMap for class ", clas.getName());
		Map<Object, Object> map = this.cachablesMaps.get(clas);
		if (map == null) {
			this.logger.debug("Cachable map not found , create one");
			map = new HashMap<>();
			this.cachablesMaps.put(clas, map);
		} else {
			// logger.debug("map found : ".concat(map.keySet().toString()));
		}
		return map;
	}

	/**
	 * Checks if is allow nullable.
	 *
	 * @return true, if is allow nullable
	 */
	public boolean isAllowNullable() {
		return this.allowNullable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#isAvailable(java.lang.String,
	 * java.lang.Class)
	 */
	@Override
	public boolean isAvailable(final Object key, final Class<?> clas) {
		this.logger.debug("@isAvailable :".concat(key.toString()).concat(" for class : ".concat(clas.toString())));
		final Object Object = getCachableMap(clas).get(key);
		if (Object == null) {
			this.logger.debug("try to find it on the nullable cache");
			this.logger.debug(getCachableMap(JKAbstractCacheManager.NULLABLE_MAP_CLASS).keySet().toString());
			return getCachableMap(JKAbstractCacheManager.NULLABLE_MAP_CLASS).containsKey(key);
		}
		this.logger.debug("key ".concat(key.toString()).concat(Object != null ? " is available in the cache" : "is not svailable"));
		return Object != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#remove(java.lang.Object, java.lang.Class)
	 */
	@Override
	public void remove(final Object key, final Class<?> clas) {
		this.logger.debug("@remove :".concat(key.toString()).concat(" with class : ".concat(clas.getName())));
		getCachableMap(clas).remove(key);
	}

	/**
	 * Sets the allow nullable.
	 *
	 * @param allowNullable
	 *            the new allow nullable
	 */
	public void setAllowNullable(final boolean allowNullable) {
		this.allowNullable = allowNullable;
	}

}
