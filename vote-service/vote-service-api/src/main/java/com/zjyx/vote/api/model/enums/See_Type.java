package com.zjyx.vote.api.model.enums;

public enum See_Type {

	see_result(1,"看过结果"),
	
	without_see(2,"没看过结果");
	
    private See_Type(int id,String desc){	
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
	
    public static See_Type valueOf(int id){
    	switch (id) {
		case 1:
			return see_result;
		case 2:
			return without_see;
		default:
			break;
		}
    	return null;
    }
}
