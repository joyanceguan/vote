package com.zjyx.vote.api.model.enums;

public enum Vote_Status {

	normal(100,"正常"),
	
	close(200,"关闭");
	
	private Vote_Status(int id,String desc){
		this.id = id;
		this.desc = desc;
	}
	
	private int id;
	private String desc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static Vote_Status valueOf(int id){
		switch (id) {
		   case 100:
		   	return normal;
		   case 200:
		   	return close;
		   default:
		   	break;
		   }
		return null;
	}
}
