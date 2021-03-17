package com.pcitc.fms.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

@SuppressWarnings("unused")
public class DISCMAp {

	private static Map<String, String> contentMaps = new ConcurrentHashMap<String, String>();

	private static Long beginTime;
	private static Integer cache_time_limit = null;


	static {
		beginTime = System.currentTimeMillis();
		String cache_time = System.getenv("cache_time");
		Integer cache_time_integer = null;
		if (StringUtils.isNotEmpty(cache_time)) {
			cache_time_integer = Integer.parseInt(cache_time);
		} else {
			cache_time_limit = 1000 * 60 * 5;
		}
		cache_time_limit = 1000 * 60 * cache_time_integer;
	}

	public static synchronized void setContentMaps(String key, String str) {
			Long currentTime = System.currentTimeMillis();
			Long cha = currentTime - beginTime;
			
			if (cha >= cache_time_limit) {
				contentMaps.clear();
				contentMaps.put(key, str);
				beginTime = currentTime;
			} else {
				contentMaps.put(key, str);
			}

	}

	public static String getContentStr(String key) {
			String res = contentMaps.get(key);
			return res;
	}

}

