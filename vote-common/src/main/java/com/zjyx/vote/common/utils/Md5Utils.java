package com.zjyx.vote.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {

	 public static String md5(String text, String key) {
		 String encodeStr = "";
		 try{
			//加密后的字符串
		    encodeStr=DigestUtils.md5Hex(text + key);
		 }catch(Exception e){}
		 return encodeStr;
	 }

	 public static boolean verify(String text, String key, String md5){
	        //根据传入的密钥进行验证
	        String md5Text = md5(text, key);
	        if(md5Text.equalsIgnoreCase(md5)){
	            return true;
	        }
	        return false;
	 }
}
