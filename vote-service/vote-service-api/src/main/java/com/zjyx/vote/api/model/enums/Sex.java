package com.zjyx.vote.api.model.enums;

public enum Sex {

	male(1,"男"),
	female(2,"女"),
	unknown(3,"未知");
	
	private Sex(int id,String desc){
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
	public static Sex valueOf(int id){
		switch (id) {
		  case 1:
		  	return male;
		  case 2:
		  	return female;
		  case 3:
			return unknown;
		  default:
		  	break;
		  }
		return null;
	}
}
