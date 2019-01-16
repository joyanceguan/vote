package com.zjyx.vote.api.model.enums;

public enum Vote_Limit_Type {
	
	login(1,"登录限制"),
	iptimes(2,"ip次数限制"),
	rate(3,"频率限制");
	
	private Vote_Limit_Type(int id,String name){
		
	}
	
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static Vote_Limit_Type valueOf(int id){
		switch (id) {
		  case 1:
		  	return login;
		  case 2:
		  	return iptimes;
		  case 3:
		  	return rate;
		  default:
		  	break;
		  }
		return null;
	}
}
