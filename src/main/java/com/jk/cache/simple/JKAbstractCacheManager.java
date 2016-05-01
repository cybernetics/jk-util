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
import java.util.logging.Logger;

import com.jk.cache.JKCacheManager;

/**
 * The Class AbstractCacheManager.
 *
 * @author Jalal Kiswani
 */
public abstract class JKAbstractCacheManager implements JKCacheManager {

	/** The Constant NULLABLE_MAP_CLASS. */
	private static final Class<Object> NULLABLE_MAP_CLASS = Object.class;

	/** The logger. */
	protected Logger logger = Logger.getLogger(getClass().getName());

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
		this.logger.fine("@cache ");

		if (object == null) {
			if (!isAllowNullable()) {
				return;
			}
			this.logger.fine("logging key :".concat(key.toString()).concat(" with null"));
			getCachableMap(JKAbstractCacheManager.NULLABLE_MAP_CLASS).put(key, null);
		} else {
			this.logger
					.fine("logging key :".concat(key.toString()).concat(" with object : ".concat(object.toString())));
			getCachableMap((Class<Object>) object.getClass()).put(key, object);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#clear(java.lang.Class)
	 */
	@Override
	public void clear(final Class<?> clas) {
		this.logger.fine("@clear:".concat(clas.getName()));
		getCachableMap(clas).clear();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#get(java.lang.Object, java.lang.Class)
	 */
	@Override
	public <T> T get(final Object key, final Class<T> clas) {
		this.logger.fine("@get :".concat(key.toString()).concat(" with class : ".concat(clas.getName())));
		final T Object = (T) getCachableMap(clas).get(key);
		return Object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#getCachableMap(java.lang.Class)
	 */
	@Override
	public Map<Object, Object> getCachableMap(final Class<?> clas) {
		this.logger.fine("@getCachableMap for class ".concat(clas.getName()));
		Map<Object, Object> map = this.cachablesMaps.get(clas);
		if (map == null) {
			this.logger.fine("Cachable map not found , create one");
			map = new HashMap<>();
			this.cachablesMaps.put(clas, map);
		} else {
			// logger.fine("map found : ".concat(map.keySet().toString()));
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
		this.logger.fine("@isAvailable :".concat(key.toString()).concat(" for class : ".concat(clas.toString())));
		final Object Object = getCachableMap(clas).get(key);
		if (Object == null) {
			this.logger.fine("try to find it on the nullable cache");
			this.logger.fine(getCachableMap(JKAbstractCacheManager.NULLABLE_MAP_CLASS).keySet().toString());
			return getCachableMap(JKAbstractCacheManager.NULLABLE_MAP_CLASS).containsKey(key);
		}
		this.logger
				.fine("key ".concat(key.toString()).concat(Object != null ? " is available in the cache" : "is not svailable"));
		return Object != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#remove(java.lang.Object, java.lang.Class)
	 */
	@Override
	public void remove(final Object key, final Class<?> clas) {
		this.logger.fine("@remove :".concat(key.toString()).concat(" with class : ".concat(clas.getName())));
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
