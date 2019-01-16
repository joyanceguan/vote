package com.zjyx.vote.api.model.enums;

public enum User_Status {

	normal(1,"正常"),
	freeze(2,"冻结");
	
	private User_Status(int id,String name){
		this.id = id;
		this.name = name;
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
	
	public static User_Status valueOf(int id){
		switch (id) {
		  case 1:
		  	return normal;
		  case 2:
		  	return freeze;
		  default:
		  	break;
		}
		return null;
	}
}
