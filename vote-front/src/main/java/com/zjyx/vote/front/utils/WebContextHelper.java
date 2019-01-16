package com.zjyx.vote.front.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zjyx.vote.api.model.dto.UserLoginDto;
import com.zjyx.vote.common.constants.VoteConstants;

public class WebContextHelper {

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		HttpServletRequest request = attributes.getRequest();
        return request;
    }
	
	public static void setSessionValue(String name,Object value){
		getRequest().getSession().setAttribute(name, value);
	}
	
	public static Object getSessionValue(String name){
		return getRequest().getSession().getAttribute(name);
	}
	
	public static void cleanSession(){
		HttpSession session = getRequest().getSession();  
		session.invalidate();  
	}
	
	public static UserLoginDto getUser(){
		UserLoginDto userLoginDto = (UserLoginDto) getSessionValue(VoteConstants.USER_SESSION);
		return userLoginDto;
	}
	
	public static boolean isLogin(){
		return getUserId()!=null;
	}
	
	public static Long getUserId(){
		Long userId = null;
		UserLoginDto userLoginDto = (UserLoginDto) getSessionValue(VoteConstants.USER_SESSION);
		if(userLoginDto!=null && userLoginDto.getUserLogin()!=null){
			userId = userLoginDto.getUserLogin().getId();
		}
		return userId;
	}
}
