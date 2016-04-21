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
package com.jk.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The Class AbstractCacheManager.
 *
 * @author Jalal Kiswani
 */
public abstract class AbstractCacheManager implements CacheManager {

	/** The Constant NULLABLE_MAP_CLASS. */
	private static final Class<Cacheable> NULLABLE_MAP_CLASS = Cacheable.class;

	/** The logger. */
	protected Logger logger = Logger.getLogger(getClass().getName());

	/** The cachables maps. */
	private final Map<Class<Cacheable>, Map<Object, Cacheable>> cachablesMaps = new HashMap<>();

	/** The allow nullable. */
	private boolean allowNullable = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#cache(java.lang.Object,
	 * com.jk.cache.Cacheable)
	 */
	@Override
	public void cache(final Object key, final Cacheable object) {
		this.logger.fine("@cache ");

		if (object == null) {
			if (!isAllowNullable()) {
				return;
			}
			this.logger.fine("logging key :".concat(key.toString()).concat(" with null"));
			getCachableMap(AbstractCacheManager.NULLABLE_MAP_CLASS).put(key, null);
		} else {
			this.logger
					.fine("logging key :".concat(key.toString()).concat(" with object : ".concat(object.toString())));
			getCachableMap((Class<Cacheable>) object.getClass()).put(key, object);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#clear(java.lang.Class)
	 */
	@Override
	public void clear(final Class<Cacheable> clas) {
		this.logger.fine("@clear:".concat(clas.getName()));
		getCachableMap(clas).clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#get(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Cacheable get(final Object key, final Class<Cacheable> clas) {
		this.logger.fine("@get :".concat(key.toString()).concat(" with class : ".concat(clas.getName())));
		final Cacheable cacheable = getCachableMap(clas).get(key);
		return cacheable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#getCachableMap(java.lang.Class)
	 */
	@Override
	public Map<Object, Cacheable> getCachableMap(final Class<Cacheable> clas) {
		this.logger.fine("@getCachableMap for class ".concat(clas.getName()));
		Map<Object, Cacheable> map = this.cachablesMaps.get(clas);
		if (map == null) {
			this.logger.fine("Cachable map not found , create one");
			map = new HashMap<Object, Cacheable>();
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
	public boolean isAvailable(final String key, final Class<Cacheable> clas) {
		this.logger.fine("@isAvailable :".concat(key.toString()).concat(" for class : ".concat(clas.toString())));
		final Cacheable cacheable = getCachableMap(clas).get(key);
		if (cacheable == null) {
			this.logger.fine("try to find it on the nullable cache");
			this.logger.fine(getCachableMap(AbstractCacheManager.NULLABLE_MAP_CLASS).keySet().toString());
			return getCachableMap(AbstractCacheManager.NULLABLE_MAP_CLASS).containsKey(key);
		}
		this.logger
				.fine("key ".concat(key).concat(cacheable != null ? " is available in the cache" : "is not svailable"));
		return cacheable != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.cache.CacheManager#remove(java.lang.Object, java.lang.Class)
	 */
	@Override
	public void remove(final Object key, final Class<Cacheable> clas) {
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
