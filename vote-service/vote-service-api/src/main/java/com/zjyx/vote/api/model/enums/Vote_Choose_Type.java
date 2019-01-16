package com.zjyx.vote.api.model.enums;

public enum Vote_Choose_Type {

	single(1,"单选"),
	two(2,"二选"),
	three(3,"三选"),
	four(4,"四选"),
	five(5,"五选");
	
	private Vote_Choose_Type(int id,String desc){
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
	public static Vote_Choose_Type valueOf(int id){
		switch (id) {
		  case 1:
		  	return single;
		  case 2:
		  	return two;
		  case 3:
		  	return three;
		  case 4:
		  	return four;
		  case 5:
		  	return five;
		  default:
		  	break;
		  }
		return null;
	}
}
