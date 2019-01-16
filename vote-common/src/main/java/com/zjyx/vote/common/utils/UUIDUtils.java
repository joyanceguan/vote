package com.zjyx.vote.common.utils;

import java.util.UUID;

public class UUIDUtils {

	public static String getUUId(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
}
