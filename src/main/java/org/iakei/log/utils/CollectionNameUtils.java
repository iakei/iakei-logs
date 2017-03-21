package org.iakei.log.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CollectionNameUtils {

	private static final SimpleDateFormat YYYY_MM_DD;
	private static final String LOGGER_COLLECTION_NAME_PREFIX="log_";
	//private static ConcurrentHashMap<String, String> date=new ConcurrentHashMap<>();
	static{
		YYYY_MM_DD=new SimpleDateFormat("yyyyMMdd");
	}
	public static String getCollectionName(String module){
		return LOGGER_COLLECTION_NAME_PREFIX+module+YYYY_MM_DD.format(new Date());
	}
	
}
