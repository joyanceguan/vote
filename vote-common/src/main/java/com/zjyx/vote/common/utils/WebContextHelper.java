package com.zjyx.vote.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	
	
	public static void cleanSession(){
		HttpSession session = getRequest().getSession();  
		session.invalidate();  
	}
	
	
	public static boolean isLoginAdmin(){
		HttpSession session = getRequest().getSession();
		Object admin = session.getAttribute(VoteConstants.ADMIN_USER_SESSION_NAME);
		return admin!=null && "admin".equals(admin);
	}
	
	public static void loginAdmin(){
		HttpSession session = getRequest().getSession();
		session.setAttribute(VoteConstants.ADMIN_USER_SESSION_NAME, "admin");;
	}
}
