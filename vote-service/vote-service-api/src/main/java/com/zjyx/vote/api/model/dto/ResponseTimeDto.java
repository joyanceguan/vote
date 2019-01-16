package com.zjyx.vote.api.model.dto;

public class ResponseTimeDto {

	private String timeDesc;
	
	private int count;
	
	private String percent;

	public String getTimeDesc() {
		return timeDesc;
	}

	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
	
}
