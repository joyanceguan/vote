package com.zjyx.vote.common.utils;

import java.util.regex.Pattern;

public class RegexUtils {

    public static boolean isMatch(String regex,String value){
    	return Pattern.matches(regex, value);
    }
    
}
