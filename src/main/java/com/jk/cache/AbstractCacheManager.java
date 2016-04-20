package com.jk.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class AbstractCacheManager implements CacheManager {
	private static final Class<Cacheable> NULLABLE_MAP_CLASS = Cacheable.class;
	protected Logger logger = Logger.getLogger(getClass().getName());
	private Map<Class<Cacheable>, Map<Object, Cacheable>> cachablesMaps = new HashMap<>();
	private boolean allowNullable = true;

	@Override
	public void cache(Object key, Cacheable object) {
		logger.fine("@cache ");

		if (object == null) {
			if (!isAllowNullable()) {
				return;
			}
			logger.fine("logging key :".concat(key.toString()).concat(" with null"));
			getCachableMap(NULLABLE_MAP_CLASS).put(key, null);
		} else {
			logger.fine("logging key :".concat(key.toString()).concat(" with object : ".concat(object.toString())));
			getCachableMap((Class<Cacheable>) object.getClass()).put(key, object);
		}

	}

	@Override
	public Cacheable get(Object key, Class<Cacheable> clas) {
		logger.fine("@get :".concat(key.toString()).concat(" with class : ".concat(clas.getName())));
		Cacheable cacheable = getCachableMap(clas).get(key);
		return cacheable;
	}

	@Override
	public void remove(Object key, Class<Cacheable> clas) {
		logger.fine("@remove :".concat(key.toString()).concat(" with class : ".concat(clas.getName())));
		getCachableMap(clas).remove(key);
	}

	@Override
	public void clear(Class<Cacheable> clas) {
		logger.fine("@clear:".concat(clas.getName()));
		getCachableMap(clas).clear();
	}

	@Override
	public Map<Object, Cacheable> getCachableMap(Class<Cacheable> clas) {
		logger.fine("@getCachableMap for class ".concat(clas.getName()));
		Map<Object, Cacheable> map = cachablesMaps.get(clas);
		if (map == null) {
			logger.fine("Cachable map not found , create one");
			map = new HashMap<Object, Cacheable>();
			cachablesMaps.put(clas, map);
		} else {
			// logger.fine("map found : ".concat(map.keySet().toString()));
		}
		return map;
	}

	public boolean isAvailable(String key, Class<Cacheable> clas) {
		logger.fine("@isAvailable :".concat(key.toString()).concat(" for class : ".concat(clas.toString())));
		Cacheable cacheable = getCachableMap(clas).get(key);
		if (cacheable == null) {
			logger.fine("try to find it on the nullable cache");
			logger.fine(getCachableMap(NULLABLE_MAP_CLASS).keySet().toString());
			return getCachableMap(NULLABLE_MAP_CLASS).containsKey(key);
		}
		logger.fine("key ".concat(key).concat(cacheable != null ? " is available in the cache" : "is not svailable"));
		return cacheable != null;
	}

	public boolean isAllowNullable() {
		return allowNullable;
	}

	public void setAllowNullable(boolean allowNullable) {
		this.allowNullable = allowNullable;
	}

}
