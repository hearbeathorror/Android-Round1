package com.hqinterview.dhara.utils;

import android.support.v4.util.LruCache;

import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.utils.constants.Global;

public class CacheUtilities {
	private static LruCache<String, Object> lruCache;
	private static final int MAX_SIZE = 1024 * 1024 * 8;
	
	public static void init(){
		lruCache = new LruCache<String, Object>(MAX_SIZE);
	}
	
	private static LruCache<String, Object> getInstance(){
		if(lruCache == null) {
			init();
		}
		return lruCache;
	}
	
	public static void storeDataIntoCache(Object object) {
		lruCache = getInstance();
		lruCache.put(Global.CACHE_DATA, (BaseResponseHolder)object);
	}
	
	public static Object getDataFromCache(String whatToGet){
		if(whatToGet.equals(Global.CACHE_DATA)) {
			Object obj = lruCache.get(Global.CACHE_DATA);
			if(obj!= null && obj instanceof BaseResponseHolder) {
				return (BaseResponseHolder)obj;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}
