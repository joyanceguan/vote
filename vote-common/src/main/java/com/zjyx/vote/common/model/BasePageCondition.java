package com.zjyx.vote.common.model;

import com.zjyx.vote.common.constants.VoteConstants;

public class BasePageCondition {
	
	private int currentPage = 1;//当前页
	private boolean needTotalResults=true;//是否需要查询总数
	private int beginNum;
	private int onePageSize = VoteConstants.DEFAULT_ONEPAGESIZE;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isNeedTotalResults() {
		return needTotalResults;
	}
	public void setNeedTotalResults(boolean needTotalResults) {
		this.needTotalResults = needTotalResults;
	}
	public int getBeginNum() {
		if(currentPage > 0 && onePageSize > 0){
			beginNum = (currentPage - 1) * onePageSize;
		}
		return beginNum;
	}
	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}
	public int getOnePageSize() {
		return onePageSize;
	}
	public void setOnePageSize(int onePageSize) {
		this.onePageSize = onePageSize;
	}
	
	public boolean isRightPageInfo(){
		if(currentPage < 1 || onePageSize < 1){
			return false;
		}
		return true;
	}
}
