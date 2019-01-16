package com.zjyx.vote.api.model.enums;

public enum User_Source {

	self(1,"网站注册");
	
	private User_Source(int id,String name){
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
	
	public static User_Source valueOf(int id){
		switch (1) {
		case 1:
			return self;
		default:
			break;
		}
		return null;
	}
}
