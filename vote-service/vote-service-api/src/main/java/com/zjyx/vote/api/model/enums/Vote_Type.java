package com.zjyx.vote.api.model.enums;

public enum Vote_Type {

	single(1,"单选"),
	mutiple(2,"多选"),
	help(3,"助力");
	
	private Vote_Type(int id,String desc){
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
	public static Vote_Type valueOf(int id){
		switch (id) {
		  case 1:
		  	return single;
		  case 2:
		  	return mutiple;
		  case 3:
		  	return help;
		  default:
		  	break;
		  }
		return null;
	}
}
