package com.zjyx.authority.api.model.bean;

public class ListBaseBean {

	private int currentPage;

	private int onePageSize;

	private long totalResults;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOnePageSize() {
		return this.onePageSize = onePageSize <= 0 ? 10 : onePageSize;
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
		double totalPage = (double) totalResults / getOnePageSize();
		return (int) Math.ceil(totalPage);
	}

	// 数据库第一条数据
	public int getBeginNum() {
		return (currentPage - 1) * onePageSize;
	}
}
