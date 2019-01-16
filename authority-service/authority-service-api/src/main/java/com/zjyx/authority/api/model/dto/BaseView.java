package com.zjyx.authority.api.model.dto;

public class BaseView {
	
	private boolean isError=false;
	private String errorCode;
	private String errorMessage;
	private Object objects;
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getObjects() {
		return objects;
	}
	public void setObjects(Object objects) {
		this.objects = objects;
	}
	public void setBaseViewValue(String errorCode,String errorMessage){
		this.isError=true;
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
}
