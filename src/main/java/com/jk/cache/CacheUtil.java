package com.jk.cache;

import java.util.Arrays;

public class CacheUtil {

	public static String buildDynamicKey(Object[] paramNames, Object[] paramValues) {
		return Arrays.toString(paramNames).concat(Arrays.toString(paramValues));
	}

}
