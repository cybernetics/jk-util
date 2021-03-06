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

import java.util.Map;

/**
 * The Interface CacheManager.
 *
 * @author Jalal Kiswani
 */
public interface JKCacheManager {

	/**
	 * Cache.
	 *
	 * @param key
	 *            the key
	 * @param cache
	 *            the cache
	 */
	public void cache(Object key, Object cache);

	/**
	 * Clear.
	 *
	 * @param clas
	 *            the clas
	 */
	public void clear(Class<?> clas);

	/**
	 * Gets the.
	 *
	 * @param <T>
	 *            the generic type
	 * @param key
	 *            the key
	 * @param clas
	 *            the clas
	 * @return the cacheable
	 */
	public <T> T get(Object key, Class<T> clas);

	/**
	 * Gets the cachable map.
	 *
	 * @param clas
	 *            the clas
	 * @return the cachable map
	 */
	public  Map<Object, ?> getCachableMap(Class<?> clas);

	/**
	 * Checks if is available.
	 *
	 * @param id
	 *            the id
	 * @param clas
	 *            the clas
	 * @return true, if is available
	 */
	public boolean isAvailable(Object id, Class<?> clas);

	/**
	 * Removes the.
	 *
	 * @param key
	 *            the key
	 * @param clas
	 *            the clas
	 */
	public void remove(Object key, Class<?> clas);

	public <T> void cache(Object key, Object object, Class<T> clas);
}
