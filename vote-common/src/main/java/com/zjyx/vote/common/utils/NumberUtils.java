package com.zjyx.vote.common.utils;

public class NumberUtils {

	public static int parseInt(String val){
		int result = 0;
		try{
			result = Integer.parseInt(val);
		}catch(Exception e){}
		return result;
	}
}
