package com.jk.cache;

import java.util.Map;

public interface CacheManager {
	public void cache(Object key, Cacheable cache);

	public Cacheable get(Object key, Class<Cacheable> clas);

	public void remove(Object key, Class<Cacheable> clas);

	public void clear(Class<Cacheable> clas);

	public Map<Object, Cacheable> getCachableMap(Class<Cacheable> clas);

	public boolean isAvailable(String id, Class<Cacheable> clas);
}
