package com.zjyx.authority.api.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseObject {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private Date ctime;
	

	public String getCtime() {
		if(ctime!=null){
			return sdf.format(ctime);
		}
		return null;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
