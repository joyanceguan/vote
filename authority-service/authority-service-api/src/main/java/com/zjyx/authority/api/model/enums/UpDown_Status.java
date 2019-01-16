package com.zjyx.authority.api.model.enums;

/**
 * 上下架状�?
 */
public enum UpDown_Status {
	
    UP(1,"上架"),
	
	DOWN(0,"下架");
	
	private int key;
	
	private String value;
	
	private UpDown_Status(int key,String value){
		this.key=key;
		this.value=value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static UpDown_Status valueOf(int key){
		 switch (key) {
         case 0:
             return DOWN;
         case 1:
             return UP;
         default:
             return null;
     }
	}
}
