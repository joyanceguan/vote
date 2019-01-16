package com.zjyx.vote.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtils {

	/*
	 * 1.String转成date类型
	 * 2.date转成String类型
	 * 3.date加减天数
	 * 4.String加减天数
	 * 5.两个日期之间的天数
	 */
	
	public static final String DAY = "yyyy-MM-dd";
	public static final String DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT1="yyyy/MM/dd HH:mm:ss";
	public static final String FORMAT2="yyyy/MM/dd";
	public static final String FORMAT3="yyyy-MM-dd HH:mm";
	
	public static String parseStringFromDate(Date date, String pattern) {
		if (date != null) {
			pattern = StringUtils.isEmpty(pattern)?DEFAULT:pattern;
			return new SimpleDateFormat(pattern).format(date);
		}
		return null;
	}
	
	public static String parseStringFromDate(Date date){
		return parseStringFromDate(date, null);
	}
	
	public static Date parseDateFromString(String date,String pattern){
		if(!StringUtils.isEmpty(date)){
			try {
				pattern = StringUtils.isEmpty(pattern)?DEFAULT:pattern;
				return new SimpleDateFormat(pattern).parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Date parseDateFromString(String date){
		return parseDateFromString(date,null);
	}
	
	public static Date addTime(Date date,int field,int duration){
		if(date!=null){
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			c.add(field, duration);
			return c.getTime();
		}
		return null;
	}
	
	public static Date addDay(Date date,int days){  
		 return addTime(date,Calendar.DAY_OF_MONTH,days);
	}
	
	public static Date addMonth(Date date,int months){  
		 return addTime(date,Calendar.MONTH,months);
	}
	
	public static Date addYear(Date date,int years){
		return addTime(date,Calendar.YEAR,years);
	}
	
	public static Date addHour(Date date,int hours){
		return addTime(date,Calendar.HOUR,hours);
	}
	
	public static Date addMinute(Date date,int minutes){
		return addTime(date,Calendar.MINUTE,minutes);
	}
	
	public static Date addSecond(Date date,int seconds){
		return addTime(date,Calendar.SECOND,seconds);
	}
	
	public static Integer daysBetween(Date start, Date end) {
		if(start!=null && end!=null){
			Date startDate=parseDay(start);
			Date endDate=parseDay(end);
			int days = (int)((endDate.getTime() - startDate.getTime())/86400000);
			return days;
		}
		return null;
	}
	
	public static Date parseDay(Date date){
		if(date!=null){
			return parseDateFromString(parseStringFromDate(date),DAY);
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtils.parseStringFromDate(new Date()));
		System.out.println(DateUtils.parseDateFromString("2016-02-17 14:03:13"));
		System.out.println(DateUtils.addMonth(new Date(), 1));
		System.out.println(DateUtils.daysBetween(DateUtils.parseDateFromString("2016-02-17 14:03:13"), new Date()));
	}
}
