package com.zjyx.vote.api.model.enums;

public enum Vote_Option_Type {

	character(1,"文字"),
	picture(2,"图片");
	
	private Vote_Option_Type(int id,String desc){
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
	
	public static Vote_Option_Type valueOf(int id){
		switch (id) {
		  case 1:
		  	return character;
		  case 2:
		  	return picture;
		  default:
		  	break;
		}
		return null;
	}
}
