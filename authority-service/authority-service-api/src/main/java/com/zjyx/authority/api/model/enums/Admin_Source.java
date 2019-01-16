package com.zjyx.authority.api.model.enums;

public enum Admin_Source {

	WEIXIN(1,"微信");
	
	private int key;
	private String value;
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
	
	private Admin_Source(int key,String value){
		this.key=key;
		this.value=value;
	}
	
	public static Admin_Source valueOf(int key){
		switch (key) {
		case 1:
			return WEIXIN;
		default:
			break;
		}
		return null;
	}
}
