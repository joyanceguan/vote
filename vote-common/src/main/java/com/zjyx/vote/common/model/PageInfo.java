package com.zjyx.vote.common.model;

import java.util.List;

import com.zjyx.vote.common.enums.Error_Type;


public class PageInfo<T>{
	
	 private Error_Type errorType = Error_Type.SUCCESS;
	   
	 private String errorCode;
	   
	 private String errorMessage;
	
     private int currentPage;
	 
	 private int onePageSize;
	        
	 private long totalResults;
	        
	 private int totalPage;
	        
	 private List<T> objects;
	 
	 private Object extendInfo;
	 
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOnePageSize() {
		return onePageSize;
	}

	public void setOnePageSize(int onePageSize) {
		this.onePageSize = onePageSize;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getObjects() {
		return objects;
	}

	public void setObjects(List<T> objects) {
		this.objects = objects;
	}
	
	public static int getTotalPage(long totalResults,int onePageSize){
		double totalPage=(double)totalResults/onePageSize;
    	return (int) Math.ceil(totalPage);
    }
	
	
	public void setPageInfo(BasePageCondition cdtn,long totalResults,List<T> objects,Object extendInfo){
   	    this.setCurrentPage(cdtn.getCurrentPage());
   	    this.setOnePageSize(cdtn.getOnePageSize());
		this.setTotalResults(totalResults);
     	this.setTotalPage(getTotalPage(totalResults,onePageSize));
     	this.setObjects(objects);
     	this.setExtendInfo(extendInfo);
    }

	public Object getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(Object extendInfo) {
		this.extendInfo = extendInfo;
	}

	public Error_Type getErrorType() {
		return errorType;
	}

	public void setErrorType(Error_Type errorType) {
		this.errorType = errorType;
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

}
